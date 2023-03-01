class ExpensesLoader(object):
    """A class for loading expenses from a file.
    """

    def __init__(self):
        pass

    def import_expenses(self, expenses, file):
        """
        Reads data from the given file and stores the expenses in the given expenses dictionary,
        where the expense type is the key and the total expense amount for that expense is the value.

        The same expense type may appear multiple times in the given file.
        Ignores expenses with missing or invalid amounts. If a line contains both an expense type and an expense amount,
        they will be separated by a colon (:).

        This method doesn’t return anything.  Rather, it updates the given expenses dictionary based
        on the expenses in the given file.

        Whitespace should be removed and blank lines should be ignored.

        Note: This method will be called twice in the main function in expenses.py with the same dictionary but different files.
        """
        # open file
        f = open(file, 'r')
        lines = f.readlines()

        for line in lines:

            line = line.strip()

            # ignore blank lines
            if not line:
                continue

            # split line into list of values (expense type and total expense amount)
            lst = line.split(':')

            # ignore expenses with missing amounts
            if len(lst) <= 1:
                continue

            # extract expense type
            key = lst[0].strip()

            # extract total expense amount
            value = lst[1].strip()

            try:
                # cast expense amount to float
                value = float(value)

                # add new total expense amount to dictionary with expense type as key
                expenses[key] = expenses.get(key, 0) + value  # get the current value, then add value
                # if there's no current value, start with 0 then add the value

            except:
                # go to next line if the value is not a valid number
                continue
