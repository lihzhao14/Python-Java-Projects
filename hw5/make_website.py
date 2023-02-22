# Student Name in Canvas: Lihong Zhao
# Penn ID: 51007389
# Did you do this homework on your own (yes / no): yes
# Resources used outside course materials: None
# Statement: I admit that this assignment was done by me alone without help.

import string


def read_from_file(file_name):
    """
    reads the file and stores it in memory as a list of lines
    """
    with open(file_name, 'r') as fin:
        # reads all lines in the file as a list
        contents = fin.readlines()
    # clear the "\n" for each line
    for i in range(len(contents)):
        contents[i] = contents[i].strip('\n')
    return contents


def detect_name(contents):
    """
    Detect and return the name by extracting the first line.
    """
    # extract the first line which contains the name
    name = contents[0]
    name = name.strip()
    # check if the of name is empty
    if len(name) > 0:
        first_letter = name[0]
    else:
        return "Invalid Name"
    # check if the first letter is the upper case
    if first_letter in string.ascii_letters.upper():
        return name
    else:
        return "Invalid Name"


def detect_email(contents):
    """
    Detect and return the email address by looking for a line that has the ‘@’ character.
    """
    # detect @ in all lines
    at_line = None
    for line in contents:
        line = line.strip()
        if "@" in line:
            at_line = line
            break
    if at_line is None:
        return ""

    at_index = at_line.index("@")

    # check if the last four characters of the email need to be either .com or .edu
    if at_line[-4:] not in [".com", ".edu"]:
        return ""
    # check if the letter after "@" is lower case
    if at_line[at_index + 1] not in "abcdefghijklmnopqrstuvwxyz":
        return ""
    # check if there is no digits or numbers in the email address
    for i in range(len(at_line)):
        if at_line[i] not in "abcdefghijklmnopqrstuvwxyz@.":
            return ""
    return at_line


def detect_course(contents):
    """
    Detect and return the courses as a list by looking for the word “Courses” in the list and then extract the line
    that contains that word.
    """
    courses_list = ""
    # Extract the line that contains "Course"
    for line in contents:
        if "Course" in line:
            courses_list = line.strip()
            break

    start_index = len("Courses")
    if courses_list:
        # any random punctuation after the word “Courses” and before the first actual course needs to be ignored
        while courses_list[start_index] not in string.ascii_letters:
            start_index += 1
        # start from the first letter without punctuations
        courses_list = courses_list[start_index:]
        # split courses by ","
        split_courses = courses_list.split(",")
        # clear the leading or trailing whitespace
        split_courses = [course.strip() for course in split_courses]
        return split_courses
    else:
        return ""


def detect_projects(contents):
    """
    Detect and return the projects as a list by looking for the word “Projects” in the list.
    """
    projects_list = []
    # Give an initial value of start_index and end_index
    start_index = -1
    end_index = len(contents)
    # find the index of the line contains "Projects"
    for idx, line in enumerate(contents):
        line = line.strip()
        if "Projects" in line:
            start_index = idx
            break

    # find the index of the line contains ‘----------’(at least 10 minus signs)
    for idx, line in enumerate(contents):
        line = line.strip()
        if len(line) >= 10 and '-' * 10 in line:
            end_index = idx
            break

    # Combine all projects in a list
    for line in contents[start_index + 1: end_index]:
        line = line.strip()
        if line:
            projects_list.append(line)

    # check if there are projects or not
    if not projects_list:
        return ""
    return projects_list


def surround_block(tag, text):
    """
    Surrounds the given text with the given html tag and returns the string.
    """
    return "<" + tag + ">" + text + "</" + tag + ">"


def surround_list_block(tag, sample_list):
    """
    Surrounds the given list with the given html tag and returns the list.
    """
    # create tags
    tag1 = "<" + tag + ">"
    tag2 = "</" + tag + ">"
    # insert tags in the first line and the last line
    sample_list.insert(0, tag1)
    sample_list.append(tag2)
    return sample_list


def create_email_link(email_address):
    """
    Creates an email link with the given email_address.
    To cut down on spammers harvesting the email address from the webpage,
    displays the email address with [aT] instead of @.

    Example: Given the email address: lbrandon@wharton.upenn.edu
    Generates the email link: <a href="mailto:lbrandon@wharton.upenn.edu">lbrandon[aT]wharton.upenn.edu</a>

    Note: If, for some reason the email address does not contain @,
    use the email address as is and don't replace anything.
    """
    if "@" in email_address:
        username, domain = email_address.split("@")
        formatted_email = "<a href=\"mailto:" + email_address + "\">" + username + "[aT]" + domain + "</a>"
        return formatted_email
    else:
        formatted_email = "<a href=\"mailto:" + email_address + "\">" + email_address + "</a>"
        return formatted_email


