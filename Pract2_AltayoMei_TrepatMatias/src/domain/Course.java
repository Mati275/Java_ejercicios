package domain;

public class Course {
	
	//ATTRIBUTES
	
	private String name;
	private Professor professor;
	
	public static final int MAX_ATTEMPTS = 5;
	
	
	//CONSTRUCTOR
	
	public Course(String name, Professor prof) {
		//TODO: Complete
		this.name = name;
		this.professor = prof;
	}
	
	
	//*******
	//METHODS
	//*******
	
	
	//GETTERS
	
	public String getName() {
		return name; //TODO: Complete/update if necessary
	}
	
	public Professor getProfessor() {
		return professor; //TODO: Complete/update if necessary
	}
	
	// OTHER METHODS

	
	@Override
	public boolean equals(Object obj) {
		
		// Declare a variable of Course
		Course course;
		
		// If the object is not a Course
		if (  !( obj instanceof Course ) ) {
			return false;
		}
		// Change(compulsory) the type of the parameter to a Course, to get the help of the methods
		course = (Course) obj;
		
		// The names are the same
		if ( this.name.equalsIgnoreCase(course.name) ) {
			return true; 
		}
		
		// The names aren't the same
		return false;
		
	}
	
	@Override
	public String toString() {
		// We're using the individual methods because professor.getDisplayName() includes it's title
		return name + "\tProfessor: " + professor.getName() + " " + professor.getLastName();
	}
	
	
}
