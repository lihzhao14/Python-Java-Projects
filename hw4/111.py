computer_hand_cards = ["s", "a", "b", "e"]
computer_target_list = ["sad", "se", "se"]
discard_pile = ["c", "z"]

print(computer_hand_cards)

computer_hand_usefulness = [0] * (len(computer_hand_cards) - 1)
for idx in range(len(computer_hand_cards[:(len(computer_hand_cards) - 1)])):
    for target_word in computer_target_list:
        if computer_hand_cards[idx] in target_word:
            computer_hand_usefulness[idx] += 1

print(computer_hand_usefulness)
print(computer_hand_usefulness[0])

new_card = computer_hand_cards[-1]
new_card_usefulness = 0
for target_word in computer_target_list:
    for letter in target_word:
        if letter in new_card:
            new_card_usefulness += 1

print(new_card_usefulness > computer_hand_usefulness[0])

for i in range(len(computer_hand_usefulness)):
    if new_card_usefulness > computer_hand_usefulness[i]:
        computer_hand_cards.insert(i, computer_hand_cards.pop(-1))
        discard_pile.insert(0, computer_hand_cards.pop(i + 1))
        break


print("new card: ", new_card)
print("computer_hand_cards:", computer_hand_cards)
print("discard_pile:", discard_pile)
