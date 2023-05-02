/**
 * Class Admin
 * 
 * @author Lihong Zhao & Haojie Zheng
 */

package roles;

import course.Course;
import file.FileInfoReader;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Represent an administractor
 */
public class Admin extends User {

    /**
     * constructor
     * @param str given string
     */
    public Admin(String str) {
        String[] admin = str.split(";");
        this.id = admin[0].trim();
        this.name = admin[1].trim();
        this.username = admin[2].trim();
        this.password = admin[3].trim();
    }

    /**
     * Default constructor
     */
    public Admin() {}


    /**
     * Logs in as admin
     * @param username of admin
     * @param password of admin
     * @param file file
     * @return true of login in successfully
     */
    @Override
    public boolean login(String username, String password, FileInfoReader file) {
        return findAdminByUsernameAndPassword(username, password, file) != null;
    }

    /**
     * Retrieves the logged in admins
     * @param username of admin
     * @param password od admin
     * @param file file
     * @return Admin object
     */
    @Override
    public Admin getLogin (String username, String password, FileInfoReader file) {
        return findAdminByUsernameAndPassword(username, password, file);
    }

    /**
     * Finds a admin by username and password.
     *
     * @param username the username of the admin
     * @param password the password of the admin
     * @param file the file reader
     * @return the found admin, or null if no admin was found
     */
    private Admin findAdminByUsernameAndPassword(String username, String password, FileInfoReader file) {
        ArrayList<Admin> admins = file.getAdmins();
        for (Admin admin : admins) {
            if (admin.username.equals(username) && admin.password.equals(password)) {
                return admin;
            }
        }
        return null;
    }
    
    //1- view all courses
    /**
     * Displays all courses.
     * @param file the file reader
     */
    public void viewAllCourse(FileInfoReader file) {
        file.getCourses().forEach(Course::printCourse);
    }

    
    //2- add new courses
    /**
     * Adds a new course.
     *
     * @param strCourse the string representation of the course to add
     * @param file the file reader
     * @param lecturerId the ID of the lecturer teaching the course
     * @param courseId the ID of the new course
     */
    public void addCourses(String strCourse, FileInfoReader file, String lecturerId, String courseId) {
        // get prof info based on id
        Professor prof = file.getOneProf(lecturerId);

        //check if prof has conflict
        prof.setCoursesTaught(file);
        ArrayList<Course> profCourses = prof.getCourses();
        Course str = new Course(strCourse);

        boolean hasConflict = profCourses.stream()
                .anyMatch(course -> checkProfConflict(course, str));

        if (hasConflict) {
            profCourses.stream()
                .filter(course -> checkProfConflict(course, str))
                .findFirst()
                .ifPresent(course -> printConflict(course, str));
        } else {
            file.setCourses(strCourse);
            Course thisCourse = file.getOneCourse(courseId);
            System.out.print("Successfully added the course: ");
            thisCourse.printCourse();
        }
    }


    /**
     * Checks if a course with the given ID already exists.
     *
     * @param id the ID of the course to check
     * @param file the file reader
     * @return true if the course exists, false otherwise
     */
    public boolean isCourseIdUnique(String id, FileInfoReader file) {
        if (file.checkCourseExist(id)) {
            System.out.println("The course already exist");
            return false;
        }
        return true;
    }


    /**
     * Checks if a professor with the given ID exists.
     *
     * @param lecturerId the ID of the professor to check
     * @param file the file reader
     * @return true if the professor exists, false otherwise
     */
    public boolean checkProfExist(String lecturerId, FileInfoReader file) {
        return file.getProfessors().stream()
                    .anyMatch(prof -> prof.getId().equals(lecturerId));
    }

    
    /**
     * Checks if the new course conflicts with existing courses.
     * @param course1 existing course
     * @param course2 new course
     * @return true if the courses conflict, false otherwise
     */
    private boolean checkProfConflict(Course course1, Course course2 ) {
        int start1 = course1.getIntStart();
        int end1 = course1.getIntEnd();
        int start2 = course2.getIntStart();
        int end2 = course2.getIntEnd();

        return IntStream.range(0, course1.getDayList().length)
                .anyMatch(i -> course1.getDayList()[i] == course2.getDayList()[i] 
                && (start2 < end1 && end2 > start1));
    }


