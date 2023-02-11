def computer_play(computer_hand_cards, computer_target_list, main_pile, discard_pile):
    # Evaluate the usefulness of the top card in the main pile
    main_pile_top_card = main_pile[0]
    main_pile_usefulness = 0
    for target_word in computer_target_list:
        for letter in target_word:
            if letter in main_pile_top_card:
                main_pile_usefulness += 1

    # Evaluate the usefulness of the top card in the discard pile
    discard_pile_top_card = discard_pile[0]
    discard_pile_usefulness = 0
    for target_word in computer_target_list:
        for letter in target_word:
            if letter in discard_pile_top_card:
                discard_pile_usefulness += 1

    # Decide whether to take the card from the main pile or the discard pile based on usefulness
    if main_pile_usefulness >= discard_pile_usefulness:
        computer_hand_cards.append(main_pile.pop(0))
    else:
        computer_hand_cards.append(discard_pile.pop(0))

    # Evaluate the usefulness of the new card and determine its position in the hand
    new_card = computer_hand_cards[-1]
    new_card_usefulness = 0
    for target_word in computer_target_list:
        for letter in target_word:
            if letter in new_card:
                new_card_usefulness += 1
    print(computer_hand_cards)
    for i in range(len(computer_hand_cards) - 1):
        if new_card_usefulness > computer_hand_cards[i]:
            computer_hand_cards.insert(i, computer_hand_cards.pop(-1))
            break
    print(computer_hand_cards)

def main():
    computer_hand_cards = ["s", "a", "b"]
    computer_target_list = ["sad", "s", "s"]
    main_pile = ["d"]
    discard_pile = ["z"]
    computer_play(computer_hand_cards, computer_target_list, main_pile, discard_pile)



if __name__ == "__main__":
    main()
