import unittest
from check_bricks import *


class Check_bricks_test(unittest.TestCase):
    def test_check_bricks(self):
        # Here, we use the set_up(length) function as a helper function
        # to get an empty list and one non-empty list
        discard_pile, main_pile = set_up(7)
        copy_of_shuffled_discard = discard_pile.copy()
        main(main_pile, discard_pile)
        self.assertEqual(7 * 26 - 1, len(main_pile))
        self.assertEqual(1, len(discard_pile))
        self.assertCountEqual(copy_of_shuffled_discard, main_pile + discard_pile)


if __name__ == '__main__':
    unittest.main()