def generate_html(txt_input_file, html_output_file):
    """
    Loads given txt_input_file,
    gets the name, email address, list of projects, and list of courses,
    then writes the info to the given html_output_file.
    """

    # call read_from_file function to load given txt_input_file
    contents = read_from_file(txt_input_file)
    # call read_from_file function to load resume_template.html
    with open("resume_template.html", 'r') as fin:
        # reads all lines in the file as a list
        template_contents = fin.readlines()
    # template_contents = read_from_file("resume_template.html")
    # Remove the last 2 lines of HTML (the </body> and </html> lines).
    del template_contents[-2:]

    # call functions to get name, email address, list of projects and list of courses
    dict_resume_contents = {
        "Name": detect_name(contents),
        "Email": detect_email(contents),
        "Projects": detect_projects(contents),
        "Courses": detect_course(contents)
    }

    # set <div id="page-wrap"> in the 1st line and </div> in the following line
    initial_div = ["<div id=\"page-wrap\">"]

    # basic information section
    # name
    basic_section = [surround_block("h1", dict_resume_contents["Name"])]
    # email
    email = "Email: " + create_email_link(dict_resume_contents["Email"])
    basic_section.append(surround_block("p", email))
    # insert <div> and </div>
    basic_section = surround_list_block("div", basic_section)

    # projects section
    projects_section = [surround_block("h2", "Projects")]
    projects_section_1 = []
    for i in range(len(dict_resume_contents["Projects"])):
        projects_section_1.append(surround_block("li", dict_resume_contents["Projects"][i]))
    # insert <ul> and </ul>
    projects_section_1 = surround_list_block("ul", projects_section_1)
    # Generate the complete projects section
    projects_section.extend(projects_section_1)
    # insert <div> and </div>
    projects_section = surround_list_block("div", projects_section)

    # courses section
    courses_section = [surround_block("h3", "Courses")]
    # Covert the list of courses to a string
    courses_string = ", ".join(dict_resume_contents["Courses"])
    courses_section.append(surround_block("span", courses_string))
    courses_section = surround_list_block("div", courses_section)

    # Combine all the resume contents together in a list, also add "</div>", "</body>", "</html>"
    list_resume_contents = initial_div + basic_section + projects_section + courses_section + ["</div>", "</body>", "</html>"]
    # add "\n" for each line
    resume_contents = [line + '\n' for line in list_resume_contents]

    template_contents.extend(resume_contents)

    # html_output_file Create a new file
    fout = open(html_output_file, 'w')
    # Write the final HTML to a new file resume.html
    fout.writelines(template_contents)
    # close file
    fout.close()


def main():
    # DO NOT REMOVE OR UPDATE THIS CODE
    # generate resume.html file from provided sample resume.txt
    generate_html('resume.txt', 'resume.html')

    # DO NOT REMOVE OR UPDATE THIS CODE.
    # Uncomment each call to the generate_html function when you’re ready
    # to test how your program handles each additional test resume.txt file
    generate_html('TestResumes/resume_bad_name_lowercase/resume.txt',
                  'TestResumes/resume_bad_name_lowercase/resume.html')
    generate_html('TestResumes/resume_courses_w_whitespace/resume.txt',
                  'TestResumes/resume_courses_w_whitespace/resume.html')
    generate_html('TestResumes/resume_courses_weird_punc/resume.txt',
                  'TestResumes/resume_courses_weird_punc/resume.html')
    generate_html('TestResumes/resume_projects_w_whitespace/resume.txt',
                  'TestResumes/resume_projects_w_whitespace/resume.html')
    generate_html('TestResumes/resume_projects_with_blanks/resume.txt',
                  'TestResumes/resume_projects_with_blanks/resume.html')
    generate_html('TestResumes/resume_template_email_w_whitespace/resume.txt',
                  'TestResumes/resume_template_email_w_whitespace/resume.html')
    generate_html('TestResumes/resume_wrong_email/resume.txt',
                  'TestResumes/resume_wrong_email/resume.html')

    # If you want to test additional resume files, call the generate_html function with the given .txt file
    # and desired name of output .html file


if __name__ == '__main__':
    main()
