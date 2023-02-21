import unittest

from make_website import *


class MakeWebsite_Test(unittest.TestCase):

    def test_read_from_file(self):
        test_contents = ["I.M. Student\n",
                         "Courses :- Programming Languages and Techniques, Biomedical image analysis, Software "
                         "Engineering\n",
                         "Projects\n",
                         "CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and "
                         "mapped it to the CancerDetector ontology. Member of the UI design team, designed the "
                         "portfolio builder UI and category search pages UI. Reviewed existing rank order and "
                         "developed new search rank order approach.\n",
                         "Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT "
                         "algorithm (using Matlab)\n",
                         "------------------------------\n",
                         "tonyl@seas.upenn.edu\n"]

        with open("temp.txt", "w") as f:
            f.writelines(test_contents)

        expected_contents = ["I.M. Student\n",
                             "Courses :- Programming Languages and Techniques, Biomedical image analysis, Software "
                             "Engineering\n",
                             "Projects\n",
                             "CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and "
                             "mapped it to the CancerDetector ontology. Member of the UI design team, designed the "
                             "portfolio builder UI and category search pages UI. Reviewed existing rank order and "
                             "developed new search rank order approach.\n",
                             "Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT "
                             "algorithm (using Matlab)\n",
                             "------------------------------\n",
                             "tonyl@seas.upenn.edu\n"]

        test_contents = read_from_file("temp.txt")

        # check if the contents read from temp.txt is a list
        self.assertIsInstance(test_contents, list)

        # check if the content is same as expected contents
        self.assertEqual(test_contents, expected_contents)

    def test_detect_name(self):
        # test1: Test if it is the valid name
        test1 = ["Tony Stark", "This should not appear."]
        result = detect_name(test1)
        self.assertEqual(result, 'Tony Stark')

        # test2: Test if it has the leading or trailing space
        test2 = [" Tony Stark     ", "This should not appear."]
        result = detect_name(test2)
        self.assertEqual(result, 'Tony Stark')

        # test3: Test if it's an empty name
        test3 = ["\n", "Projects", "Courses"]
        result = detect_name(test3)
        self.assertEqual(result, "Invalid Name")

        # test4: Test if it is the invalid name
        # test4_1: Test if the capital letter is in the lower case
        test4_1 = ["tony Stark", "This should not appear."]
        result = detect_name(test4_1)
        self.assertEqual(result, "Invalid Name")
        # test4_2: Test if it contains numbers
        test4_2 = ["Ton12y Stark"]
        result = detect_name(test4_2)
        self.assertEqual(result, "Invalid Name")







    def test_surround_block(self):
        # test text with surrounding h1 tags
        self.assertEqual("<h1>Eagles</h1>", surround_block('h1', 'Eagles'))

        # test text with surrounding h2 tags
        self.assertEqual("<h2>Red Sox</h2>", surround_block('h2', 'Red Sox'))

        # test text with surrounding p tags
        self.assertEqual('<p>Lorem ipsum dolor sit amet, consectetur ' +
                         'adipiscing elit. Sed ac felis sit amet ante porta ' +
                         'hendrerit at at urna.</p>',
                         surround_block('p', 'Lorem ipsum dolor sit amet, consectetur ' +
                                        'adipiscing elit. Sed ac felis sit amet ante porta ' +
                                        'hendrerit at at urna.'))

    def test_create_email_link(self):
        # test email with @ sign
        self.assertEqual(
            '<a href="mailto:lbrandon@wharton.upenn.edu">lbrandon[aT]wharton.upenn.edu</a>',
            create_email_link('lbrandon@wharton.upenn.edu')
        )

        # test email with @ sign
        self.assertEqual(
            '<a href="mailto:krakowsky@outlook.com">krakowsky[aT]outlook.com</a>',
            create_email_link('krakowsky@outlook.com')
        )

        # test email without @ sign
        self.assertEqual(
            '<a href="mailto:lbrandon.at.seas.upenn.edu">lbrandon.at.seas.upenn.edu</a>',
            create_email_link('lbrandon.at.seas.upenn.edu')
        )


if __name__ == '__main__':
    unittest.main()
