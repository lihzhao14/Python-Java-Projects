# Student Name in Canvas: Lihong Zhao
# Penn ID: 51007389
# Did you do this homework on your own (yes / no): yes
# Resources used outside course materials: None
# Statement: I admit that this assignment was done by me alone without help.

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

        expected_contents = ["I.M. Student",
                             "Courses :- Programming Languages and Techniques, Biomedical image analysis, Software "
                             "Engineering",
                             "Projects",
                             "CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and "
                             "mapped it to the CancerDetector ontology. Member of the UI design team, designed the "
                             "portfolio builder UI and category search pages UI. Reviewed existing rank order and "
                             "developed new search rank order approach.",
                             "Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT "
                             "algorithm (using Matlab)",
                             "------------------------------",
                             "tonyl@seas.upenn.edu"]

        test_contents = read_from_file("temp.txt")

        # check if the contents read from temp.txt is a list
        self.assertIsInstance(test_contents, list)

        # check if the content is same as expected contents
        self.assertEqual(test_contents, expected_contents)

    def test_detect_name(self):
        # test1: Test if it is the valid name having the leading or trailing space
        test1 = ["   Tony Stark   ", "This should not appear."]
        result = detect_name(test1)
        self.assertEqual(result, "Tony Stark")
        print()
        print("The name '{}' is valid".format(result))

        # test2: Test if it starts with a non-alphabetic token
        test2 = ["22ony Stark", "This should not appear."]
        result = detect_name(test2)
        self.assertEqual(result, "Invalid Name")
        print("The name is valid because it starts with a non-alphabetic token")

        # test3: Test if it's an empty name
        test3 = ["\n", "Projects", "Courses"]
        result = detect_name(test3)
        self.assertEqual(result, "Invalid Name")
        print("The name is invalid because it is empty")

        # test4: Test if the capital letter is in the lower case
        test4 = ["tony Stark", "This should not appear."]
        result = detect_name(test4)
        self.assertEqual(result, "Invalid Name")
        print("The name '{}' is invalid because it starts with a lower case letter".format(test4[0]))

    def test_detect_email(self):
        # test1
        test1 = read_from_file("../hw5/TestResumes/resume_template_email_w_whitespace/resume.txt")
        result = detect_email(test1)
        self.assertEqual(result, "lbrandon@wharton.upenn.edu")
        # test2
        test2 = ["lbrandon@wharton2.upenn.com"]
        result = detect_email(test2)
        self.assertEqual(result, "")
        # test3
        test3 = read_from_file("../hw5/TestResumes/resume_wrong_email/resume.txt")
        result = detect_email(test3)
        self.assertEqual(result, "")
        # test4
        test4 = ["lbrandon@Wharton.upenn.com"]
        result = detect_email(test4)
        self.assertEqual(result, "")

    def test_detect_course(self):
        # test1:
        test1 = read_from_file("../hw5/TestResumes/resume_courses_w_whitespace/resume.txt")
        expect = ["Programming Languages and Techniques", "Biomedical image analysis", "Pottery"]
        courses_name = detect_course(test1)
        self.assertEqual(courses_name, expect)
        print()
        print("Detect course test1 passed")

        # test2:
        test2 = read_from_file("../hw5/TestResumes/resume_courses_weird_punc/resume.txt")
        expect = ["Programming Languages and Techniques", "Biomedical image analysis", "Software Engineering"]
        courses_name = detect_course(test2)
        self.assertEqual(courses_name, expect)
        print("Detect course test2 passed")

    def test_detect_projects(self):
        # test1:
        test1 = read_from_file("../hw5/TestResumes/resume_projects_w_whitespace/resume.txt")
        expect = ["CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to "
                  "the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and "
                  "category search pages UI. Reviewed existing rank order and developed new search rank order "
                  "approach.",
                  "Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm ("
                  "using Matlab)"]
        projects_list = detect_projects(test1)
        self.assertEqual(projects_list, expect)
        # test2:
        test2 = read_from_file("../hw5/TestResumes/resume_projects_with_blanks/resume.txt")
        expect = ["CancerDetector.com, New Jersey, USA - Project manager, codified the assessment and mapped it to "
                  "the CancerDetector ontology. Member of the UI design team, designed the portfolio builder UI and "
                  "category search pages UI. Reviewed existing rank order and developed new search rank order "
                  "approach.",
                  "Biomedical Imaging - Developed a semi-automatic image mosaic program based on SIFT algorithm ("
                  "using Matlab)"]
        projects_list = detect_projects(test2)
        self.assertEqual(projects_list, expect)

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

    def test_surround_list_block(self):
        sample_list = ["<h2>Projects</h2>", "<ul>", "<li>built a robot</li>", "<li>fixed an ios app</li>", "</ul>"]
        expect = ["<div>", "<h2>Projects</h2>", "<ul>", "<li>built a robot</li>", "<li>fixed an ios app</li>", "</ul>", "</div>"]
        self.assertEqual(expect, surround_list_block("div", sample_list))

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
