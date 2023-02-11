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
            length_of_word = int(input("Input the length of the word:"))
            if length_of_word in range(3, 11):
                break
            else:
                print("Please enter a valid integer in [3, 10]")
        except ValueError:
            print("Please enter a valid integer")
    return length_of_word


def filter_word_list(all_words, length):
    """
    Randomly select the words which has the same number as the value of length.
    Parameter all_words is the list of all words.
    Parameter length is the given length.
    Returns a list of words with the specific length.
    """
    # TODO: 1. Given a list of words, and a number, returns a list of words with the specific length
    #       2. Parameter all_words is the list of all words
    #       3. Parameter length is the given length
    random_word_list = []
    while len(random_word_list) <= length:
        random_word = random.choice(all_words)
        if len(random_word) == length:
            random_word_list.append(random_word)
    return random_word_list


def set_up(length):
    """
    Create main pile and discard pile.
    Parameter length is the given length
    Returns a tuple of lists of mail pile and discard pile
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
    first_card = pile[0]
    # remove the first item of the given list
    pile.pop(0)
    return first_card


def deal_initial_cards(main_pile, discard_pile, length):
    """
    Start the game by dealing two sets of length cards each, from the given main_pile.
    Deal order: one card to the computer, one to the user, one to the computer, one to the user, and so on.
    Returns a tuple containing two lists, the first one representing the human’s hand and the second
    one representing the computer’s hand
    """
    # TODO
    computer_hand = []
    human_hand = []
    # Remove the card on top of the main pile and put it on the discard pile
    for i in range(length):
        # The computer is always the first person that gets dealt to and always plays first
        computer_word = main_pile.pop(0)
        computer_hand.append(computer_word)
        # Generate Human's turn
        human_word = main_pile.pop(0)
        human_hand.append(human_word)
    top_card = get_first_from_pile_and_remove(main_pile)
    discard_pile.insert(0, top_card)
    return computer_hand, human_hand


def check_bricks(main_pile, discard_pile):
    """
    Check whether the main_pile is empty.
    If so, shuffles the discard_pile and moves all the cards to the main_pile. Then
    turn over the top card of the main_pile to be the start of the new discard_pile.
    Otherwise, do nothing.
    Return nothing.
    """

    if main_pile == "":
        shuffle_cards(discard_pile)
        main_pile = copy.deepcopy(discard_pile)
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
    # TODO 1. determine the inputs and outputs of the program:
    #       Inputs: omputer_hand_cards, computer_target_list, main_pile, discard_pile
    #       Outputs: take the card or always reject it.


def ask_for_the_letter_to_be_replaced(length):
    """

    """
    # TODO


def ask_yes_or_no(msg):
    """

    """
    # TODO


def check_game_over(human_hand_cards, computer_hand_cards, words_with_specific_length):
    """

    """
    # TODO


def main():
    # reads all words from file
    all_words = read_from_file("words.txt")

    print("Welcome to the game!")

    # ask for a number as the length of the word
    # TODO
    length = ask_for_length()
    # filter all_words with a length equal to the given length
    # TODO
    filter_word_list(all_words, length)

    # set up main_pile and discard_pile
    # TODO

    # shuffle main pile
    # TODO

    # deal cards to players, creating human_hand_cards and computer_hand_cards
    # and initialize discard pile
    # TODO

    # start the game
    while True:
        # check if main_pile is empty by calling check_bricks(main_pile, discard_pile)
        
        # computer play goes here
        # TODO

        # human play goes here
        # TODO

        # check if game is over and print out results
        # TODO
        pass

if __name__ == "__main__":
    main()
