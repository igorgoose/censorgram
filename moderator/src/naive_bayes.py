from sklearn.datasets import load_files
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB

# Load the text data
categories = [
    'alt.atheism',
    'talk.religion.misc',
    'comp.graphics',
    'sci.space',
]
twenty_train_subset = load_files('datasets/20news-bydate-train/',
                                 categories=categories, encoding='latin-1')
twenty_test_subset = load_files('datasets/20news-bydate-test/',
                                categories=categories, encoding='latin-1')

# Turn the text documents into vectors of word frequencies
vectorizer = TfidfVectorizer(min_df=2)
X_train = vectorizer.fit_transform(twenty_train_subset.data)
y_train = twenty_train_subset.target

# Fit a classifier on the training set
classifier = MultinomialNB().fit(X_train, y_train)
print("Training score: {0:.1f}%".format(
    classifier.score(X_train, y_train) * 100))

# Evaluate the classifier on the testing set
X_test = vectorizer.transform(twenty_test_subset.data)
y_test = twenty_test_subset.target
print("Testing score: {0:.1f}%".format(
    classifier.score(X_test, y_test) * 100))
