import random
import copy
import string


def read_from_file(file_name):
    """
    Reads all words from file
    Parameter file_name is the name of the file
    This function returns a list containing all the words
    """
    f = open(file_name, "r")
    all_words = f.read().splitlines()
    f.close()
    return all_words


def ask_for_length():
    """
    Ask the user for the number of hand cards (= Length).
    Returns the number of hand cards L.
    """
    # TODO：1. Ask the user for the number of hand cards so that the length of words players
    #       is going to guess is determined
    #       2. Prompt again if the user input is not a valid integer, or if the number is not
    #       between 3 to 10, inclusive
    #       3. Returns the number of hand cards L
    while True:
        try:
            length_of_word = int(input("Enter a number between 3 ~ 10 to be the length of the word you're going to "
                                       "guess:"))
            if length_of_word in range(3, 11):
                break
            else:
                print("Please enter a valid integer in [3, 10]")
        except ValueError:
            print("Please enter a valid integer")
    return length_of_word


def filter_word_list(all_words, length):
    """
    Collect the words which has the same length as the given length
    Parameter all_words is the list of all words.
    Parameter length is the given length.
    Returns a list of words with the specific length.
    """

    word_list_specific_length = []
    for idx in range(len(all_words)):
        if len(all_words[idx]) == length:
            word_list_specific_length.append(all_words[idx])
            # print(all_words[idx])
    return word_list_specific_length


def set_up(length):
    """
    Create the main pile
    Create the discard pile of 0 cards, represented as an empty list
    Parameter length is the given length
    Returns both lists as a tuple, with the main pile as the first item and the discard pile as the second item
    """
    # TODO: Creates a main pile of 26 * length cards, represented as a list of lowercase
    #       letters, with length of each letter
    main_pile = []
    for letter in 'abcdefghijklmnopqrstuvwxyz':
        main_pile.extend([letter] * length)
    # Creates a discard pile of 0 cards, represented as an empty list
    discard_pile = []
    return main_pile, discard_pile


def shuffle_cards(pile):
    """
    This function shuffles the given pile
    Parameter pile is the given list of words
    This function doesn’t return anything
    """
    # TODO
    random.shuffle(pile)


def move_to_discard_pile(discard_pile, card):
    """
    Move the given card to the top of the discard pile
    Parameter discard_pile is the discard pile
    Parameter card is the given letter to be discarded
    This function doesn’t return anything
    """
    # TODO
    # Move the given card to the top of the discard pile
    discard_pile.insert(0, card)


def get_first_from_pile_and_remove(pile):
    """
    Get the first card from the pile and remove the first card of the pile
    Parameter pile is the list from which to remove the first element
    Return first card of the pile: first_card
    """
    # TODO
    top_card = pile[0]
    # remove the first item of the given list
    pile.pop(0)
    return top_card


def deal_initial_cards(main_pile, discard_pile, length):
    """
    Start the game by dealing two sets of length cards each, from the given main_pile.
    Deal order: one card to the computer, one to the user, one to the computer, one to the user, and so on.
    Returns a tuple containing two lists, the first one representing the human’s hand and the second
    one representing the computer’s hand
    """
    # TODO
    computer_hand_cards = []
    human_hand_cards = []
    # Remove the card on top of the main pile and put it on the discard pile
    for i in range(length):
        # The computer is always the first person that gets dealt to and always plays first
        computer_turn = main_pile.pop(0)
        computer_hand_cards.append(computer_turn)
        # Generate Human's turn
        human_turn = main_pile.pop(0)
        human_hand_cards.append(human_turn)
    top_card = get_first_from_pile_and_remove(main_pile)
    discard_pile.insert(0, top_card)
    return human_hand_cards, computer_hand_cards


def check_bricks(main_pile, discard_pile):
    """
    Check whether the main_pile is empty.
    If so, shuffles the discard_pile and moves all the cards to the main_pile. Then
    turn over the top card of the main_pile to be the start of the new discard_pile.
    Otherwise, do nothing.
    Return nothing.
    """

    if not main_pile:
        shuffle_cards(discard_pile)
        # main_pile = copy.deepcopy(discard_pile)
        main_pile[:] = discard_pile.copy()  # deepcopy是copy全新的内容到新的地址上；
        # copy是copy全新的内容到原来的地址上
        discard_pile.clear()
        # turn over the top card of the main_pile to be the start of the new discard_pile.
        top_card = get_first_from_pile_and_remove(main_pile)
        discard_pile.insert(0, top_card)


