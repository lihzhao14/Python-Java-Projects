# Abstract
This python project is about a game called "Pick Your Letters". This game needs two players and we will just play the user versus the computer.
The user’s moves are decided by the user playing the game, by asking for input, and
the computer’s moves are decided by the program.

At the very beginning, the program will prompt the user to enter a number as the
Length L to be the length of the word they are going to make up. The game starts
with a main pile of n cards, each labeled with a letter, and n = 26 * L. So, there are L
of each letter. The objective it to be the first player to arrange L cards in your hand in
an order that forms a word.
The cards in the main pile are shuffled and both the user and the computer are each
initially dealt L cards from the main pile. As a player receives each card, they must
place the card at the far right of their hand in the order they received it. E.g., if a user
receives ‘A’, ‘R’, ‘K’, ‘I’, ‘P’ consecutively, their hand is in the same order and looks like:
‘A’, ‘R’, ‘K’, ‘I’, ‘P’.
After the hand is dealt to the user and the computer, there will be ((26 * L) – (2 * L))
cards left in the main pile. The top card of the main pile (the card to the far left of
cards list) is turned over to begin the discarded card pile.
On each player’s turn, the player chooses to either pick up the top card from the
discard pile, or to pick up the top card from the main pile. The top card from the
discard pile is known. In other words, the discard pile is ‘face up’ and everyone
knows what the letter on it is. The main pile is ‘face down’ and the player does not
know what the card is.

Once a player chooses a card, either from the discard pile or from the main pile, the
player decides where in the hand to put the card. The hand is always of the size L, 
so placing a card means that an existing card in the hand is removed and replaced
with the new card.
If the player takes a card from the main pile (the one that is ‘face down’), the player
can reject it and place it in the discard pile. This means that nothing in that player’s
hand changes during that turn. If the player takes a card from the discard pile (the
one that is ‘face up’), the player MUST place it into their hand.

The first player whose cards in hand form a word wins. How does the program
know the cards form a word? We’ll discuss that below, soon!
If, at any point, all of the cards have been removed from the main pile, then all of the
cards in the discard pile are shuffled and moved to the main pile. Then the top card
is turned over to start the new discard pile.

![image](https://github.com/lihzhao14/Python-Java-Projects/assets/113971230/b70ce70b-5490-4aab-9647-2a7f86e0960e)
