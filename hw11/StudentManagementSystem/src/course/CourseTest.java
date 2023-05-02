package course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import roles.Student;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course course;

    @BeforeEach
    void setUp() {
    	String str = "CIT590; Programming Languages and Techniques; Brandon L Krakowsky; MW; 16:30; 18:00; 110";
        course = new Course(str);
    }

    @Test
    void testConstructor() {
        assertEquals("CIT590", course.getId());
        assertEquals("Programming Languages and Techniques", course.getName());
        assertEquals("Brandon L Krakowsky", course.getLecturer());
        assertEquals("MW", new String(course.getDayList()));
        assertEquals(110, course.getCapacity());
        assertEquals(0, course.getTotal());
        assertEquals(1630, course.getIntStart());
        assertEquals(1800, course.getIntEnd());
    }

    @Test
    void testInvalidCourse() {
        assertThrows(IllegalArgumentException.class, () -> new Course("CIT590; Programming Languages ; Brandon L Krakowsky; MW; 16:30; 18:00")); // Missing capacity
        assertThrows(IllegalArgumentException.class, () -> new Course("CIT590; Programming Languages ; Brandon L Krakowsky; MW; 16:30; 18:00; OneTen")); // Capacity is not a number
    }
    
    @Test
    void testSetTotal() {
        assertEquals(0, course.getTotal());
        course.setTotal();
        assertEquals(1, course.getTotal());
    }

    @Test
    void testSetStudentsIn() {
        Student student = new Student(); // Create an instance of Student class.
        course.setStudentsIn(student);
        course.setTotal();
        assertEquals(1, course.getTotal());

        // If the Course class has a getStudentsIn() method, you can use it to check that the student was added.
        // assertTrue(course.getStudentsIn().contains(student));
    }

}
