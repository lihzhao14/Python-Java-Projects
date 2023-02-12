def computer_play(computer_hand_cards, computer_target_list, main_pile, discard_pile):
    # TODO: 1. Determine if the top card of the discard pile or the top card of the main pile is more useful to the
    #       computer.
    #       2. Evaluate the usefulness of each card based on the current contents of the computer's hand cards
    #       and target list of words.
    #       3. Decide whether to take the top card from the discard pile or from the top of the main pile.
    #       4. If take the top card from the discard pile, determine which card should be replaced and place that card
    #       to the discard pile (the far right side)
    #       5. If take the top card from the top of the main pile, determine the position that the taken card
    #       should be placed in the computer's hand cards. (Use index)

    # Check if any of the cards in the computer's hand match the target list
    same = 0
    for word in computer_target_list:
        for card in computer_hand_cards:
            if card in word:
                same += 1
    probability = same / len(computer_hand_cards)

    for card in computer_hand_cards:
        if card in computer_target_list:
            # If a match is found, remove the card from the computer's hand and add it to the target list
            computer_hand_cards.remove(card)
            computer_target_list.remove(card)
            return "collected", card

    # Check if the top card of the discard pile is useful
    if discard_pile:
        top_card_discard = discard_pile[0]
        for letter in computer_target_list:
            if top_card_discard in letter:
                # Take out the top card of the discard pile
                discard_pile.pop(0)
                # Put the top card of the discard pile at the far right of computer's hand
                computer_hand_cards.append(top_card_discard)
                break




    # If no match is found, draw a card from the main pile
    if len(main_pile) > 0:
        # remove and return the last item from a list
        drawn_card = main_pile.pop()
        computer_hand_cards.append(drawn_card)
        return "drawn", drawn_card

    # If the main pile is empty, draw a card from the discard pile
    else:
        drawn_card = discard_pile.pop()
        computer_hand_cards.append(drawn_card)
        return "drawn", drawn_card
