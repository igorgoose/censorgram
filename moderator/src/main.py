from flask import Flask, request
from flask_restful import Api, Resource, reqparse
from transformers import BertForSequenceClassification, BertTokenizer

app = Flask(__name__)
api = Api(app)

model = BertForSequenceClassification.from_pretrained("../model/", local_files_only=True)
tokenizer = BertTokenizer.from_pretrained("../model/", local_files_only=True)

moderator_request_parser = reqparse.RequestParser()
moderator_request_parser.add_argument("id", type=str, required=True,
                                      help="id of the object under inspection is required!")
moderator_request_parser.add_argument("text", type=str, required=True, help="Text for inspection is required!")

class Moderator(Resource):

    def get(self):
        return {"message": "working!"}

    def post(self):
        args = moderator_request_parser.parse_args()
        encoded_input = tokenizer(args["text"], return_tensors='pt')
        output = model(**encoded_input)
        result = output.get('logits').argmax(axis=1)[0].item()
        prob = output.get('logits').softmax(1)[0][result].item()
        return {"id": args["id"], "inappropriate": result, "probability": prob}


api.add_resource(Moderator, "/moderator/inspect")

if __name__ == "__main__":
    app.run(debug=True)
