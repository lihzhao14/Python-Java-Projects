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

    # Evaluate the usefulness of cards in computer's hand
    computer_hand_usefulness = [0] * (len(computer_hand_cards) - 1)
    for index, card in enumerate(computer_hand_cards):
        for target_word in computer_target_list:
            if card in target_word:
                computer_hand_usefulness[index] += 1

    # Evaluate the usefulness of the new card and determine its position in the hand
    new_card = computer_hand_cards[-1]
    new_card_usefulness = 0
    for target_word in computer_target_list:
        for letter in target_word:
            if letter in new_card:
                new_card_usefulness += 1

    # Compare the new card with each original card
    for i in range(len(computer_hand_cards) - 1):
        if new_card_usefulness > computer_hand_cards[i]:
            computer_hand_cards.insert(i, computer_hand_cards.pop(-1))
            break









    #
    #     new_card_usefulness = computer_hand_usefulness[-1]
    #     if new_card_usefulness > computer_hand_usefulness[len(computer_hand_usefulness) - 1]:
    #         computer_hand_cards.insert(index, computer_hand_cards.pop(-1))

