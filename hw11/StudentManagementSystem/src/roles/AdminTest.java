package roles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import course.Course;
import file.FileInfoReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminTest {
    Admin admin;
    FileInfoReader file;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        file = new FileInfoReader();
    }

    @Test
    void login() {
        assertTrue(admin.login("admin01", "password590", file));
        assertFalse(admin.login("testStudent01", "password591", file));
    }

    @Test
    void addCourses() {
        Admin ad = admin.getLogin("admin01", "password590", file);
        ArrayList<Course> courses = file.getCourses();
        assertEquals(50, courses.size());

        // course have exist
        String str = "CIT590; Programming Languages and Techniques; Brandon L Krakowsky; MW; 16:30; 18:00; 110";
        ad.addCourses(str, file, "029", "CIT590" );
        assertEquals(50, courses.size());

        //add successfully
        String str2 = "CIT000; Programming Languages ; Brandon L Krakowsky; MW; 01:30; 03:00; 110";
        ad.addCourses(str2, file, "029", "CIT000" );
        assertEquals(51, courses.size());

        // time conflict
        String str3 = "CIT001; Programming Languages ; Brandon L Krakowsky; MW; 17:30; 18:00; 110";
        ad.addCourses(str3, file, "029", "CIT001" );
        assertEquals(51, courses.size());
    }

    @Test
    void beforeAddCourse() {
        assertFalse(admin.isCourseIdUnique("CIT590", file));
        assertTrue(admin.isCourseIdUnique("CIT000", file));
    }

    @Test
    void checkProfIdExist() {
        assertTrue(admin.checkProfExist("029", file));
        assertTrue(admin.checkProfExist("029", file));
        assertFalse(admin.checkProfExist("000", file));
    }

    @Test
    void addStudent() {
        ArrayList<Student> students = file.getStudents();
        assertEquals(2, students.size());

        // add a new student
        //001; StudentName1; testStudent01; password590; CIS191: A, CIS320: A
        String str = "003; StudentName3; testStudent03; password590; CIS191: A, CIS320: A";
        admin.addStudent(str, file);
        assertEquals(3, students.size());
    }

    @Test
    void addProf() {
        ArrayList<Professor> professors = file.getProfessors();
        assertEquals(32, professors.size());

        //Clayton Greenberg; 001; Greenberg; password590
        String str = "Cally; 000; Greenberg; password590";
        admin.addProf(str, file);
        assertEquals(33, professors.size());

    }

    @Test
    void deleteProf() {
        ArrayList<Professor> professors = file.getProfessors();
        assertEquals(32, professors.size());
        // delete successfully
        admin.deleteProf("001", file);
        assertEquals(31, professors.size());

        // delete unsuccessfully
        admin.deleteProf("000", file);
        assertEquals(31, professors.size());
    }

    @Test
    void deleteStu() {
        ArrayList<Student> students = file.getStudents();
        assertEquals(2, students.size());

        // delete unsuccessful
        admin.deleteStu("001", file);
        assertEquals(1, students.size());

        // does not exist
        admin.deleteStu("000", file);
        assertEquals(1, students.size());
    }

    @Test
    void deleteCourse() {
        ArrayList<Course> courses = file.getCourses();
        assertEquals(50, courses.size());

        // course no exist
        admin.deleteCourse("CIT000", file);
        assertEquals(50, courses.size());

        // course delete
        admin.deleteCourse("CIT590", file);
        assertEquals(49, courses.size());
    }


    @Test
    void beforeDelStu() {
        // exist
        assertTrue(admin.beforeDelStu("001", file));
        // do not exist
        assertFalse(admin.beforeDelStu("000", file));
    }


    @Test
    void beforeAddStuId() {
        // exist
        assertFalse(admin.isStudentIdUnique("001", file));
        // do not exist
        assertTrue(admin.isStudentIdUnique("000", file));
    }

    @Test
    void beforeAddStuUsername() {
        // exist
        assertFalse(admin.isStudentUsernameUnique("testStudent01", file));
        // do not exist
        assertTrue(admin.isStudentUsernameUnique("testStudent00", file));
    }

    @Test
    void beforeAddProfId() {
        // exist
        assertFalse(admin.isProfIdUnique("029", file));
        // do not exist
        assertTrue(admin.isProfIdUnique("000", file));

    }
}