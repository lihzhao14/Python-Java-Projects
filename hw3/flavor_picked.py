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
    return first_letter


def get_ice_cream_flavor(ice_cream_flavors):
    flavor_picked = ""
    flag = True
    while flag:
        flavor_asked = input("Which flavor would you like (v/c/s)? ")
        first_letter_flavor = get_first_letter_of_user_input(flavor_asked)
        for idx in range(len(ice_cream_flavors)):
            if first_letter_flavor == get_first_letter_of_user_input(ice_cream_flavors[idx]):
                flavor_picked += ice_cream_flavors[idx]
                flag = False

    return flavor_picked


def main():
    ice_cream_flavors = ['Vanilla', 'Chocolate', 'Strawberry']
    flavor_picked = get_ice_cream_flavor(ice_cream_flavors)
    print(flavor_picked)


if __name__ == '__main__':
    main()
