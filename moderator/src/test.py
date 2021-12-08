import numpy as np

a = np.arange(8).reshape(2, 2, 2) + 10
print(a)
print(np.argmax(a, 0))