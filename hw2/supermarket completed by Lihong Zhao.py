# Student Name: Lihong Zhao
# Student ID: 51007389
# Statement: I admit that this assignment was done by me alone without help.

# Code
# import the random module
# use "winnings = random.randint(2, 10)" to generate a random int from 2 - 10 and store in a variable "winnings"
import random
# import the time module
import time

# unit price of a lottery ticket
constant_lottery_unit_price = 2

# unit price of an apple
constant_apple_unit_price = .99

# unit price of a can of beans
constant_canned_beans_unit_price = 1.58

# unit price of a soda
constant_soda_unit_price = 1.23

# the user has initial $5 for shopping
money = 5

# the user has spent $0 initially
money_spent = 0

# the amounts of lottery tickets, apples, cans of beans, and sodas the user has purchased
lottery_amount, apple_amount, canned_beans_amount, soda_amount = 0, 0, 0, 0



print("--------------------------------------------------------------------")
# Print the welcome message and show a list of products and their prices
print("Welcome!  Here's what we have in stock:\n"
      "- Lottery ticket: ${} each\n"
      "- Apples: ${} each\n"
      "- Cans of beans: ${} each\n"
      "- Sodas: ${} each".format(constant_lottery_unit_price, constant_apple_unit_price, constant_canned_beans_unit_price, constant_soda_unit_price))


print("--------------------------------------------------------------------")
# Buy Lottery ticket or not
print("You have ${} now.".format(round(money, 2))) # Tell the user how much money he had
# Ask the user if they want to buy the lottery ticket
print("Do you want to buy a $2 lottery ticket for a chance to win $2-$10? ")
answer_lottery = input("Input y or Y (yes) or n or N (no).")
winnings = 0 # Set the initial winnings equal to 0

# Ask if user wants to buy the ticket or not
# User bought the ticket
if answer_lottery == "y" or answer_lottery == "Y":
    print("You bought a lottery ticket! Good luck!")
    lottery_amount = 1 # Bought one lottery ticket
    # The cost of purchasing the lottery ticket
    lottery_spent = lottery_amount * constant_lottery_unit_price
    # The possibility of win the lottery ticket
    possibility = random.randint(0, 2)
    # If user wins the ticket
    if possibility == 1:
        # Randomly generated lottery amounts
        winnings = random.randint(2, 10)
        # Money left
        money_left = money - constant_lottery_unit_price + winnings
        print("Congrats! You won ${}. Keep Shopping!".format(winnings))
    # If user doesn't win the ticket
    else:
        # Money left
        money_left = money - constant_lottery_unit_price
        lottery_amount = 1
        print("Sorry, you didn't win the lottery. Keep Shopping!")

# User didn't buy the ticket
else:
    money_left = money
    lottery_amount = 0
    print("No lottery tickets were purchased. Keep Shopping!")



print("--------------------------------------------------------------------")
# Ask if user wants to buy the apple(s) or not
print("Now you have ${}. ".format(round(money_left, 2)))
print("Do you want to buy apples?")
answer_apple = input("Input y or Y (yes) or n or N (no).")
apple_spent = 0

try:
    # If user wants to buy apple(s)
    if answer_apple == "y" or answer_apple == "Y":
        # Let the user's input must be an integer
        number_of_apples = int(input("How many apple(s) you want to buy?"))
        # The cost of purchasing apple(s)
        apple_spent = number_of_apples * constant_apple_unit_price
        # Money supposed to be left
        money_left_supposed = money_left - apple_spent
        print("You're going to buy {} apple(s) which will cost ${}.".format(number_of_apples, round(apple_spent, 2)))
        # If user has enough money to buy apple(s)
        if money_left_supposed >= 0:
            # Money left
            money_left = money_left_supposed
            # The amount of apple(s) that user bought
            apple_amount = number_of_apples
            print("You have enough money. {} apple(s) purchased.".format(apple_amount))
        # User doesn't have enough money to buy apple(s)
        else:
            print("Not enough money! No apples are selected.")
    # If user doesn't want to buy apple(s)
    else:
        print("No apples were purchased. Keep Shopping!")

