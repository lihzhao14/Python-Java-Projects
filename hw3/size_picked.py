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


def get_ice_cream_size(ice_cream_sizes):
    size_picked = ""
    first_letter_sizes = []
    flag = True
    while flag:
        size_asked = input("Which size would you like (s/m/l)? ")
        first_letter_size = get_first_letter_of_user_input(size_asked)
        for idx in range(len(ice_cream_sizes)):
            # print(get_first_letter_of_user_input(ice_cream_sizes[idx]))
            a = get_first_letter_of_user_input(ice_cream_sizes[idx]
            first_letter_sizes.append(a)

        index = first_letter_sizes.index(first_letter_size)
        size_picked.join(ice_cream_sizes[idx])
        size_picked = ice_cream_prices[index]
    return


        if first_letter_size == get_first_letter_of_user_input(ice_cream_sizes[idx]):
            size_picked.join(ice_cream_sizes[idx])
            flag = False
        else:
            print("Input is not a valid size.")
            break
    return size_picked


def main():
    ice_cream_sizes = ['Small', 'Medium', 'Large']
    size_picked_ = get_ice_cream_size(ice_cream_sizes)
    print(size_picked_)


if __name__ == '__main__':
    main()
