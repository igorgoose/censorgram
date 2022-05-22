from typing import Tuple
from funcs.functions import get_metrics, compute_metrics
from transformers import BertTokenizer, BertForSequenceClassification, TrainingArguments
from transformers import Trainer
import torch
from transformers.file_utils import cached_property
import pandas as pd
from src.classes import UnsafeDataset
from transformers.trainer_callback import EarlyStoppingCallback

model_name = 'DeepPavlov/rubert-base-cased-conversational'
tokenizer = BertTokenizer.from_pretrained(model_name)
model = BertForSequenceClassification.from_pretrained(model_name)

data = pd.read_csv("C:\\Users\\Igor\\Desktop\\university\\diplom\\censorgram\\moderator\\data\\train.csv")
data_eval = pd.read_csv("C:\\Users\\Igor\\Desktop\\university\\diplom\\censorgram\\moderator\\data\\val.csv")
data_test = pd.read_csv("C:\\Users\\Igor\\Desktop\\university\\diplom\\censorgram\\moderator\\data\\test.csv")

print(data.describe())

# приводим датасет в порядок
# фильтрация по ограничениям
label_name = 'inappropriate'
threshold = 0
data = data[(data[label_name] >= 1 - threshold) | (data[label_name] <= threshold)]
data_eval = data_eval[(data_eval[label_name] >= 1 - threshold) | (data_eval[label_name] <= threshold)]
data_test = data_test[(data_test[label_name] >= 1 - threshold) | (data_test[label_name] <= threshold)]

# окргуление до 0 или 1
data[label_name] = data[label_name].apply(round)
data_eval[label_name] = data_eval[label_name].apply(round)
data_test[label_name] = data_test[label_name].apply(round)

print(data.describe())

train_dataset = UnsafeDataset(tokenizer(data.text.tolist(),
                                        max_length=64,
                                        truncation=True,
                                        padding='longest'), data.inappropriate.tolist())

eval_dataset = UnsafeDataset(tokenizer(data_eval.text.tolist(),
                                       max_length=64,
                                       truncation=True,
                                       padding='longest'), data_eval.inappropriate.tolist())

test_dataset = UnsafeDataset(tokenizer(data_test.text.tolist(),
                                       max_length=64,
                                       truncation=True,
                                       padding='longest'), data_test.inappropriate.tolist())

torch.cuda.is_available = lambda: False
device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')


class TrAr(TrainingArguments):
    @cached_property
    def _setup_devices(self) -> Tuple["torch.device", int]:
        return device


model.to(device)

for param in model.bert.parameters():
    param.requires_grad = True

training_args = TrainingArguments(
    output_dir='./unsafe/FINAL_VERS',  # output directory
    overwrite_output_dir=True,
    num_train_epochs=5,  # total # of training epochs
    per_device_train_batch_size=32,  # batch size per device during training
    per_device_eval_batch_size=32,  # batch size for evaluation
    warmup_steps=0,  # number of warmup steps for learning rate scheduler
    weight_decay=1e-8,  # strength of weight decay
    learning_rate=2e-5,
    save_total_limit=2,
    logging_dir='./logs',  # directory for storing logs
    logging_steps=2500,
    eval_steps=2500,
    save_steps=2500,
    evaluation_strategy='steps', metric_for_best_model='f1', greater_is_better=True, load_best_model_at_end=True
)
trainer = Trainer(
    model=model,  # the instantiated 🤗 Transformers model to be trained
    args=training_args,  # training arguments, defined above
    train_dataset=train_dataset,  # training dataset
    eval_dataset=eval_dataset,  # evaluation dataset
    tokenizer=tokenizer,
    compute_metrics=compute_metrics
)
trainer.compute_loss()
trainer.add_callback(EarlyStoppingCallback(3))
print(trainer.train())

pred = trainer.predict(test_dataset)
print(get_metrics(pred))
