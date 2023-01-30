order_qty = 0
# TODO: Write your code here
customer_name = input("Now serving customer: ")
print("Welcome {}!".format(customer_name))
while True:
    try:
        order_qty = int(input("How many ice creams will you be ordering (1 to 5)?"))
        if order_qty in range(1, 6, 1):
            break
        else:
            print("Wrong Input! Input is not in [1,5]")
            continue
    except ValueError:
        print("Wrong Input! Input is not an integer")
        continue