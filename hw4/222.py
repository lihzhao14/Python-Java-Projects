all_words = ["Given", "a", "list", "of", "words", "and", "a", "number", "returns", "a", "list",
                 "of", "words", "with", "the", "specific"]
length = 5
list1 = []
for idx in range(len(all_words)):
    if len(all_words[idx]) == length:
        list1.append(all_words[idx])
        print(all_words[idx])
print(list1)