import numpy as np
import pandas as pd
from scipy.special import softmax
from sklearn.metrics import precision_recall_fscore_support, classification_report
from sklearn.metrics import f1_score, roc_auc_score, precision_score, recall_score, accuracy_score


def get_metrics(preds):
    preds, labels = preds.predictions, preds.label_ids
    # standard round approach
    pred_flat = np.argmax(preds, axis=1).flatten()
    pr, rec, f, _ = precision_recall_fscore_support(labels, pred_flat, average='weighted')

    print("precision", pr)
    print("recall", rec)
    print("fscore_weighted", f)

    # adjust threshold approach
    preds_adj = np.array([[float(el1), float(el2)] for el1, el2 in preds])
    preds_adj = softmax(preds_adj, axis=1)
    roc_auc = roc_auc_score(labels, preds_adj[:, 1])
    print("roc_auc", roc_auc)

    all_metrcis = []
    for threshold in [0.01, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1]:
        metrcis = []
        pred_labels = (preds_adj[:, 1] >= threshold).astype(int)
        metrcis.append(threshold)
        metrcis.append(round(f1_score(labels, pred_labels, average='weighted'), 2))
        metrcis.append(round(precision_score(labels, pred_labels), 2))
        metrcis.append(round(recall_score(labels, pred_labels), 2))
        metrcis.append(round(accuracy_score(labels, pred_labels), 2))
        all_metrcis.append(metrcis)

    df_metrics = pd.DataFrame(data=all_metrcis, columns=['threshold', 'f1', 'prec', 'rec', 'acc'])
    df_metrics = df_metrics.sort_values(by='f1', ascending=False)

    print(classification_report(labels, pred_flat))

    print(df_metrics.head())

    return f


def compute_metrics(pred_):
    labels = pred_.label_ids
    preds = pred_.predictions.argmax(-1)
    precision, recall, f1, _ = precision_recall_fscore_support(labels, preds, average='weighted')
    acc = accuracy_score(labels, preds)
    return {
        'accuracy': acc,
        'f1': f1,
        'precision': precision,
        'recall': recall
    }