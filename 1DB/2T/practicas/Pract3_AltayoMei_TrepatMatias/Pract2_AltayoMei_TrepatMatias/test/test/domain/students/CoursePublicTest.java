package test.domain.students;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import domain.Course;
import domain.Professor;

public class CoursePublicTest {

    @Test
    void testGetName() {
        Professor prof = new Professor("Alan", "Turing", "aturing","Prof.");
        Course course = new Course("Algorithms", prof);

        assertEquals("Algorithms", course.getName());
    }

    @Test
    void testGetProfessor() {
        Professor prof = new Professor("Ada", "Lovelace", "alovelace", "Prof.");
        Course course = new Course("Programming", prof);

        assertEquals(prof, course.getProfessor());
    }

    @Test
    void testMaxAttemptsConstant() {
        assertEquals(5, Course.MAX_ATTEMPTS);
    }
    
    
    //2nd session
    @Test
    void testToStringContainsCourseAndProfessorName() {
        Professor prof = new Professor("Grace", "Hopper", "ghopper", "Prof.");
        Course course = new Course("Compilers", prof);

        String text = course.toString();

        assertTrue(text.contains("Compilers"));
        assertTrue(text.contains("Grace"));
        assertTrue(text.contains("Hopper"));
    }
    
}

