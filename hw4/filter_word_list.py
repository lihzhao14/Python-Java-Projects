import random

random_word_list = []
length = 4
all_words = ["Give", "a", "list", "of", "words", "and", "a", "number", "returns", "a", "list",
             "of", "word", "with", "them", "specific"]
while len(random_word_list) < length:
    random_word = random.choice(all_words)
    if len(random_word) == length:
        random_word_list.append(random_word)
print(random_word_list)
