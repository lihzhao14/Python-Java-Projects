def ask_yes_or_no(msg):
    while True:
        response = input(msg).lower()
        if response in ['y', 'yes']:
            return True
        elif response in ['n', 'no']:
            return False
        else:
            print("Invalid input. Please enter y or n.")

result = ask_yes_or_no("Do you want to continue? (y/n) ")
print("The result is:", result)