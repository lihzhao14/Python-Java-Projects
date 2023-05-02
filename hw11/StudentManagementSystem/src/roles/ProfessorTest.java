package roles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import file.FileInfoReader;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {
    Professor prof;
    FileInfoReader file;

    @BeforeEach
    void setUp() {
        file = new FileInfoReader();
        prof = new Professor();
    }

    @Test
    void login() {
        assertTrue(prof.login("Greenberg", "password590", file));
        assertFalse(prof.login("testStudent01", "password591", file));
    }

    @Test
    void setTeach() {
        Professor prof2 = prof.getLogin("Greenberg", "password590", file);
        prof2.setCoursesTaught(file);
        assertEquals("CIT592", prof2.getCourses().get(0).getId());

    }

    @Test
    void courseTeach() {
        Professor prof2 = prof.getLogin("Greenberg", "password590", file);
        prof2.setCoursesTaught(file);
        prof2.printCoursesTaught(file);
    }

    @Test
    void studentInCourse() {
        Professor prof2 = prof.getLogin("Greenberg", "password590", file);
        Student stu = new Student();
        Student stu2 = stu.getLogin("testStudent02", "password590", file);
        stu2.addCourse("CIT590", file);
        prof2.studentInCourse("CIT590", file);
    }
}