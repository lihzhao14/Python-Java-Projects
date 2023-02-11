computer_hand_cards = ["s", "a", "b", "d"]
computer_target_list = ["sad", "s", "s"]

computer_hand_usefulness = [0] * (len(computer_hand_cards))
for index, card in enumerate(computer_hand_cards):
    for target_word in computer_target_list:
        if card in target_word:
            computer_hand_usefulness[index] += 1

print(computer_hand_usefulness)