    /**
     * Prints a conflict message when a course conflicts with an existing course.
     *
     * @param course the course with which the new course conflicts
     * @param newCourse the new course
     */
    private void printConflict(Course course, Course newCourse){
        System.out.print("The new added course has a time conflict with the course: " );
        course.printCourse();
        System.out.print("Unable to add new course ");
        newCourse.printCourse();
    }
    
    //3-Delete Course
    /**
     * Deletes a course with the given ID.
     *
     * @param id the ID of the course to delete
     * @param file the file reader
     */
    public void deleteCourse(String id, FileInfoReader file) {
        if (doesCourseExist(id, file)) {
            file.removeCourse(id);
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete failed, course does not exists.");
        }
    }

    /**
     * Checks if a course with the given ID exists.
     *
     * @param id the ID of the course to check
     * @param file the file reader
     * @return true if the course exists, false otherwise
     */
    private boolean doesCourseExist(String id, FileInfoReader file) {
        return file.getCourses().stream()
                    .anyMatch(course -> course.getId().equals(id));
    }

    
    //4-Add new professor
    /**
     * Adds a new professor to the system.
     *
     * @param strProf the string representation of the new professor
     * @param file the file reader
     */
    public void addProf(String strProf, FileInfoReader file) {
        file.setProfessors(strProf);
    }
    
    
    /**
     * Checks if the given ID is unique before adding a professor.
     *
     * @param id the ID of the professor to check
     * @param file the file reader
     * @return true if the ID is unique, false otherwise
     */
    public boolean isProfIdUnique(String id, FileInfoReader file) {
        boolean isExist = file.getProfessors().stream()
                .anyMatch(prof -> prof.getId().equals(id));
        if (isExist) {
            System.out.println("The ID already exists.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the given username is unique before adding a professor.
     *
     * @param username the username to check
     * @param file the file reader
     * @return true if the username is unique, false otherwise
     */
    public boolean isProfUsernameUnique(String username, FileInfoReader file) {
        boolean isExist = file.getProfessors().stream()
                .anyMatch(prof -> prof.getUsername().equals(username));
        if (isExist) {
            System.out.println("The username you entered is not available.");
            return false;
        }
        return true;
    }
    
    
    //5-Delete Professor
    /**
     * Deletes a professor with the given ID.
     *
     * @param id the ID of the professor to delete
     * @param file the file reader
     */
    public void deleteProf(String id, FileInfoReader file) {
        if (checkProfExist(id, file)) {
            file.removeProfessor(id);
            System.out.println("Delete successful");
        } else{
            System.out.println("Delete failed, lecturer does not exists.");
        }
    }
    

    //6-Add new student
    /**
     * Adds a new student to the system.
     *
     * @param strStu the string representation of the new student
     * @param file the file reader
     */
    public void addStudent(String strStu, FileInfoReader file) {
        file.setStudents(strStu);
    }

    /**
     * Checks if the given ID is unique before adding a student.
     *
     * @param id the ID of the student to check
     * @param file the file reader
     * @return true if the ID is unique, false otherwise
     */
    public boolean isStudentIdUnique(String id, FileInfoReader file) {
        boolean isExist = file.getStudents().stream()
                .anyMatch(stu -> stu.getId().equals(id));
        if (isExist) {
            System.out.println("The ID already exists.");
            return false;
        }
        return true;
    }
    

    /**
     * Checks if the given username is unique before adding a student.
     *
     * @param username the username to check
     * @param file the file reader
     * @return true if the username is unique, false otherwise
     */
    public boolean isStudentUsernameUnique(String username, FileInfoReader file) {
        boolean isExist = file.getStudents().stream()
                .anyMatch(stu -> stu.getUsername().equals(username));
        if (isExist) {
            System.out.println("The username you entered is not available.");
            return false;
        }
        return true;
    }

    
    //7-Delete Student
    /**
     * Deletes a student with the given ID.
     *
     * @param id the ID of the student to delete
     * @param file the file reader
     */
    public void deleteStu(String id, FileInfoReader file) {
        if (beforeDelStu(id, file)) {
            file.removeStudent(id);
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete failed, lecturer does not exists.");
        }

    }


    /**
     * Checks if a student with the given ID exists.
     *
     * @param id the ID of the student to check
     * @param file the file reader
     * @return true if the student exists
     */

    public boolean beforeDelStu(String id, FileInfoReader file) {
        return file.getStudents().stream()
                    .anyMatch(stu -> stu.getId().equals(id));
    }

}
