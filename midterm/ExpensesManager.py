def add_expense(expenses, expense_type, value):
    """
    Adds the given expense type and value to the given expenses dictionary.

    If the expense type already exists, add the value to the total amount.
    Otherwise, creates a new expense type with the value.

    Prints the expense amount.
    This method doesn’t return anything.
    """
    # check if expense type is in expenses
    if expense_type in expenses:
        expenses[expense_type] += value
    else:
        expenses.update({expense_type: value})
    print("Expense Amount: {:.2f}".format(expenses[expense_type]))


def deduct_expense(expenses, expense_type, value):
    """
    Deducts the given value from the given expense type in the given expenses dictionary.

    Prints a friendly message if the expense type doesn't exist.  Note: Printing a friendly
    message means that the program should not raise an error or otherwise terminate. Simply tell
    the user that the requested expense type does not exist and continue the program.

    Raises a RuntimeError if the value is greater than the existing total of the expense type.
    If runtime error is not raised, prints the expense amount.
    This method doesn’t return anything.
    """

    # Deducts the given value from the given expense type in the given expenses dictionary

    if expense_type in expenses:
        expenses[expense_type] -= value
        if value > sum(expenses.values()):
            raise RuntimeError
        else:
            print("Updated Expense Amount: {:.2f}".format(expenses[expense_type]))
    else:
        print("Sorry, Expense Type doesn't exist.")


def update_expense(expenses, expense_type, value):
    """
    Updates the given expense type with the given value in the given expenses dictionary.

    Prints a friendly message if the expense type doesn't exist.
    Note: Printing a friendly message means that the program should not raise an error or otherwise terminate.
    Simply tell the user that the requested expense type does not exist and continue the program.
    Prints the expense amount.
    This method doesn’t return anything.
    """

    if expense_type in expenses:
        expenses.update({expense_type: value})
        print("Updated Expense Amount: {:.2f}".format(expenses[expense_type]))
    else:
        print("Sorry, Expense Type doesn't exist")


def sort_expenses(expenses, sorting):
    """
    Converts the key:value pairs in the given expenses dictionary to a list of tuples and
    sorts based on the given sorting argument.

    Returns the list of sorted items.

    If the sorting argument is the string ‘expense_type’, sorts the list of tuples based on the
    expense type (e.g. ‘rent’) in ascending alphabetical order,
    e.g. sorted results: ("coffee", 5), ("food", 5000), ("rent", 1000)

    Otherwise, if the sorting argument is ‘amount’, sorts the list of tuples based on the total
    expense amount (e.g. 825) in descending order,
    e.g. sorted results: ("food", 5000), ("rent", 1000), ("coffee", 5)

    Note: If the given sorting argument is not an acceptable value (e.g. ‘expense_type’ or 'amount'),
    this method does nothing except print a friendly message and return None.
    """

    # convert dictionary to list of tuples
    expenses_list = list(expenses.items())

    if sorting == "expense_type":
        expenses_list = sorted(expenses_list, key=lambda x: x[0])
        return expenses_list
    elif sorting == "amount":
        expenses_list = sorted(expenses_list, key=lambda x: x[1], reverse=True)
        return expenses_list
    else:
        print("Unacceptable Value")
        return None


def export_expenses(expenses, expense_types, file):
    """
    Exports the given expense types from the given expenses dictionary to the given file.

    Iterates over the given expenses dictionary, filters based on the given expense types (a list of strings),
    and exports to a file.  Skips any expense type in the given list of expense types that doesn't exist.

    If the expenses argument is the dictionary {"food": 5000, "rent": 1000, "coffee": 5, "clothes": 58.92}
    and the expense_types argument is the list of strings ‘coffee, clothes, rent’, exports a file containing:
    coffee: 5
    clothes: 58.92
    rent: 1000

    If the expenses argument is the dictionary {"food": 5000, "rent": 1000, "coffee": 5, "clothes": 58.92}
    and the expense_types argument is the list of strings ‘coffee, clothes, sports’, exports a file containing:
    coffee: 5
    clothes: 58.92

    Note, the specified expense type 'sports' does not exist in the expenses dictionary, so it is ignored.
    If an item is duplicated in the given expense types, don’t worry about it, just export the data as is.
    You should not deduplicate the expense types.
    This method doesn’t return anything.
    """
    lines = []
    for expense_type in expense_types:
        if expense_type in expenses:
            line = expense_type + ": " + str(expenses[expense_type]) + "\n"
            lines.append(line)

    fout = open(file, 'w')
    fout.writelines(lines)
    fout.close()


def get_expense(expenses, expense_type):
    """
    Returns the value for the given expense type in the given expenses dictionary.

    Prints a friendly message and returns None if the expense type doesn't exist. (Note: None is a
    specific keyword in Python of NoneType. You should not return a string “None” from this method.)

    Note: Printing a friendly message means that the program should not raise an error or otherwise
    terminate. Simply tell the user that the requested expense type does not exist and continue the program.
    """

    # TODO insert your code
    if expense_type in expenses:
        print("Expense Amount: {:.2f}".format(expenses[expense_type]))
        return expenses[expense_type]
    else:
        print("Sorry, the Expense Type doesn't exist")
        return None


class ExpensesManager(object):
    """A class for managing expenses in a dictionary.
    """

    def __init__(self):
        pass
