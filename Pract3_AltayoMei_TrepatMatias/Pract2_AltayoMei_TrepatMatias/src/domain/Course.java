package domain;

public class Course {
	
	//TODO: Add attributes
	
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

}
