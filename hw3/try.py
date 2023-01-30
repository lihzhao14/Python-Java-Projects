question = input("String:")
first_letter = ""
    # TODO: Write your code here
question = question.strip()
question = question.lower()
first_letter = first_letter.join(question[0])
print(first_letter)
