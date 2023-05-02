/**
 * Class FileInfoReader
 * 
 * @author Lihong Zhao & Haojie Zheng
 */

package file;

import roles.Admin;
import roles.Professor;
import roles.Student;

//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

import course.Course;

/**
 * read the file in
 */
public class FileInfoReader {

    /**
     * all courses
     */
    private ArrayList<Course> courses = new ArrayList<>();
    /**
     * all students
     */
    private ArrayList<Student> students = new ArrayList<>();
    /**
     * all administrators
     */
    private ArrayList<Admin> admins = new ArrayList<>();
    /**
     * all professors
     */
    private ArrayList<Professor> professors = new ArrayList<>();
    
    /**
     * student list
     */
    public List<String> studentList;
    /**
     * professor list
     */
    public List<String> profList;
    /**
     * administrator list
     */
    public List<String> adminList;
    /**
     * course list
     */
    public List<String> courseList;


    /**
     * read file from .txt file
     */
    private static final String STUDENT_INFO_PATH = "src/studentInfo.txt";
    private static final String PROF_INFO_PATH = "src/profInfo.txt";
    private static final String ADMIN_INFO_PATH = "src/adminInfo.txt";
    private static final String COURSE_INFO_PATH = "src/courseInfo.txt";
    
    /**
     * read file from .txt file
     */
    public FileInfoReader() {
        try {
            this.studentList = Files.readAllLines(Paths.get(STUDENT_INFO_PATH));
            this.profList = Files.readAllLines(Paths.get(PROF_INFO_PATH));
            this.adminList = Files.readAllLines(Paths.get(ADMIN_INFO_PATH));
            this.courseList = Files.readAllLines(Paths.get(COURSE_INFO_PATH));

            constructCourses();
            constructStudents();
            constructAdmins();
            constructProfessors();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * construct the courses
     */
    private void constructCourses() {
        for(String courseInfo : this.courseList) {
           this.courses.add(new Course(courseInfo));
        }
    }


    /**
     * construct the Admin
     */
    private void constructAdmins() {
        for(String adminInfo : this.adminList) {
            this.admins.add(new Admin(adminInfo));
        }
    }


    /**
     * construct the students
     */
    private void constructStudents() {
        for(String studentInfo : this.studentList) {
            this.students.add(new Student(studentInfo));
        }
    }


    /**
     * construct the Professors
     */
    private void constructProfessors() {
        for(String profInfo : this.profList) {
            this.professors.add(new Professor(profInfo));
        }
    }


    /**
     * get the students list
     * @return students list
     */
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    /**
     * get the admin list
     * @return admin list
     */
    public ArrayList<Admin> getAdmins() {
        return this.admins;
    }

    /**
     * get the Professors list
     * @return Professors list
     */
    public ArrayList<Professor> getProfessors() {
        return this.professors;
    }

    /**
     * get the courses list
     * @return courses list
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    /**
     * give courseId, check if it has exists
     * @param courseId of course
     * @return true if it has exists
     */
    public boolean checkCourseExist(String courseId) {
        for (Course couse : this.courses) {
            if (couse.getId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set the course list
     * @param course given course information
     */
    public void setCourses(String course) {
        this.courses.add(new Course(course));
    }

    /**
     * set the student list
     * @param student given student information
     */
    public void setStudents(String student) {
        this.students.add(new Student(student));
    }

    /**
     * set the professor list
     * @param professor given student information
     */
    public void setProfessors(String professor) {
        this.professors.add(new Professor(professor));
    }

    /**
     * get professor information based on the id
     * @param profId of professor
     * @return professor information
     */
    public Professor getOneProf(String profId) {
        for (Professor prof : this.professors) {
            if (prof.getId().equals(profId)) return prof;
        }
        return null;
    }

    /**
     * get one course object
     * @param courseId id of course
     * @return Course object
     */
    public Course getOneCourse(String courseId){
        for (Course cour : this.courses) {
            if (cour.getId().equals(courseId)) return cour;
        }
        return null;
    }

    /**
     * remove the course based on ID
     * @param id of Course
     */
    public void removeCourse(String id) {
        courses.removeIf(course -> course.getId().equals(id));
    }


    /**
     * remove the Professor based on ID
     * @param id of Professor
     */
    public void removeProfessor(String id) {
        professors.removeIf(professor -> professor.getId().equals(id));
    }


    /**
     * remove the Student based on ID
     * @param id of Student
     */
    public void removeStudent(String id) {
    	students.removeIf(professor -> professor.getId().equals(id));
    }
}
