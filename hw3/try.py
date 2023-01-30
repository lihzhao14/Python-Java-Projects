def get_first_letter_of_user_input(question):
    """
    Takes in a string as its argument, to be used as the question you want the user to be asked.
    Gets input from the user, removes whitespace and makes all letters lowercase
    Hint: Use the strip() and lower() functions
    Returns: The first letter of the input the user provides. Ask again if the input is empty.
    """

    first_letter = ""
    # TODO: Write your code here
    question = question.strip()
    question = question.lower()
    first_letter = first_letter.join(question[0])
    print(first_letter)
    return first_letter


ice_cream_sizes = ['Small', 'Medium', 'Large']
size_picked = ""
# TODO: Write your code here
flag = True
while flag:
    question = input("Which size would you like (s/m/l)? ")
    get_first_letter_of_user_input(question)
    if question in ["s", "m", "l", "S", "M", "L"]:
        if question == "s" or "S":
            size_picked = size_picked.join(ice_cream_sizes[0])
        elif question == "m" or "M":
            size_picked = size_picked.join(ice_cream_sizes[1])
        elif question == "l" or "L":
            size_picked = size_picked.join(ice_cream_sizes[2])
        print(size_picked)
        flag = False

    else:
        print("Input is not a valid size.")