def computer_play(computer_hand_cards, computer_target_list, main_pile, discard_pile):
    """
    1. Given the computer’s current hand, then let it make a decision:
    ● take the top card from the discard pile
    ● or do you want to take a card from the top of the main pile
    2. See if that card is useful and the position it should go into
    Parameter computer_hand_cards is the computer’s hand cards
    Parameter main_pile is the main pile
    Parameter discard_pile is the discard pile
    Parameter computer_target_list is a list of words.
    This function doesn’t return anything
    """

    # Select which word is going to be guessed
    match_computer_hand_list = [0] * len(computer_hand_cards)
    for idx_target, target_word in enumerate(computer_target_list):
        # change selected target word to be a list
        list_target_word = list(target_word)
        for idx in range(len(computer_hand_cards)):
            # Compare each char in target word with each letter in computer hand
            if list_target_word[idx] == computer_hand_cards[idx]:
                match_computer_hand_list[idx_target] += 1
    max_match = max(match_computer_hand_list)
    max_index = match_computer_hand_list.index(max_match)
    selected_target_word = computer_target_list[max_index]
    # change the selected word to be a list
    list_selected_target_word = list(selected_target_word)

    # Evaluate the usefulness of the top card in the main pile
    main_pile_top_card = main_pile[0]
    main_pile_usefulness = 0
    for target_word in computer_target_list:
        for letter in target_word:
            if letter in main_pile_top_card:
                main_pile_usefulness += 1

    # Evaluate the usefulness of the top card in the discard pile
    print("Discard pile: ".format(discard_pile))
    discard_pile_top_card = discard_pile[0]
    discard_pile_usefulness = 0
    for target_word in computer_target_list:
        for letter in target_word:
            if letter in discard_pile_top_card:
                discard_pile_usefulness += 1

    # Decide whether to take the card from the main pile or the discard pile based on usefulness
    if main_pile_usefulness >= discard_pile_usefulness:
        print("Computer draw a card from the MAIN PILE. The card is: '{}'".format(main_pile[0]))
        computer_hand_cards.append(main_pile.pop(0))
    else:
        print("Computer draw a card from the DISCARD PILE. The card is: '{}'".format(discard_pile[0]))
        computer_hand_cards.append(discard_pile.pop(0))

    # Evaluate the usefulness of cards in computer's hand except the new card
    computer_hand_usefulness = [0] * (len(computer_hand_cards) - 1)
    # Pick the letters in hand except the new card
    for idx in range(len(computer_hand_cards[:(len(computer_hand_cards) - 1)])):
        if computer_hand_cards[idx] == list_selected_target_word[idx]:
            computer_hand_usefulness[idx] += 1

    # Evaluate the usefulness of the new card and determine its position in the hand
    new_card_usefulness_list = [0] * len(list_selected_target_word)
    new_card = computer_hand_cards[-1]
    for idx in range(len(list_selected_target_word)):
        if new_card == list_selected_target_word[idx]:
            new_card_usefulness_list[idx] += 1
    max_new_card = max(new_card_usefulness_list)
    max_new_card_index = new_card_usefulness_list.index(max_new_card)
    # 无法解决如果有两个最大值，那index应该选哪个

    # Compare the new card with each original card
    min_usefulness_hand = min(computer_hand_usefulness)
    min_index = computer_hand_usefulness.index(min_usefulness_hand)
    if max_new_card_index >= min_index:
        computer_hand_cards.insert(min_index, computer_hand_cards.pop(-1))
        discard_pile.insert(0, computer_hand_cards[min_index + 1])
        computer_hand_cards.pop(min_index + 1)
    else:
        discard_pile.insert(0, computer_hand_cards.pop(-1))
    print()
    print("Computer's current hand is {}".format(computer_hand_cards))


def ask_for_the_letter_to_be_replaced(length):
    """
    Ask for the index of the letter that the user wants to replace
    Parameter length is the number of cards in the human’s hand
    Returns the index of the letter to be replaced
    """

    while True:
        try:
            index = int(input("Enter the index of the letter to be replaced?"))
            # Do not include its own index
            if index in range(0, length):
                break
            else:
                print("Please enter a valid integer in [0, {}]".format(length - 1))
        except ValueError:
            print("Please enter a valid integer")
    return index


