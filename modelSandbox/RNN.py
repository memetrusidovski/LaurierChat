import matplotlib.pyplot as plt
from pickletools import optimize
from tabnanny import verbose
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences
import json
import numpy as np
from tensorflow.keras import losses
from tensorflow.keras import utils
from tensorflow.keras.layers import TextVectorization

import tensorflow_datasets as tfds
import tensorflow_text as tf_text


#with open("/tmp/sarcasm.json", 'r') as f:
with open("modelSandbox/isComplete_copy.json", 'r') as f:
    datastore = json.load(f)

s = []
label = []
c = 0
for item in datastore:
    s.append(item["sentence"])
    label.append(item["isComplete"])


vocab_size = 10000
embedding_dim = 16
max_length = 100
trunc_type = 'post'
padding_type = 'post'
oov_tok = "<OOV>"


training_size = 15
training_sentences = s[0:training_size]
testing_sentences = s[training_size:]
training_labels = label[0:training_size]
testing_labels = label[training_size:]


tok = Tokenizer(num_words=vocab_size, oov_token=oov_tok)

tok.fit_on_texts(training_sentences)

word_index = tok.word_index

training_sequences = tok.texts_to_sequences(training_sentences)
training_padded = pad_sequences(
    training_sequences, maxlen=max_length, padding=padding_type, truncating=trunc_type)

testing_sequences = tok.texts_to_sequences(testing_sentences)
testing_padded = pad_sequences(
    testing_sequences, maxlen=max_length, padding=padding_type, truncating=trunc_type)


'''
print(word_index)
print(training_sentences)'''


training_padded = np.array(training_padded)
training_labels = np.array(training_labels)
testing_padded = np.array(testing_padded)
testing_labels = np.array(testing_labels)


model = keras.Sequential([
    layers.Embedding(vocab_size, 64, mask_zero=True),
    layers.Conv1D(64, 5, padding="valid", activation="relu", strides=2),
    layers.GlobalMaxPooling1D(),
    layers.Dense(4)
])

model.compile(loss=losses.SparseCategoricalCrossentropy(from_logits=True),
              optimizer='adam', metrics=['accuracy'])

num_epochs = 30

#print(training_padded)
#model.summary()

history = model.fit(training_padded, training_labels, epochs=num_epochs,
                    validation_data=(testing_padded, testing_labels), verbose=2)


def plot_graphs(history, string):
  plt.plot(history.history[string])
  plt.plot(history.history['val_'+string])
  plt.xlabel("Epochs")
  plt.ylabel(string)
  plt.legend([string, 'val_'+string])
  plt.show()


plot_graphs(history, "accuracy")
plot_graphs(history, "loss")
