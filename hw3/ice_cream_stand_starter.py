"""
STARTER CODE
Homework 3: Ice Cream Stand
Topics Covered:
- Lists (append, pop)
- For and while loops
- Getting user inputs
- Validating user inputs
- Functions and helper functions
- Formatted Strings
"""

# TODO: Students, fill out statement of work header
# Student Name in Canvas: Lihong Zhao
# Penn ID: 51007389
# Did you do this homework on your own (yes / no): yes
# Resources used outside course materials: None
# Statement: I admit that this assignment was done by me alone without help.

# import statements
# from random import randint, choice
import random


def print_welcome_and_menu(list_of_flavors, list_of_sizes, list_of_prices):
    """
    Prints the following:
    1. Welcome message (Must contain word 'welcome')
    2. Message on what flavors are available in the ice cream store.
        Hint: Loop through the list_of_flavors
    3. Message on how much each size cost.
        Hint: Loop through the list_of_sizes, list_of_prices
        Format should be: Our {size} ice cream is ${price}.
    """
    # list_of_flavors = ["Vanilla", "Chocolate", "Strawberry"]
    # list_of_sizes = ["Small", "Medium", "Large"]
    # list_of_prices = [4.99, 7.49, 8.49]

    print("Welcome to Penn's Student Run Ice Cream Stand!\n")
    print("Our current flavors for today are:")
    for flavor in list_of_flavors:
        print(flavor)

    print("")

    for idx in range(len(list_of_sizes)):
        print("Our {} ice cream is ${}".format(list_of_sizes[idx], list_of_prices[idx]))

    print("")


def get_order_qty(customer_name):
    """
    Ask the customer how many orders of ice cream they want.
    Valid order quantity should be an integer 1-5 inclusive. If outside the range or non-int, re-prompt.
    Hint: When asking for user input, cast it to an integer. If the input cannot be cast-ed to an integer, re-prompt.
    "2.55", "abc", "   ", are a few examples of what should all re-prompt the user.
    Returns: How many orders of ice cream the customer wants.
    """
    order_qty = 0
    # TODO: Write your code here
    print("Welcome {}!".format(customer_name))
    while True:
        try:
            order_qty = int(input("How many ice creams will you be ordering (1 to 5)?"))
            if order_qty in range(1, 6, 1):
                break
            else:
                print("Please enter a valid integer in [1,5]")
                # continue
        except ValueError:
            print("Please enter a valid integer")
            # continue
    return order_qty


def get_ice_cream_flavor(ice_cream_flavors):
    """
    Ask the customer 'Which flavor would you like (v/c/s)? '
    Then, processes and cleans the input and returns the equivalent flavor from ice_cream_flavors list.
    Hint:   Use the indices set in the main function for the flavors.
            Call the get_first_letter_of_user_input function to get and process inputs.
            Note: Only the first letter of the input will be considered so an input of 'Cookies and Cream'
            will be considered as 'c' which corresponds to 'Chocolate'.
            Ask again if it is not a valid flavor.
    Returns: String of ice cream flavor picked (e.g "Vanilla")
    """
    flavor_picked = ""
    # TODO: Write your code here
    flag = True
    while flag:
        flavor_asked = input("Which flavor would you like (v/c/s)? ")
        first_letter_flavor = get_first_letter_of_user_input(flavor_asked)
        for idx in range(len(ice_cream_flavors)):
            if first_letter_flavor == get_first_letter_of_user_input(ice_cream_flavors[idx]):
                flavor_picked = ice_cream_flavors[idx]
                flag = False

    return flavor_picked


def get_ice_cream_size(ice_cream_sizes):
    """
    Ask the customer 'Which size would you like (s/m/l)? '
    Then, processes and cleans the input and returns the equivalent size from ice_cream_sizes list.
    Hint:   Use the indices set in the main function for the sizes.
            Call the get_first_letter_of_user_input function to get and process inputs.
            Note: Only the first letter of the input will be considered so an input of 'Super Large'
            will be considered as 's' which corresponds to 'Small'.
            Ask again if it is not a valid size.
    Returns: String of Size picked (e.g "Small")
    """
    size_picked = ""
    # TODO: Write your code here
    flag = True
    while flag:
        size_asked = input("Which size would you like (s/m/l)? ")
        first_letter_size = get_first_letter_of_user_input(size_asked)
        for idx in range(len(ice_cream_sizes)):
            if first_letter_size == get_first_letter_of_user_input(ice_cream_sizes[idx]):
                size_picked += ice_cream_sizes[idx]
                flag = False

    return size_picked