# Report the error if the input of the number of apple(s) is not an integer
except ValueError as e:
    print("Sorry, your input is not acceptable. The input should be an integer.")
    print("No apples are selected.")
    print(e)  # print actual error



print("--------------------------------------------------------------------")
# Ask if user wants to buy can(s) of beans or not
print("Now you have ${}. Do you want to buy cans of beans?".format(round(money_left,2)))
answer_canned_beans = input("Input y or Y (yes) or n or N (no).") # Wait for the user's input
canned_beans_spent = 0

try:
    # If user wants to buy can(s) of beans
    if answer_canned_beans == "y" or answer_canned_beans == "Y":
        # Ask user the number of can(s) of beans he wants to buy
        number_of_canned_beans = int(input("How many can(s) of beans you want to buy?"))
        # The cost of can(s) of beans
        canned_beans_spent = number_of_canned_beans * constant_canned_beans_unit_price
        # Money supposed to be left
        money_left_supposed = money_left - canned_beans_spent
        print("You're going to buy {} can(s) of beans which will cost ${}.".format(number_of_canned_beans, round(canned_beans_spent, 2)))
        # If user has enough money to buy can(s) of beans
        if money_left_supposed >= 0:
            # Money left
            money_left = money_left_supposed
            # The amount of can(s) of beans that user bought
            canned_beans_amount = number_of_canned_beans
            print("You have enough money. {} can(s) of beans purchased.".format(canned_beans_amount))
        # If user has not enough money to buy can(s) of beans
        else:
            print("Not enough money! No can(s) of beans are selected.")
    # If user doesn't want to buy can(s) of beans
    else:
        print("No cans of beans were purchased. Keep Shopping!")

# Report the error if the input of the number of can(s) of beans is not an integer
except ValueError as e:
    print("Sorry, your input is not acceptable. The input should be an integer.")
    print("No can(s) of beans are selected.")
    print(e)  # print actual error

# Sodas
print("--------------------------------------------------------------------")
# Ask if user wants to buy sodas or not
print("Now you have ${}. Do you want to buy sodas?".format(round(money_left, 2)))
answer_soda = input("Input y or Y (yes) or n or N (no).")

try:
    # If user wants to buy soda(s)
    if answer_soda == "y" or answer_soda == "Y":
        number_of_soda = int(input("How many soda(s) you want to buy?"))
        soda_spent = number_of_soda * constant_soda_unit_price
        # Money supposed to be left
        money_left_supposed = money_left - soda_spent
        print("You're going to buy {} soda(s) which will cost ${}.".format(number_of_soda, round(soda_spent, 2)))
        # If user has enough money to buy soda(s)
        if money_left_supposed >= 0:
            # Money left
            money_left = money_left_supposed
            # The amount of soda(s) that user bought
            soda_amount = number_of_soda
            print("You have enough money. {} soda(s) purchased.\n".format(soda_amount))
        # If user has not enough money to buy soda(s)
        else:
            print("Not enough money! No soda(s) are selected.\n")
    # If user doesn't want to buy soda(s)
    else:
        print("No sodas were purchased.\n")

# Report the error if the input of the soda(s) is not an integer
except ValueError as e:
    print("Sorry, your input is not acceptable. The input should be an integer.")
    print("No soda(s) are selected.\n")
    print(e)  # print actual error

time.sleep(2) # Sleep/Wait for 2 seconds

# Tell the user how much money he has left and the amount of each item he bought
print("################## Shopping List ##################")
# money left
print("Money left: ${}".format(round(money_left, 2)))
# number of lottery tickets purchased
print("Number of lottery tickets purchased: {}".format(lottery_amount))
# amount of lottery winnings
print("Amount of lottery winnings: ${}".format(winnings))
# number of apple(s) purchased
print("Number of apple(s) purchased: {}".format(apple_amount))
# number of can(s) of beans purchased
print("Number of can(s) of beans purchased: {}".format(canned_beans_amount))
# number of soda(s) purchased
print("Number of soda(s) purchased: {}".format(soda_amount))

print("###################################################\n")

print("Thank you for your shopping. Have a great day!")