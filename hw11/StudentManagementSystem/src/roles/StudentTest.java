package roles;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import file.FileInfoReader;

class StudentTest {
    Student stu;
    FileInfoReader file;

    @BeforeEach
    void setUp() {
        file = new FileInfoReader();
        stu = new Student();
    }

    @Test
    void login() {
        assertTrue(stu.login("testStudent01", "password590", file));
        assertFalse(stu.login("testStudent01", "password591", file));
    }

    @Test
    void getLogin() {
        Student stu2 = stu.getLogin("testStudent01", "password590", file);
        assertEquals("001", stu2.getId());
        assertEquals("StudentName1", stu2.getName());
    }

//    @Test
//    void viewAllCourse() {
//        stu.viewAllCourse(file);
//    }

    @Test
    void addCourse() {
        Student stu2 = stu.getLogin("testStudent01", "password590", file);
        assertEquals(0, stu.getSchedule().size());
        // add a new course
        stu2.addCourse("CIT590", file);
        assertEquals(1, stu2.getSchedule().size());
        // add a course that exists
        stu2.addCourse("CIT590", file);
        assertEquals(1, stu2.getSchedule().size());
        // add a course not in system
        stu2.addCourse("CIT999", file);
        assertEquals(1, stu2.getSchedule().size());
        // has time conflict
        stu2.addCourse("CIS620", file);
        assertEquals(1, stu2.getSchedule().size());

    }

    @Test
    void dropCourse() {
        Student stu2 = stu.getLogin("testStudent02", "password590", file);
        assertEquals(0, stu.getSchedule().size());
        // add a new course
        stu2.addCourse("CIT590", file);
        assertEquals(1, stu2.getSchedule().size());
        // drop a course not in schedule
        stu2.dropCourse("CIT666");
        assertEquals(1, stu2.getSchedule().size());
        // drop a course
        stu2.dropCourse("CIT590");
        assertEquals(0, stu2.getSchedule().size());
        // drop a course not in schedule
        stu2.dropCourse("CIT666");
        assertEquals(0, stu2.getSchedule().size());
        
    }

    @Test
    void checkCourseSchedule() {
        Student stu2 = stu.getLogin("testStudent02", "password590", file);
        stu2.addCourse("CIT590", file);
        stu2.addCourse("CIT592", file);
        stu2.checkCourseSchedule();
    }

    @Test
    void viewGrades() {
        Student stu2 = stu.getLogin("testStudent02", "password590", file);
        stu2.viewGrades(file);
    }

    @Test
    void getSchedule() {
        Student stu2 = stu.getLogin("testStudent01", "password590", file);
        stu2.addCourse("CIT590", file);
        assertEquals(stu2.getSchedule().get(0).getId(), "CIT590");
    }



}