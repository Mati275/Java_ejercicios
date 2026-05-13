package test.domain.students;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import domain.Student;
import domain.Course;
import domain.Professor;

public class StudentPublicTest {

    @Test
    void testInitialState() {
        Student s = new Student("John", "Doe", "jdoe");

        assertEquals(0, s.totalEnrolledCourses());
        assertEquals("jdoe@stu.university.edu", s.getEmail());
    }
    

    @Test
    void testAddGradeToNewCourse() {
        Student s = new Student("Jane", "Doe", "jdoe2");
        Professor p = new Professor("Alan", "Turing", "aturing", "Dr.");
        Course c = new Course("Math", p);

        assertTrue(s.addGradeToCourse(c, 7.5));
        assertEquals(1, s.totalEnrolledCourses());
        assertEquals(7.5, s.getLastGrade(c));
    }

    @Test
    void testAddSecondGradeSameCourse() {
        Student s = new Student("Alice", "Smith", "asmith");
        Professor p = new Professor("Ada", "Lovelace", "alovelace", "Prof.");
        Course c = new Course("Programming", p);

        s.addGradeToCourse(c, 4.0);
        s.addGradeToCourse(c, 6.5);

        assertEquals(6.5, s.getLastGrade(c));
        assertEquals(1, s.totalEnrolledCourses());
    }

    @Test
    void testGradeAverageTwoCourses() {
        Student s = new Student("Bob", "Brown", "bbrown");
        Professor p = new Professor("Grace", "Hopper", "ghopper", "Dr.");

        Course c1 = new Course("Databases", p);
        Course c2 = new Course("Networks", p);

        s.addGradeToCourse(c1, 8.0);
        s.addGradeToCourse(c2, 6.0);

        assertEquals(7.0, s.getGradeAverage());
    }

    //Before implementing the code in the second session, the tests below may fail

    @Test
    void testToStringContainsNumCourses() {
        Student s = new Student("Test", "User", "tuser");
        Professor p = new Professor("Alan", "Turing", "aturing", "Dr.");
        Course c = new Course("Math", p);

        s.addGradeToCourse(c, 5.0);

        String text = s.toString();
        assertTrue(text.contains("Num courses: 1"));
    }
    
    @Test
    void testToStringContainsBasicInformation() {
        Student p = new Student("Jane", "Doe", "jdoe2");

        String text = p.toString();

        assertTrue(text.contains("Jane"));
        assertTrue(text.contains("Doe"));
        assertTrue(text.contains("jdoe2"));
    }

    @Test
    void testEqualsSameObject() {
        Student p = new Student("Mark", "Twain", "mtwain");

        assertEquals(p, p);
    }
}


