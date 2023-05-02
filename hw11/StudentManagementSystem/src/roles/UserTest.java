package roles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import file.FileInfoReader;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    Student stu;
    Professor prof;
    Admin ad;
    FileInfoReader file;

    @BeforeEach
    void setUp() {
        stu = new Student();
        prof = new Professor();
        ad = new Admin();
        file = new FileInfoReader();
    }

    @Test
    void login() {
        assertTrue(prof.login("Smith", "password590", file));
        assertFalse(prof.login("sherry", "password591", file));
    }

    @Test
    void getLogin() {
        Student stu2 = stu.getLogin("testStudent01", "password590", file);
        assertEquals("001", stu2.getId());
        assertEquals("StudentName1", stu2.getName());
    }
    
    @Test
    void tryLoginValid() {
        User loggedInUser = prof.tryLogin("Smith", "password590", file);
        assertNotNull(loggedInUser);
        assertTrue(loggedInUser instanceof Professor);
        assertEquals("Smith", loggedInUser.getUsername());
        assertEquals("Harry Smith", loggedInUser.getName());
    }

    @Test
    void tryLoginInvalid() {
        User loggedInUser = ad.tryLogin("jane", "password591", file);
        assertNull(loggedInUser);
    }

    @Test
    void tryLoginQuit() {
        User loggedInUser = stu.tryLogin("q", "password590", file);
        assertNull(loggedInUser);

        loggedInUser = prof.tryLogin("Smith", "q", file);
        assertNull(loggedInUser);
    }
}