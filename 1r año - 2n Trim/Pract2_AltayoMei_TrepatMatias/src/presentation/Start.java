package presentation;

import domain.*;

public class Start {
	

	public static void main (String[] args) {
		
		University university;
		Department tecnology, business;
		Professor profOne, profTwo;
		Student studentOne, studentTwo;
		Course maths, poo, english, history, computers, videogameDesign;
		
		
		// Creating a university --> University void
		university = new University("Tecnocampus", "Mataró");
		System.out.println( university.toString() );
		
		// Creating the departments
		tecnology = new Department("Tecnology", university);
		business = new Department("Business", university);
		
		university.addDepartment(tecnology);
		university.addDepartment(business);
		
		// Creating the professors
		profOne = new Professor("Mario", "Torres", "mTorres", "Dr.");
		profTwo = new Professor("Julia", "García", "jGarcia", "Prof.");
		
		university.addProfessorToDepartment(profOne, tecnology);
		university.addProfessorToDepartment(profTwo, tecnology);
		
		// Creating the students
		studentOne = new Student("Maria", "Álvarez", "mAlvarez");
		studentTwo = new Student("Juan", "Pérez", "jPerez");

		
		university.addStudent(studentOne);
		university.addStudent(studentTwo);
		
		
		// Printing the departments, professors and students
		System.out.println( tecnology.toString() );
		System.out.println( business.toString() );
		
		System.out.println( profOne.toString() );
		System.out.println( profTwo.toString() );
		
		System.out.println( studentOne.toString() );
		System.out.println( studentTwo.toString() );
		
		// Creating the Courses
		maths = new Course("maths", profOne);
		poo = new Course("poo", profOne);
		english = new Course("english", profOne);
		history = new Course("history", profTwo);
		computers = new Course("computers", profTwo);
		videogameDesign = new Course("videogameDesign", profTwo);
		
		university.addCourse(maths);
		university.addCourse(poo);
		university.addCourse(english);
		university.addCourse(history);
		university.addCourse(computers);
		university.addCourse(videogameDesign);
		
		// Printing the courses
		System.out.println( maths.toString() );
		System.out.println( poo.toString() );
		System.out.println( english.toString() );
		System.out.println( history.toString() );
		System.out.println( computers.toString() );
		System.out.println( videogameDesign.toString() );
		
		
		// Adding the courses to one student
		studentOne.addGradeToCourse(maths, -1.0);
		studentOne.addGradeToCourse(poo, -1.0);
		studentOne.addGradeToCourse(english, -1.0);
		studentOne.addGradeToCourse(history, -1.0);
		studentOne.addGradeToCourse(computers, -1.0);
		studentOne.addGradeToCourse(videogameDesign, -1.0);
		
		System.out.println( studentOne.toString() );
		
		// Adding grades to the same student
		
		for (int i = 0; i < 6; i++) {
			if ( studentOne.addGradeToCourse(maths, i) ) {
				System.out.println("Nota afegida correctament");
			}
			else {
				System.out.println("No s'ha pogut afegir la nota");
			}
		}
		
		for (int i = 0; i < 6; i++) {
			if ( studentOne.addGradeToCourse(poo, i) ) {
				System.out.println("Nota afegida correctament");
			}
			else {
				System.out.println("No s'ha pogut afegir la nota");
			}
		}
		
		// Showing the average of the student with the grades
		System.out.println( studentOne.getGradeAverage() );


		
		// Info. of the university --> University partially filled
		System.out.println( university.toString() );

	}

}
