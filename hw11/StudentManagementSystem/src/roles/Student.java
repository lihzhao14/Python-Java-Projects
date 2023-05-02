/**
 * Class Student
 * 
 * @author Lihong Zhao & Haojie Zheng
 */

package roles;

import course.Course;
import file.FileInfoReader;

import java.util.*;


/**
 * Represent a student
 */
public class Student extends User {
	// HashMap to store course grades for this student
	private HashMap<String, String> courseGrades = new HashMap<>();
	// List to store courses in this student's schedule
	private List<Course> schedule = new LinkedList<>();

    /**
     * Default constructor
     */
    public Student() {}

    /**
     * Constructs a student with details from the given string.
     *
     * @param str a string containing the student details
     */
    public Student(String str) {
        String[] student = str.split(";");
        this.id = student[0].trim();
        this.name = student[1].trim();
        this.username = student[2].trim();
        this.password = student[3].trim();
        String course = student[4].trim();
        String[] courses = course.split(",");
        for (String cour : courses) {
            String[] temp = cour.split(":");
            this.courseGrades.put(temp[0].trim(), temp[1].trim());
        }
    }
    
    
    /**
     * Logs in the student.
     *
     * @param username the username of the student
     * @param password the password of the student
     * @param file the file reader
     * @return true if the username and password are correct
     */
    @Override
    public boolean login(String username, String password, FileInfoReader file) {
        return findStudentByUsernameAndPassword(username, password, file) != null;
    }

    /**
     * Gets the logged in student.
     *
     * @param username the username of the student
     * @param password the password of the student
     * @param file the file reader
     * @return the logged in student
     */
    @Override
    public Student getLogin (String username, String password, FileInfoReader file) {
        return findStudentByUsernameAndPassword(username, password, file);
    }

    /**
     * Finds a student by username and password.
     *
     * @param username the username of the student
     * @param password the password of the student
     * @param file the file reader
     * @return the found student, or null if no student was found
     */
    private Student findStudentByUsernameAndPassword(String username, String password, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        for (Student student : students) {
            if (student.username.equals(username) && student.password.equals(password)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Views all courses.
     *
     * @param file the file reader
     */
    public void viewAllCourse(FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();

        for (Course cour : courses) {
            cour.printCourse();
        }
    }


    /**
     * Adds a course to the student's schedule.
     *
     * @param courseId the ID of the course to add
     * @param file the file reader
     */
    public void addCourse(String courseId, FileInfoReader file) {
        // check whether in schedule
        if(courseValid(courseId, file)) {
            System.out.println("Course added successfully");
            System.out.println();
        }
    }

    /**
     * Check if course is in schedule
     * @param courseId of course
     * @return Optional of course if in schedule
     */
    private Optional<Course> courseInSchedule(String courseId) {
        return this.schedule.stream()
            .filter(cour -> cour.getId().equals(courseId))
            .findFirst();
    }

    /**
     * Check if the course is in system
     * @param courseId of course
     * @param file file
     * @return Optional of course if it in system
     */
    private Optional<Course> courseInSystem(String courseId, FileInfoReader file) {
        return file.getCourses().stream()
            .filter(course -> course.getId().equals(courseId))
            .findFirst();
    }

    /**
     * Check if the course is valid
     * @param courseId of course
     * @param file file
     * @return true if valid
     */
    private boolean courseValid(String courseId, FileInfoReader file) {
        // check if in schedule
        Optional<Course> courseInSchedule = courseInSchedule(courseId);
        if (courseInSchedule.isPresent()) {
            System.out.println("The course you selected is already in your list");
            return false;
        }

        // check if in system
        Optional<Course> courseInSystem = courseInSystem(courseId, file);
        if (!courseInSystem.isPresent()) {
            System.out.println("The course you selected is not in system");
            return false;
        }

        Course course = courseInSystem.get();

        // check capacity
        if (course.getCapacity() == course.getTotal()) {
            System.out.println("The course you selected is full");
            return false;
        }

        // check time conflict
        if (courseConflict(course)) {
            return false;
        }

        this.schedule.add(course);
        course.setTotal();
        course.setStudentsIn(this);
        return true;
    }


    /**
     * Check if the given course conflicts with any course in the schedule
     * @param course the course to check
     * @return true if there is a time conflict
     */
    private boolean courseConflict(Course course) {
        return schedule.stream().anyMatch(sch -> TimeConflict(sch, course));
    }

    /**
     * Check if there is a time conflict between two courses
     * @param course1 the first course
     * @param course2 the second course
     * @return true if there is a time conflict
     */
    private boolean TimeConflict(Course course1, Course course2) {
        Set<Character> days1 = new HashSet<Character>();
        for (char d : course1.getDayList()) {
            days1.add(d);
        }

        for (char d : course2.getDayList()) {
            if (days1.contains(d)) {
                // Courses are on the same day, check time conflict
                int start1 = course1.getIntStart();
                int end1 = course1.getIntEnd();
                int start2 = course2.getIntStart();
                int end2 = course2.getIntEnd();

                if (!(start2 >= end1 || end2 <= start1)) {
                    System.out.println("The course you selected has time conflict with " + course1.getId() + " " + course1.getName());
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Drop a course by its ID
     * @param courseId is course's ID
     */
    public void dropCourse(String courseId) {

        Optional<Course> courseInSchedule = courseInSchedule(courseId);
        if (!courseInSchedule.isPresent()) {
            System.out.println("The course isn't in your schedule");
            return;
        }

        this.schedule.remove(courseInSchedule.get());
        System.out.println("Course dropped successfully");
    }

    

    /**
     * Checks the student's course schedule.
     */
    public void checkCourseSchedule() {
        for (Course cour : this.schedule) {
            cour.printCourse();
        }
    }
    
    
    /**
     * Prints the grades of the courses for the current student
     *
     * @param file The file information reader
     */
    public void viewGrades(FileInfoReader file) {
        HashMap<String, String> courseGrades = this.courseGrades;

        for (String key : courseGrades.keySet()) {
            String tempGrade = courseGrades.get(key);
            ArrayList<Course> courses = file.getCourses();
            String name = "";
            for (Course co : courses) {
                if (co.getId().equals(key)) {
                    name = co.getName();
                    break;
                }
            }

            System.out.println("Grade of " + key + " " + name + ": " + tempGrade);
        }

    }

    /**
     * Gets the current student's schedule
     *
     * @return The current student's schedule
     */
    public List<Course> getSchedule() {
        return this.schedule;
    }

    /**
     * Determines whether a given course is planned
     *
     * @param courseId The ID of the course
     * @return True if the course is planned, false otherwise
     */
    public boolean planCourse(String courseId) {
        for (Course co : this.schedule) {
            if (co.getId().equals(courseId)) return true;
        }
        return false;
    }

}
