lst = [1, 2, 4, 3, 6, 5]
for elem in lst:
    print(elem)
    if elem % 2 == 0:
        lst.remove(elem)
print(lst)
