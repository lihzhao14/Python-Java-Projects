import unittest

from make_website import *


class MakeWebsite_Test(unittest.TestCase):

    def test_read_from_file(self):
        # test_contents = ["I.M. Student\n",
        #             "Courses :- Programming Languages and Techniques, Biomedical image analysis, Software Engineering\n",
        #             "Projects\n",
        #             "CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and category search pages UI. Reviewed existing rank order and developed new search rank order approach.\n",
        #             "Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm (using Matlab)\n",
        #             "------------------------------\n",
        #             "tonyl@seas.upenn.edu\n"]

        # with open("temp.txt", "w") as f:
        #     f.writelines(test_contents)
            # f.write("I.M. Student\n")
            # f.write("Courses :- Programming Languages and Techniques, Biomedical image analysis, Software Engineering\n")
            # f.write("Projects\n")
            # f.write("CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and category search pages UI. Reviewed existing rank order and developed new search rank order approach.\n")
            # f.write("Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm (using Matlab)\n")
            # f.write("------------------------------\n")
            # f.write("tonyl@seas.upenn.edu\n")

        contents = read_from_file("resume.txt")


        # check if the contents read from temp.txt is a list
        self.assertIsInstance(contents, list)

        expected = ["I.M. Student\n",
                    "Courses :- Programming Languages and Techniques, Biomedical image analysis, Software Engineering\n",
                    "Projects\n",
                    "CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and category search pages UI. Reviewed existing rank order and developed new search rank order approach.\n",
                    "Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm (using Matlab)\n",
                    "------------------------------\n",
                    "tonyl@seas.upenn.edu\n"]

        # check if the content is same as expected contents
        self.assertEqual(contents, expected)



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
