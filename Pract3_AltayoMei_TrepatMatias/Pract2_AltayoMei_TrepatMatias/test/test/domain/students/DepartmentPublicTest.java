package test.domain.students;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import domain.Department;
import domain.Professor;
import domain.University;

public class DepartmentPublicTest {

    @Test
    void testInitialState() {
        University u = new University("Tecnocampus", "Mataro");
        Department d = new Department("Computer Science", u);

        assertEquals("Computer Science", d.getName());
        assertEquals(0, d.getNumProfessors());
        assertFalse(d.isFull());
    }

    @Test
    void testAddProfessor() {
        University u = new University("Tecnocampus", "Mataro");
        Department d = new Department("Math", u);

        Professor p = new Professor("Alan", "Turing", "aturing", "Dr.");

        assertTrue(d.addProfessor(p));
        assertEquals(1, d.getNumProfessors());
        assertEquals(p, d.getProfessor(0));
    }

    @Test
    void testAddSameProfessorTwice() {
        University u = new University("Tecnocampus", "Mataro");
        Department d = new Department("Physics", u);

        Professor p = new Professor("Marie", "Curie", "mcurie", "Prof.");

        assertTrue(d.addProfessor(p));
        assertFalse(d.addProfessor(p));
        assertEquals(1, d.getNumProfessors());
    }

    @Test
    void testRemoveProfessor() {
        University u = new University("Tecnocampus", "Mataro");
        Department d = new Department("Chemistry", u);

        Professor p = new Professor("Linus", "Pauling", "lpauling", "Dr.");

        d.addProfessor(p);
        assertTrue(d.removeProfessor(p));
        assertEquals(0, d.getNumProfessors());
    }

    @Test
    void testGetProfessorOutOfBounds() {
        University u = new University("Tecnocampus", "Mataro");
        Department d = new Department("Biology", u);

        assertNull(d.getProfessor(0));
    }
}

