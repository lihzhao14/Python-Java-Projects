import unittest

from ExpensesLoader import *
from ExpensesManager import *

class Expenses_Test(unittest.TestCase):

    def setUp(self):
        """The setUp function runs before every test function."""

        # create expenses dictionary and populate with data
        self.expenses = {'food': 5.0, 'coffee': 12.4, 'rent': 825.0, 'clothes': 45.0,
                         'entertainment': 135.62, 'music': 324.0, 'family': 32.45}

    def test_import_expenses(self):

        # import expenses files
        expenses = {}

        # create instance of ExpensesLoader class
        expenses_loader = ExpensesLoader()

        # call import_expenses method in ExpensesLoader class
        # to import expense files and store in dictionary
        expenses_loader.import_expenses(expenses, 'expenses.txt')
        expenses_loader.import_expenses(expenses, 'expenses_2.txt')

        # test existing total expenses
        self.assertAlmostEqual(45, expenses['clothes'])
        self.assertAlmostEqual(12.40, expenses['coffee'])
        self.assertAlmostEqual(135.62, expenses['entertainment'])
        print(expenses['clothes'])
        print(expenses['coffee'])
        print(expenses['entertainment'])

    def test_get_expense(self):

        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        #test getting expenses based on expense type
        self.assertAlmostEqual(12.40, expenses_manager.get_expense(self.expenses, "coffee"))

        #test non-existing expense types
        self.assertEqual(None, expenses_manager.get_expense(self.expenses, "phone"))

        # TODO insert 2 additional test cases
        #  Hint(s): Test both existing and non-existing expense types

    def test_add_expense(self):

        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        #test adding a new expense
        expenses_manager.add_expense(self.expenses, "fios", 84.5)
        self.assertAlmostEqual(84.5, self.expenses.get("fios"))

        # TODO insert 2 additional test cases
        #  Hint(s): Test adding to existing expenses

    def test_deduct_expense(self):

        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        #test deducting from expense
        expenses_manager.deduct_expense(self.expenses, "coffee", .99)
        self.assertAlmostEqual(11.41, self.expenses.get("coffee"))

        #test deducting from expense
        expenses_manager.deduct_expense(self.expenses, "entertainment", 100)
        self.assertAlmostEqual(35.62, self.expenses.get("entertainment"))

        # TODO insert 2 additional test cases
        #  Hint(s):
        #   Test deducting too much from expense
        #   Test deducting from non-existing expense

    def test_update_expense(self):

        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        #test updating an expense
        expenses_manager.update_expense(self.expenses, "clothes", 19.99)
        self.assertAlmostEqual(19.99, expenses_manager.get_expense(self.expenses, "clothes"))

        # TODO insert 2 additional test cases
        #  Hint(s):
        #   Test updating an expense
        #   Test updating a non-existing expense

    def test_sort_expenses(self):

        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        #test sorting expenses by 'expense_type'
        expense_type_sorted_expenses = [('clothes', 45.0),
                                        ('coffee', 12.40),
                                        ('entertainment', 135.62),
                                        ('family', 32.45),
                                        ('food', 5.0),
                                        ('music', 324.0),
                                        ('rent', 825.0)]

        self.assertListEqual(expense_type_sorted_expenses, expenses_manager.sort_expenses(self.expenses, "expense_type"))

        # TODO insert 1 additional test case
        #   Hint: Test sorting expenses by 'amount'

    def test_export_expenses(self):

        # create instance of ExpensesManager class
        expenses_manager = ExpensesManager()

        #test export with existing expense types
        expenses_manager.export_expenses(self.expenses, ['coffee', 'clothes'], 'export1.txt')

        # TODO insert 1 additional test case
        #   Hint: Test exporting with some non-existent expense types.
        #   Similar to above, it is acceptable to just call export_expenses() using different inputs with a different
        #   file to write to and just visually inspect the results. You are not required to have any assert() statements
        #   in this test function.

if __name__ == '__main__':
    unittest.main()