def get_ice_cream_order_price(ice_cream_size, ice_cream_prices, ice_cream_sizes):
    """
    Hint:   Use the indices set in the main function for the prices of Small, Medium and Large.
    Returns: The equivalent price of an ice cream size. Example: Returns 4.99 if ice_cream_size is 'Small'
    """
    # TODO: Write your code here

    index = ice_cream_sizes.index(ice_cream_size)
    return ice_cream_prices[index]


def take_customer_order(customer_name, ice_cream_flavors, ice_cream_sizes, ice_cream_prices):
    """
    This function runs when a customer reaches the front of the queue. It should print
    the current customer's name being served, and take their order(s).
    If the customer can pay for their order, returns the amount of revenue from the sale.
    If the customer cancels their order, returns 0.
    Hint: Use other helper functions we required you to write whenever needed here.
    Returns: Amount of Revenue from the sale with customer
    """

    total_bill = 0

    # TODO: Print a message "Now serving customer: X" where X is the current customer's name
    print("Now serving customer: {}".format(customer_name))

    # TODO: Call the get_order_qty and save the value to order_qty
    order_qty = get_order_qty(customer_name)

    # TODO: For Each order you need to get a flavor, and size
    for order in range(order_qty):
        print("Order No.:", order + 1)
        # TODO: Write code to get the ice cream flavor for this order
        flavor_picked = get_ice_cream_flavor(ice_cream_flavors)
        # TODO: Write code to get the ice cream size for this order
        size_picked = get_ice_cream_size(ice_cream_sizes)
        # TODO: Write code to get the price for this order
        price_of_order = get_ice_cream_order_price(size_picked, ice_cream_prices, ice_cream_sizes)
        # TODO: Update the total_bill
        total_bill += price_of_order
        # TODO: Print the details for this order
        #   Hint: See https://www.w3schools.com/python/python_string_formatting.asp for string formatting examples on rounding to 2 decimal places
        print("You ordered a {} {} for ${:.2f}".format(size_picked, flavor_picked, price_of_order))

    # TODO: Print the customer's total_bill
    print("Your total bill is: ${}".format(total_bill, 2))
    # TODO: Once orders are all taken, the customer should be asked if they still want to Pay or Cancel
    #  "Would you like to pay or cancel the order (p/c)? "
    #   Hint: Use the get_first_letter_of_user_input() Re-prompt if answer does not start with 'p' or 'c'
    process = True
    while process:
        pay_or_cancel = input("Would you like to pay or cancel the order (p/c)?")
        first_letter_pay_or_cancel = get_first_letter_of_user_input(pay_or_cancel)
        if first_letter_pay_or_cancel == "p":
            process = False
        elif first_letter_pay_or_cancel == "c":
            total_bill = 0
            process = False

    return total_bill


def get_first_letter_of_user_input(question):
    """
    Takes in a string as its argument, to be used as the question you want the user to be asked.
    Gets input from the user, removes whitespace and makes all letters lowercase
    Hint: Use the strip() and lower() functions
    Returns: The first letter of the input the user provides. Ask again if the input is empty.
    """
    first_letter = ""
    # TODO: Write your code here
    question = question.strip()
    question = question.lower()
    if question == "":
        question = "empty"
    first_letter += question[0]
    return first_letter


def are_all_customers_served(customer_queue_length):
    """
    If there are no customers in the queue, returns True, and all customers have been served.
    Otherwise, returns False.
    Returns: True or False
    """
    # TODO: Write your code here
    if customer_queue_length == 0:
        return True
    return False


def print_current_status(customers_served, tracking_revenue):
    """
    Prints a message of how many customers have been served and the total sales of the ice cream stand.
    Hint: See https://www.w3schools.com/python/python_string_formatting.asp for string formatting examples on rounding to 2 decimal places
    No Return, only print statements
    """
    # TODO: Write your code here
    print("")
    print("We have now served {} customer(s) and received ${:.2f} in revenue\n".format(customers_served, tracking_revenue))


def print_sales_summary(customers_served, tracking_revenue):
    """
    Takes in the arguments customers_served and tracking_revenue. Prints both
    arguments as strings to let the user know what those values are.
    Output should look something like:
        Total customers served: 3
        Total sales           : $xx.xx
    Hint: See https://www.w3schools.com/python/python_string_formatting.asp for string formatting examples on rounding to 2 decimal places
    No Return, only print statements
    """
    # TODO: Write your code here
    print("Total customers served: {}".format(customers_served))
    print("Total sales           : ${:.2f}".format(tracking_revenue))


