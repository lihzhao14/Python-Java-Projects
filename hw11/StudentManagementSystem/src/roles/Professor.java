/**
 * Class Professor
 * 
 * @author Lihong Zhao & Haojie Zheng
 */

package roles;

import course.Course;
import file.FileInfoReader;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

/**
 * Represent professor object
 */
public class Professor extends User{
    /**
     * course list
     */
    ArrayList<Course> courses = new ArrayList<>();

    /**
     * constructor
     * @param str professor information
     */
    public Professor(String str) {
        String[] professor = str.split(";");
        this.id = professor[1].trim();
        this.name = professor[0].trim();
        this.username = professor[2].trim();
        this.password = professor[3].trim();

    }

    /**
     * Default constructor
     */
    public Professor() {}

    /**
     * Login as professor
     * @param username of professor
     * @param password of professor
     * @param file file
     * @return true if login in successfully
     */ 
    @Override
    public boolean login(String username, String password, FileInfoReader file) {
        return findProfByUsernameAndPassword(username, password, file) != null;
    }
    
    
    /**
     * Gets login information
     * @param username of professor
     * @param password of professor
     * @param file file
     * @return professor object
     */
    @Override
    public Professor getLogin (String username, String password, FileInfoReader file) {
        return findProfByUsernameAndPassword(username, password, file);
    }
    
    
    /**
     * Finds a professor by username and password.
     *
     * @param username the username of the professor
     * @param password the password of the professor
     * @param file the file reader
     * @return the found professor, or null if no professor was found
     */
    private Professor findProfByUsernameAndPassword(String username, String password, FileInfoReader file) {
        ArrayList<Professor> professors = file.getProfessors();
        for (Professor prof : professors) {
            if (prof.username.equals(username) && prof.password.equals(password)) {
                return prof;
            }
        }
        return null;
    }
    
    
    /**
     * Sets the list of courses taught by the lecturer.
     * @param file file
     */
    public void setCoursesTaught(FileInfoReader file) {
        List<Course> allCourses = file.getCourses();
        this.courses.clear();
        this.courses.addAll(allCourses.stream()
                .filter(course -> course.getLecturer().equals(this.name))
                .collect(Collectors.toList()));
    }

    /**
     * Prints the list of courses taught by the lecturer.
     * 
     * @param fileInfoReader the file information reader
     */
    public void printCoursesTaught(FileInfoReader file) {
    	setCoursesTaught(file);
        System.out.println("------------The course list------------");
        for (Course course : this.courses) {
            course.printCourse();
        }
        System.out.println();
    }

    
    /**
     * Displays the list of students enrolled in a course taught by this professor.
     *
     * @param courseId the ID of the course
     * @param file the file containing course and student data
     */
    public void studentInCourse(String courseId, FileInfoReader file) {
        if (this.courses.isEmpty()) {
            setCoursesTaught(file);
        }

        Optional<Course> courseOptional = courses.stream().filter(course -> course.getId().equals(courseId)).findFirst();

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            String courseName = course.getName() == null ? "Unknown" : course.getName();
            System.out.printf("Students in your course %s %s:%n", courseId, courseName);

            ArrayList<Student> students = file.getStudents();
            for (Student stu : students) {
                if (stu.planCourse(courseId)) {
                    System.out.printf("%s %s%n", stu.getId(), stu.getName());
                }
            }
            System.out.println();
        } else {
            System.out.printf("No course found with ID %s%n", courseId);
        }
    }


    /**
     * get the course list the professor teach
     * @return course list
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }
}
