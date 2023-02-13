def computer_play(computer_hand_cards, computer_target_list, main_pile, discard_pile):

    # Select which word is going to be guessed
    match_computer_hand_list = [0] * len(computer_hand_cards)
    for idx_target, target_word in computer_target_list:
        # change selected target word to be a list
        list_target_word = list(target_word)
        for idx in range(len(computer_hand_cards)):
            # Compare each char in target word with each letter in computer hand
            if list_target_word[idx] == computer_hand_cards[idx]:
                match_computer_hand_list[idx_target] += 1
    max_match = max(match_computer_hand_list)
    max_index = match_computer_hand_list.index(max_match)
    selected_target_word = computer_target_list[max_index]

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
    #
    for idx in range(len(computer_hand_cards[:(len(computer_hand_cards) - 1)])):
            if computer_hand_cards[idx] in selected_target_word:
                computer_hand_usefulness[idx] += 1

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