def random_queue_length():
    """
    Takes no arguments.
    Uses the imported randint function to generate a random integer between 2 and 5 inclusive.
    Hint: See https://www.w3schools.com/python/ref_random_randint.asp
    Returns: The resulting random integer.
    """
    random_number = random.randint(2, 5)
    return random_number


def main():
    """
    Lists of available flavors, sizes and prices. DO NOT CHANGE.
    For sizes and prices, we will use the following convention:
    Index 0 for Small
    Index 1 for Medium
    Index 2 for Large
    """
    ice_cream_flavors = ['Vanilla', 'Chocolate', 'Strawberry']
    ice_cream_sizes = ['Small', 'Medium', 'Large']
    ice_cream_prices = [4.99, 7.49, 8.49]

    # List of names of possible customers
    customer_names = ["Alice", "Bob", "Charlie", "Dan", "Eve", "Frank", "Grace", "Heidi", "Ivan", "Judy"]

    program_running = True
    while program_running:
        # set shop to open
        input('Press any key to open the shop! ')
        queue_is_open = True

        # TODO: Call the print_welcome_and_menu function with the parameters in the following order -
        #  ice_cream_flavors, ice_cream_sizes, ice_cream_prices
        print_welcome_and_menu(ice_cream_flavors, ice_cream_sizes, ice_cream_prices)

        # set initial values
        tracking_revenue = 0

        # will hold the list of names of the customers in the queue
        customers_in_queue = []
        customers_served = 0

        # TODO: Call the random_queue_length function and save the result to num_of_customers_in_queue
        # num_of_customers_in_queue = 0
        num_of_customers_in_queue = random_queue_length()

        # TODO: Print how many customers are in the queue
        print("Num of customers in queue: {}\n".format(num_of_customers_in_queue))

        # TODO: Call the imported choice function to generate a random name from customer_names.
        # The total number of customer names added should be equal to num_of_customers_in_queue
        for num in range(num_of_customers_in_queue):
            # random_customer_name = []
            random_customer_name = random.choice(customer_names)
            # Append each name to the end of the customers_in_queue list.
            customers_in_queue.append(random_customer_name)

        #   Hint: See https://www.w3schools.com/python/ref_random_choice.asp
        #   Note: It is OK to have duplicate names in the queue.

        while queue_is_open:
            # TODO: Extract the first customer (index 0) from the customers_in_queue and save it to
            #  the current_customer_name variable.
            #  After extraction, the customer should now be removed from the customers_in_queue list.
            #  Hint: Use the pop function with an index argument
            current_customer_name = ""
            current_customer_name += customers_in_queue[0]
            customers_in_queue.pop(0)

            # TODO: Take a customer at the window and update the revenue by calling the take_customer_order function
            current_revenue = take_customer_order(current_customer_name, ice_cream_flavors, ice_cream_sizes,
                                                  ice_cream_prices)
            # Tracking the total revenue
            tracking_revenue += current_revenue
            # TODO: Update the customers_served variable
            customers_served += 1
            # TODO: Call the print_current_status
            print_current_status(customers_served, tracking_revenue)
            # TODO: Call the are_all_customers_served(customer_queue_length) function to check if there are any more
            #  customers in the queue.
            #  If False, continue the loop.
            #  If True, call the print_sales_summary(customers_served, tracking_revenue) and close the queue
            if are_all_customers_served((num_of_customers_in_queue - customers_served)):
                print_sales_summary(customers_served, tracking_revenue)
                queue_is_open = False

            # else:
            #     continue

        # TODO: Ask if you want to open the ice cream stand again "Do you want to open again (y/n)? "
        #  Hint: Use the get_first_letter_of_user_input function
        #  Update the program_running variable if you get a valid answer either 'y' or 'n'
        #  Otherwise, re-prompt until a valid answer is given
        open_or_close = True
        while open_or_close:
            open_again = input("Do you want to open again (y/n)? ")
            first_letter_open_again = get_first_letter_of_user_input(open_again)
            if first_letter_open_again == "y":
                program_running = True
                open_or_close = False
            elif first_letter_open_again == "n":
                program_running = False
                open_or_close = False


if __name__ == '__main__':
    main()
