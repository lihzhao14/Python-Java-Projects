package file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import course.Course;
import roles.Professor;
import roles.Student;
import roles.Admin;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileInfoReaderTest {
    FileInfoReader file;

    @BeforeEach
    void setUp() {
        file = new FileInfoReader();
    }

    @Test
    void testFileInfoReader() {
        FileInfoReader file = new FileInfoReader();
        ArrayList<Course> courses = file.getCourses();
        ArrayList<Student> students = file.getStudents();
        ArrayList<Admin> admins = file.getAdmins();
        ArrayList<Professor> professors = file.getProfessors();
        assertEquals(courses.size(), 50);
        assertEquals(students.size(), 2);
        assertEquals(admins.size(), 3);
        assertEquals(professors.size(), 32);
    }

    @Test
    void checkCourseExist() {
        assertTrue(file.checkCourseExist("CIT590"));
        assertFalse(file.checkCourseExist("CIT000"));
    }
    
    @Test
    void removeCourse() {
        ArrayList<Course> courses = file.getCourses();
        assertEquals(50, courses.size());
        file.removeCourse("CIT590");
        assertEquals(49, courses.size());
    }

    @Test
    void removeProfessor() {
        ArrayList<Professor> professors = file.getProfessors();
        assertEquals(32, professors.size());
        file.removeProfessor("023");
        assertEquals(31, professors.size());
    }

    @Test
    void removeStudent() {
        ArrayList<Student> students = file.getStudents();
        assertEquals(2, students.size());
        file.removeStudent("001");
        assertEquals(1, students.size());
    }
}