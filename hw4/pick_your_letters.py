import random
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

    """
    # TODO


def filter_word_list(all_words, length):
    """

    """
    # TODO


def set_up(length):
    """

    """
    # TODO


def shuffle_cards(pile):
    """

    """
    # TODO


def move_to_discard_pile(discard_pile, card):
    """

    """
    # TODO


def deal_initial_cards(main_pile, discard_pile, length):
    """

    """
    # TODO


def get_first_from_pile_and_remove(pile):
    """

    """
    # TODO


def check_bricks(main_pile, discard_pile):
    """
    """
    # TODO


def computer_play(computer_hand_cards, computer_target_list, main_pile, discard_pile):
    """

    """
    # TODO


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

    # filter all_words with a length equal to the given length
    # TODO

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
