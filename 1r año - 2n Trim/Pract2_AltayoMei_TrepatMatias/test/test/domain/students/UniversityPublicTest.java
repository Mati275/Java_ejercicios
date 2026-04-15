package test.domain.students;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import domain.University;
import domain.Professor;
import domain.Student;
import domain.Course;
import domain.Department;

public class UniversityPublicTest {

    @Test
    void testInitialState() {
        University uni = new University("Tecnocampus", "Mataro");
        assertEquals("Tecnocampus", uni.getName());
        assertEquals("Mataro", uni.getAddress());
    }

    @Test
    void testAddStudent() {
        University uni = new University("Tecnocampus", "Mataro");
        Student s = new Student("John", "Doe", "jdoe");

        assertTrue(uni.addStudent(s));
        assertTrue(uni.containsStudent(s));
    }

    @Test
    void testAddCourse() {
        University uni = new University("Tecnocampus", "Mataro");
        Professor p = new Professor("Alan", "Turing", "aturing", "Dr.");
        Course c = new Course("Math", p);

        assertTrue(uni.addCourse(c));
        assertTrue(uni.containsCourse(c));
    }

    @Test
    void testAddDepartment() {
        University uni = new University("Tecnocampus", "Mataro");
        Department d = new Department("CS", uni);

        assertTrue(uni.addDepartment(d));
        assertTrue(uni.containsDepartment(d));
    }

    @Test
    void testAddProfessorToDepartment() {
        University uni = new University("Tecnocampus", "Mataro");
        Department d = new Department("CS", uni);
        uni.addDepartment(d);

        Professor p = new Professor("Ada", "Lovelace", "alovelace", "Prof.");

        assertTrue(uni.addProfessorToDepartment(p, d));
        assertTrue(uni.containsProfessor(p));
    }
}
