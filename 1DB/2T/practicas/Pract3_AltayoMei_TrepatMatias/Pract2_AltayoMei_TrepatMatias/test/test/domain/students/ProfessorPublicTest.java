package test.domain.students;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import domain.Professor;
import domain.Student;

public class ProfessorPublicTest {

    @Test
    void testGetters() {
        Professor prof = new Professor("Alan", "Turing", "aturing", "Dr.", 50000);

        assertEquals("Dr.", prof.getTitle());
        assertEquals(50000, prof.getSalary());
    }

    //Before implementing the code in the second session, the tests below may fail
    @Test
    void testDisplayNameIncludesTitle() {
        Professor prof = new Professor("Ada", "Lovelace", "alovelace", "Prof.");

        assertTrue(prof.getDisplayName().startsWith("Prof."));
        assertTrue(prof.getDisplayName().contains("Ada Lovelace"));
    }

    @Test
    void testEmailFormat() {
        Professor prof = new Professor("Grace", "Hopper", "ghopper", "Dr.");

        assertEquals("ghopper@university.edu", prof.getEmail());
    }
    
    @Test
    void testToStringContainsBasicInformation() {
        Professor p = new Professor("Jane", "Doe", "jdoe2", "Prof.",45000);

        String text = p.toString();

        assertTrue(text.contains("Jane"));
        assertTrue(text.contains("Doe"));
        assertTrue(text.contains("jdoe2"));
    }

    @Test
    void testToStringContainsSalary() {
        Professor prof = new Professor("Alan", "Turing", "aturing", "Dr.", 60000);

        String text = prof.toString();
        assertTrue(text.contains("Salary"));
        assertTrue(text.contains("60000"));
    }
    
    @Test
    void testEqualsSameObject() {
        Student p = new Professor("Mark", "Twain", "mtwain","Dr.",50000);

        assertEquals(p, p);
    }
}

