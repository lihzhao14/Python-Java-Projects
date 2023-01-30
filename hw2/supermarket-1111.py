# import the random module
# use "winnings = random.randint(2, 10)" to generate a random int from 2 - 10 and store in a variable "winnings"
import random

# unit price of a lottery ticket
constant_lottery_unit_price = 2

# unit price of an apple
constant_apple_unit_price = .99

# unit price of a can of beans
constant_canned_beans_unit_price = 1.58

# unit price of a soda
constant_soda_unit_price = 1.23

# the user has initial $5 for shopping
money = 5.00

# the user has spent $0 initially
money_spent = 0

# the amounts of lottery tickets, apples, cans of beans, and sodas the user has purchased
lottery_amount, apple_amount, canned_beans_amount, soda_amount = 0, 0, 0, 0

# Show the welcome message to the user along with a list of products and their unit prices.
print("Welcome to the supermarket!  Here's what we have in stock:\n"
      "- Lottery ticket: ${} each\n"
      "- Apples: ${} each\n"
      "- Cans of beans: ${} each\n"
      "- Sodas: ${} each\n".format(constant_lottery_unit_price, constant_apple_unit_price, constant_canned_beans_unit_price, constant_soda_unit_price))

# Lottery ticket
print("--------------------------------------------------------------------")
print("You have ${} now.".format(money))
print("Do you want to buy a $2 lottery ticket for a chance to win $2-$10? ")
answer_lottery = input("Input y (yes) or n (no).")
winnings = 0

if answer_lottery == "y":
    print("You bought a lottery ticket. Good luck!")
    lottery_amount = 1
    lottery_spent = lottery_amount * constant_lottery_unit_price
    # the percentage of winning
    possibility = random.randint(0, 2)
    if possibility == 1:
        winnings = random.randint(2, 10)
        money_left = money - constant_lottery_unit_price + winnings
        print("Congrats, you win! You win ${}. Keep Shopping!".format(winnings))
    else:
        money_left = money - constant_lottery_unit_price
        lottery_amount = 1
        print("Sorry, you didn't win the lottery. Keep Shopping!")
else:
    money_left = money
    lottery_amount = 0
    print("No lottery tickets were purchased. Keep Shopping!")


# Apple
print("--------------------------------------------------------------------")
print("Now you have ${}. ".format(round(money_left, 2)))
print("Do you want to buy apples?")
answer_apple = input("Input y (yes) or n (no).")
apple_spent = 0

try:
    if answer_apple == "y":
        number_of_apples = float(input("How many apple(s) you want to buy?"))
        print(number_of_apples)
        if not isinstance(number_of_apples, int):
        #if type(number_of_apples) != int:
            print("your input is not an integer, please enter an integer value")
            number_of_apples = float(input("How many apple(s) you want to buy?"))

        else:
            apple_spent = number_of_apples * constant_apple_unit_price
            money_left_supposed = money_left - apple_spent
            print("You're going to buy {} apple(s) which will cost ${}".format(number_of_apples, round(apple_spent, 2)))
            if money_left_supposed >= 0:
                money_left = money_left_supposed
                apple_amount = number_of_apples
                print("You have enough money. {} apple(s) purchased.".format(apple_amount))
            else:
                print("Not enough money! No apples are selected.")
    else:
        print("No apples were purchased. Keep Shopping!")

except ValueError as e:
    print("Sorry, your input is not acceptable. The input should be an integer.")
    print("No apples are selected.")
    print(e)  # print actual error


# Cans of beans
print("--------------------------------------------------------------------")
print("Now you have ${}. Do you want to buy cans of beans?".format(round(money_left,2)))
answer_canned_beans = input("Input y (yes) or n (no).")
canned_beans_spent = 0

try:
    if answer_canned_beans == "y":
        number_of_canned_beans = int(input("How many can(s) of beans you want to buy?"))
        canned_beans_spent = number_of_canned_beans * constant_canned_beans_unit_price
        money_left_supposed = money_left - canned_beans_spent
        print("You're going to buy {} can(s) of beans which will cost ${}".format(number_of_canned_beans, round(canned_beans_spent, 2)))
        if money_left_supposed >= 0:
            money_left = money_left_supposed
            canned_beans_amount = number_of_canned_beans
            print("You have enough money. {} can(s) of beans purchased.".format(canned_beans_amount))
        else:
            print("Not enough money! No can(s) of beans are selected.")
    else:
        print("No cans of beans were purchased. Keep Shopping!")

except ValueError as e:
    print("Sorry, your input is not acceptable. The input should be an integer.")
    print("No can(s) of beans are selected.")
    print(e)  # print actual error

# Sodas
print("--------------------------------------------------------------------")
print("Now you have ${}. Do you want to buy sodas?".format(round(money_left, 2)))
answer_soda = input("Input y (yes) or n (no).")

try:
    if answer_soda == "y":
        number_of_soda = int(input("How many sodas you want to buy?"))
        soda_spent = number_of_soda * constant_soda_unit_price
        money_left_supposed = money_left - soda_spent
        print("You're going to buy {} soda(s) which will cost ${}".format(number_of_soda, round(soda_spent, 2)))
        if money_left_supposed >= 0:
            money_left = money_left_supposed
            soda_amount = number_of_soda
            print("You have enough money. {} soda(s) purchased.\n".format(soda_amount))
        else:
            print("Not enough money! No soda(s) are selected.\n")
    else:
        print("No sodas were purchased.\n")

except ValueError as e:
    print("Sorry, your input is not acceptable. The input should be an integer.")
    print("No soda(s) are selected.\n")
    print(e)  # print actual error


print("################## Shopping List ##################")
# money_spent
# money_spent = lottery_spent + apple_spent + canned_beans_spent + soda_spent
# print("Money spent: $"+str(money_spent))

# money left
print("Now, you have ${}.".format(round(money_left, 2)))
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

print("###################################################")