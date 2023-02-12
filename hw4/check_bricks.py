import copy
import random


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


def main(main_pile, discard_pile):
    # discard_pile, main_pile = set_up(7)
    # copy_of_shuffled_discard = discard_pile.copy()
    # print(main_pile)
    # print(discard_pile)
    if not main_pile:
        shuffle_cards(discard_pile)
        main_pile = copy.deepcopy(discard_pile)
        # print(len(main_pile))
        discard_pile = []
        # print(main_pile)
        # print(discard_pile)
        # turn over the top card of the main_pile to be the start of the new discard_pile.
        top_card = get_first_from_pile_and_remove(main_pile)
        discard_pile.insert(0, top_card)
    print(main_pile)
    print(discard_pile)
    print(len(main_pile))
    print(len(discard_pile))
    print(7 * 26 - 1 == len(main_pile))
    print(1 == len(discard_pile))
    # print(set(copy_of_shuffled_discard) == set(main_pile + discard_pile))


if __name__ == "__main__":
    main()
