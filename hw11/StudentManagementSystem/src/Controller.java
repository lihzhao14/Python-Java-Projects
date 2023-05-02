/**
 * Class Controller
 * 
 * @author Lihong Zhao & Haojie Zheng
 */


import file.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;

import java.util.Scanner;


/**
 * represent a Student Management System
 */
public class Controller {
    Scanner scan = new Scanner(System.in);

//System Starts------------------------------------------------------------------
    /**
     * Entry point of the system.
     * @param str command line arguments
     */
    public static void main(String[] str) {
        Controller system = new Controller();
        FileInfoReader file = new FileInfoReader();
        Student stu = new Student();
        Professor prof = new Professor();
        Admin admin = new Admin();

        while (true) {
            int choice = system.managementSystem();

            switch (choice) {
                case 1:
                    system.loginAsUser(stu, file);
                    break;
                case 2:
                    system.loginAsUser(prof, file);
                    break;
                case 3:
                    system.loginAsUser(admin, file);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
        }
    }
    
    /**
     * Login as a user with the specified user object and file information.
     *
     * @param user the user object to log in as
     * @param file the file information
     */
    private void loginAsUser(User user, FileInfoReader file) {
        String[] np = askNameAndPassWord();
        // to quit
        if (np[0].equals("q") || np[1].equals("q")) return;
        // when user name or password not valid
        User loggedInUser = user.tryLogin(np[0], np[1], file);
        while (loggedInUser == null) {
            System.out.println("Invalid username or password");
            np = askNameAndPassWord();
            // to quit
            if (np[0].equals("q") || np[1].equals("q")) return;
            loggedInUser = user.tryLogin(np[0], np[1], file);
        }
        if (loggedInUser instanceof Student) {
            handleStudentActions((Student) loggedInUser, file);
        } else if (loggedInUser instanceof Professor) {
            handleProfActions((Professor) loggedInUser, file);
        } else if (loggedInUser instanceof Admin) {
            handleAdminActions((Admin) loggedInUser, file);
        }
    }

//Login as a student------------------------------------------------------------
    /**
     * Handles the actions for a logged-in student.
     * @param student the logged-in student
     * @param file the FileInfoReader object
     */
    private void handleStudentActions(Student student, FileInfoReader file) {
        boolean running = true;
        while (running) {
            int option = printStudentInterface(student);
            switch (option) {
                case 1:
                    student.viewAllCourse(file);
                    break;
                case 2:
                    processStudentAddCourse(student, file);
                    break;
                case 3:
                    student.checkCourseSchedule();
                    break;
                case 4:
                    processStudentDropCourse(student, file);
                    break;
                case 5:
                    student.viewGrades(file);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Prompts the student to add a course to their list.
     *
     * @param student the student object
     * @param file    the file reader object
     */
    private void processStudentAddCourse(Student student, FileInfoReader file) {
        String option;
        while (!(option = promptStudentToAddCourse()).equals("q")) {
            student.addCourse(option, file);
        }
    }

    /**
     * Processes a student's request to drop a course.
     * 
     * @param student the student who wants to drop a course
     * @param file    the file containing the student's course information
     */
    private void processStudentDropCourse(Student student, FileInfoReader file) {
        String option;
        while (!(option = promptStudentToDropCourse(student, file)).equals("q")) {
            student.dropCourse(option);
        }
    }

//Login as a professor------------------------------------------------------------
    /**
     * Handles the actions that a professor can perform after logging in.
     *
     * @param professor the professor object
     * @param file 		the file reader object
     */
    private void handleProfActions(Professor professor, FileInfoReader file) {
        boolean exit = false;

        while (!exit) {
            int option = printProfessorInterface(professor);

            switch (option) {
                case 1:
                    // View given courses
                    professor.printCoursesTaught(file);
                    break;
                case 2:
                    // View student list of the given course
                    handleStudentList(professor, file);
                    break;
                case 3:
                    // Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Handles the display of the student list for the given course.
     *
     * @param professor the professor object
     * @param file      the file reader object
     */
    private void handleStudentList(Professor professor, FileInfoReader file) {
        while (true) {
            String courseId = promptProfessorToViewStudentList(professor, file);

            if (courseId.equalsIgnoreCase("q")) {
                break;
            }

            professor.studentInCourse(courseId, file);
        }
    }


//Login as an Admin------------------------------------------------------------
    /**
     * Handles the interaction when the user is logged in as an admin.
     *
     * @param admin the admin object
     * @param file  the file reader object
     */
    private void handleAdminActions(Admin admin, FileInfoReader file) {
        boolean exit = false;

        while (!exit) {
            int option = printAdminInterface();

            switch (option) {
                case 1:
                    // View given courses
                    displayCourseList(admin, file);
                    break;
                case 2:
                    // Add new courses
                	adminAddCourse(admin, file);
                    break;
                case 3:
                    // Delete courses
                    deleteCourse(admin, file);
                    break;
                case 4:
                    // Add new professor
                	adminAddProf(admin, file);
                    break;
                case 5:
                    // Delete professor
                    deleteProfessor(admin, file);
                    break;
                case 6:
                    // Add new student
                	adminAddStudent(admin, file);
                    break;
                case 7:
                    // Delete student
                    deleteStudent(admin, file);
                    break;
                case 8:
                    // Exit
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    /**
     * Displays the list of courses.
     * 
     * @param admin the admin user
     * @param file  the file containing the course information
     */
    private void displayCourseList(Admin admin, FileInfoReader file) {
        System.out.println("--------------The course list----------------");
        admin.viewAllCourse(file);
    }
    
    /**
     * Deletes a course based on the provided course ID.
     * 
     * @param admin the admin user
     * @param file  the file containing the course information
     */
    private void deleteCourse(Admin admin, FileInfoReader file) {
        System.out.println("Please enter the course ID, or type 'q' to end");
        String id = scan.next();
        if (!id.equals("q")) {
            admin.deleteCourse(id, file);
        }
    }
    
    /**
     * Deletes a professor based on the provided professor ID.
     * 
     * @param admin   the admin user
     * @param file    the file containing the professor information
     */
    private void deleteProfessor(Admin admin, FileInfoReader file) {
        System.out.println("Please enter the professor ID, or type 'q' to end");
        String profId = scan.next();
        if (!profId.equals("q")) {
            admin.deleteProf(profId, file);
        }
    }
    
    /**
     * Deletes a student based on the provided student ID.
     * 
     * @param admin   the admin user
     * @param file    the file containing the student information
     */
    private void deleteStudent(Admin admin, FileInfoReader file) {
        System.out.println("Please enter the Student ID, or type 'q' to end");
        String stuId = scan.next();
        if (!stuId.equals("q")) {
            admin.deleteStu(stuId, file);
        }
    }


//Student Operations-----------------------------------------------------------------------
    /**
     * Prompts the student to add a course to their list.
     *
     * @return the course ID or 'q' to return to the previous menu
     */
    private String promptStudentToAddCourse() {
        System.out.println("Please select the course ID you want to add to your list, e.g., 'CIT590'");
        System.out.println("Or enter 'q' to return to the previous menu");
        return scan.next();
    }

    /**
     * Prompts the student to drop a course from their list.
     *
     * @param student the student object
     * @param file    the file reader object
     * @return the course ID or 'q' to return to the previous menu
     */
    private String promptStudentToDropCourse(Student student, FileInfoReader file) {
        System.out.println("The courses in your list:");
        student.checkCourseSchedule();
        System.out.println();

        System.out.println("Please enter the ID of the course you want to drop");
        System.out.println("Or enter 'q' to return to the previous menu");
        return scan.next();
    }

//Professor Operations--------------------------------------------------------------------
    /**
     * Prompts the professor to choose a course to view its student list.
     *
     * @param professor the professor object
     * @param file      the file reader object
     * @return the course ID or 'q' to quit
     */
    private String promptProfessorToViewStudentList(Professor professor, FileInfoReader file) {
        professor.printCoursesTaught(file);
        System.out.println("Please enter the course ID, e.g., 'CIS519'.");
        System.out.println("Or type 'q' to quit.");
        return scan.next();
    }


//---------------------------Administrator----------------------------------

    /**
     * As Admin, adds a new course to the system.
     * 
     * @param admin the admin performing the operation
     * @param file  the file containing the course information
     */
    private void adminAddCourse(Admin admin, FileInfoReader file) {
        String id = getInput("Please enter the course ID, or type 'q' to end.");
        if (id == null) return;

        while (!admin.isCourseIdUnique(id, file)) {
            id = getInput("Please enter a different course ID, or type 'q' to end.");
            if (id == null) return;
        }

        String name = getInput("Please enter the course name, or type 'q' to end.");
        if (name == null) return;

        String start = getInput("Please enter the course start time, or type 'q' to end. eg. '19:00'");
        if (start == null) return;

        String end = getInput("Please enter the course end time, or type 'q' to end. eg. '20:00'");
        if (end == null) return;

        String days = getInput("Please enter the course date, or type 'q' to end. eg. 'MW'");
        if (days == null) return;

        String capacity = getInput("Please enter the course capacity, or type 'q' to end. eg. '72'");
        if (capacity == null) return;

        String lecturerId = getInput("Please enter the course lecturer's id, or type 'q' to end. eg. '001'");
        if (lecturerId == null) return;

        Professor prof;

        if (admin.checkProfExist(lecturerId, file)) {
            prof = file.getOneProf(lecturerId);
        } else {
            System.out.println("The professor isn't in the system, please add this professor first.");
            adminAddProf(admin, file);

            if (!admin.checkProfExist(lecturerId, file)) {
                return;
            }

            prof = file.getOneProf(lecturerId);
        }

        String strCourse = id + ";" + name + ";" + prof.getName() + ";" +
                days + ";" + start + ";" + end + ";" + capacity;
        admin.addCourses(strCourse, file, lecturerId, id);
    }
    


    /**
     * Adds a new professor to the system.
     * 
     * @param admin the admin responsible for adding the professor
     * @param file  the file containing the professor information
     */
    private void adminAddProf(Admin admin, FileInfoReader file) {
        String id = getInput("Please enter the professor's ID, or type 'q' to quit ");
        if (id == null) return;

        while(! admin.isProfIdUnique(id, file)) {
            id = getInput("Please enter a different professor's ID, or type 'q' to quit ");
            if (id == null) return;
        }

        String name = getInput("Please enter the professor's name, or type 'q' to quit ");
        if (name == null) return;

        String username = getInput("Please enter a username ");
        if (username == null) return;

        while(! admin.isProfUsernameUnique(username, file)) {
            username = getInput("Please enter a different username, or type 'q' to quit ");
            if (username == null) return;
        }

        String password = getInput("Please enter a password ");
        if (password == null) return;

        String strProf = name + ";" + id + ";" + username + ";" + password;
        admin.addProf(strProf, file);
        System.out.println("Successfully added the new professor: " + id + " " + name);
    }


    /**
     * Adds a new student to the system with the provided information.
     * 
     * @param admin the admin performing the operation
     * @param file  the file containing the student information
     */
    private void adminAddStudent(Admin admin, FileInfoReader file) {
        String id = getInput("Please enter the student's ID, or type 'q' to quit ");
        if (id == null) return;

        while(! admin.isStudentIdUnique(id, file)) {
            id = getInput("Please enter a different student's ID, or type 'q' to quit ");
            if (id == null) return;
        }

        String name = getInput("Please enter the student's name, or type 'q' to quit ");
        if (name == null) return;

        String username = getInput("Please enter a username ");
        if (username == null) return;

        while(! admin.isStudentUsernameUnique(username, file)) {
            username = getInput("Please enter a different username, or type 'q' to quit ");
            if (username == null) return;
        }

        String password = getInput("Please enter a password ");
        if (password == null) return;

        String str = id + ";" + name + ";" + username + ";" + password;

        int count = 0;
        while (true) {
            String courseId = getInput("Please enter ID of a course which this student already took, one in a time\n" +
                    "type 'q' to quit, type 'n' to stop adding.");
            if (courseId == null || courseId.equals("n")) break;

            if (count == 0) str += ";";
            else str += ",";

            String grade = getInput("Please enter the grade, eg, 'A' ");
            str += courseId + ":" + grade;
            count++;
        }

        admin.addStudent(str, file);
        System.out.println("Successfully added the new student: " + id + " " + name);
    }

    
    /**
     * Retrieves user input from the console with a given prompt.
     *
     * @param prompt the message displayed to the user as a prompt
     * @return the user's input as a String, or null if the input is "q"
     */
    private String getInput(String prompt) {
        System.out.println(prompt);
        String input = scan.next();
        return input.equals("q") ? null : input;
    }

//Interface--------------------------------------------------------------------------------------------------------
    /**
     * Prints the main menu of the system and asks the user to choose an option.
     *
     * @return The user's choice as an integer.
     */
    private int managementSystem() {
        System.out.println("----------------------------");
        System.out.println("Students Management System");
        System.out.println("----------------------------");

        return askForChoice(new String[]{
                "Login as a student",
                "Login as a professor",
                "Login as an admin",
                "Quit the system"
        });
    }

    /**
     * Asks for the user's username and password.
     *
     * @return An array containing the username and password.
     */
    private String[] askNameAndPassWord() {
        System.out.println("Please enter your username, or type 'q' to quit");
        String username = scan.next();
        if (username.equals("q")) return new String[]{"q", "q"};
        System.out.println("Please enter your password, or type 'q' to quit");
        String password = scan.next();
        if (password.equals("q")) return new String[]{username, "q"};
        return new String[]{username, password};
    }

    /**
     * Prints the student interface and asks for an option.
     *
     * @param s The student.
     * @return The chosen option as an integer.
     */
    private int printStudentInterface(Student s) {
        System.out.println("---------------------------");
        System.out.println("Welcome, " + s.getName());
        System.out.println("---------------------------");

        return askForChoice(new String[]{
                "View all courses",
                "Add courses to your list",
                "View enrolled courses",
                "Drop courses in your list",
                "View grades",
                "Return to previous menu"
        });
    }

    /**
     * Prints the professor interface and asks for an option.
     *
     * @param p The professor.
     * @return The chosen option as an integer.
     */
    private int printProfessorInterface(Professor p) {
        System.out.println("-----------------------------");
        System.out.println("Welcome, " + p.getName());
        System.out.println("-----------------------------");

        return askForChoice(new String[]{
                "View given courses",
                "View student list of the given course",
                "Return to previous menu"
        });
    }

    /**
     * Prints the administrator interface and asks for an option.
     *
     * @return The chosen option as an integer.
     */
    private int printAdminInterface() {
        System.out.println("---------------");
        System.out.println("Welcome, admin");
        System.out.println("---------------");

        return askForChoice(new String[]{
                "View all courses",
                "Add new courses",
                "Delete courses",
                "Add new professor",
                "Delete professor",
                "Add new student",
                "Delete student",
                "Return to previous menu"
        });
    }

    /**
     * Prints the provided options and asks the user to choose one.
     * 
     * @param options An array of options to be displayed to the user.
     * @return The chosen option as an integer.
     */
    private int askForChoice(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " -- " + options[i]);
        }
        System.out.println();
        System.out.println("Please enter your option, eg. '1'.");

        String choice;
        while (true) {
            choice = scan.next();
            try {
                return Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

}