def ask_yes_or_no(msg):
    """
    Parameter msg is the message to display
    Prompt again if the input is invalid
    Returns True if the user answers ‘y’ or ‘yes’, and returns False if the user answers ‘n’ or ‘no’
    """
    while True:
        response = input(msg).lower()
        if response in ['y', 'yes']:
            return True
        elif response in ['n', 'no']:
            return False
        else:
            print("Invalid input. Please enter again")


def check_game_over(human_hand_cards, computer_hand_cards, words_with_specific_length):
    """
    Check if the game ends
    If there is a tie, the game ends as well
    Parameter human_hand_cards is the human’s current hand (list)
    Parameter computer_hand_cards is the computer’s current hand (list)
    Parameter words_with_specific_length is a list containing all the words with the specific length
    Returns True if the human or the computer wins the game, otherwise False
    """
    # TODO
    human_hand_cards_str = "".join(human_hand_cards)
    computer_hand_cards_str = "".join(computer_hand_cards)
    if human_hand_cards_str in words_with_specific_length:
        return True
    elif computer_hand_cards_str in words_with_specific_length:
        return True
    elif human_hand_cards_str in words_with_specific_length and computer_hand_cards_str in words_with_specific_length:
        return True
    else:
        return False


def main():
    # reads all words from file
    all_words = read_from_file("words.txt")

    print("Welcome to the game!")

    # ask for a number as the length of the word
    length = ask_for_length()

    # filter all_words with a length equal to the given length
    filtered_words = filter_word_list(all_words, length)

    # set up main_pile and discard_pile
    # TODO
    main_pile, discard_pile = set_up(length)

    # shuffle main pile
    shuffle_cards(main_pile)

    # deal cards to players, creating human_hand_cards and computer_hand_cards
    # and initialize discard pile
    # TODO
    human_hand_cards, computer_hand_cards = deal_initial_cards(main_pile, discard_pile, length)
    # start the game
    while True:
        # check if main_pile is empty by calling check_bricks(main_pile, discard_pile)
        check_bricks(main_pile, discard_pile)

        # computer play goes here
        print()
        print("Computer's turn")
        print("Computer's current hand is "
              "{}".format(computer_hand_cards))
        print()
        computer_play(computer_hand_cards, filtered_words, main_pile, discard_pile)

        # human play goes here
        print("----------------------------------------------------------------------------------------------------")
        print("Human's turn")
        print("Human's current hand is "
              "{}".format(human_hand_cards))
        print()
        print("Pick '{}' from DISCARD PILE or reveal the card from MAIN PILE\n".format(discard_pile[0]))

        flag1 = ask_yes_or_no("If you want to get the card from DISCARD PILE, type 'y/yes';\n"
                              "Otherwise, type 'n/no', you will get a card from MAIN PILE.")
        print()

        if flag1:
            human_card = get_first_from_pile_and_remove(discard_pile)
        else:
            human_card = get_first_from_pile_and_remove(main_pile)
        print("The letter from MAIN PILE is '{}'".format(human_card))

        # Another way to judge the card from MAIN PILE or DISCARD PILE
        # while True:
        #     reply = input("Reply 'D' or 'M' to respond: ")
        #     if reply is 'D':
        #         human_card = discard_pile[0]
        #         return False
        #     elif reply is 'M':
        #         human_card = main_pile[0]
        #         print("The letter from MAIN PILE is '{}'".format(human_card))
        #         return False

        flag2 = ask_yes_or_no("Do you want to accept this card?\n"
                              "Type 'y/yes' to accept, 'n/no' to discard.")
        print()
        if flag2:
            index = ask_for_the_letter_to_be_replaced(length)
            print("You replaced '{}' with '{}'".format(human_hand_cards[index], human_card))
            human_hand_cards.insert(index, human_card)
            human_hand_cards.pop(index + 1)
        else:
            discard_pile.insert(0, human_card)
            print("You didn't put new card in your hand")

        print("Your word list is: {}".format(human_hand_cards))
        # check if game is over and print out results
        check_game_over(human_hand_cards, computer_hand_cards, filtered_words)
        pass


if __name__ == "__main__":
    main()
