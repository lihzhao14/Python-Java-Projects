import string

courses_list = [
    "   Introduction to Programming with Python ",
    "Web Development with JavaScript",
    "   Data Science with Python ",
    "  Courses: !!!Intro to Python, Advanced Java, SQL Basics",
    "   Algorithms and Data Structures ",
]

# Find the line that contains the word "Courses"
courses_line = None
for line in courses_list:
    if "Courses" in line:
        courses_line = line.strip()  # Remove leading/trailing whitespace
        break

# Extract the course names from the line
if courses_line:
    start_index = courses_line.find("Courses") + len("Courses")
    while start_index < len(courses_line) and courses_line[start_index] not in string.ascii_letters:
        start_index += 1
    end_index = start_index
    while end_index < len(courses_line) and courses_line[end_index] in string.ascii_letters:
        end_index += 1
    course_names = courses_line[start_index:end_index].split(", ")
    print(course_names)
else:
    print("No courses found")
