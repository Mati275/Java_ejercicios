package domain;

public class Department {
	
	//ATTRIBUTES
	
	private String name;
	private University university;
	private Professor assignedProfessor [];
	private int numAsignedProfessor;
	private static final int MAX_CAPACITY = 100;

	
	// CONSTRUCTOR
	public Department(String name, University university) {
		//TODO: Complete
		this.name = name;
		this.university = university;
		
		assignedProfessor = new Professor [MAX_CAPACITY];
		
		numAsignedProfessor = 0;
	}
	
	
	
	//*******
	//METHODS
	//*******
	
	// GETTERS
	public String getName() {
		return name; //TODO: Complete/update if necessary
	}
	
	public int getNumProfessors() {
		return numAsignedProfessor; //TODO: Complete/update if necessary
	}
	
	public boolean isFull() {
		for(int i = 0; i < assignedProfessor.length; i++) {
			if (assignedProfessor [i] == null) {  //if one element of assignedProfessor is empty
				return false;
			}
		}
		
		return true; //TODO: Complete/update if necessary
	} 
	
	public Professor getProfessor(int position) {
		if (position < 0 || position >= 100) {
			return null;
		}
		
		return assignedProfessor[position]; //TODO: Complete/update if necessary
	}
	
	/**
	 * Adds professor to department if not already there
	 * @param prof
	 * @return
	 */
	public boolean addProfessor(Professor prof) {
		
		// The professor is in the array of professors
		if ( findProfessor(prof) != -1 ) {
			return false; // The professor is already on the array of professors
		} // The professor isn't on the array of professors
		
		
		for (int i = 0; i < assignedProfessor.length; i++) {
			
			// Find the first void position
			if(assignedProfessor[i] == null) {
				
				assignedProfessor[i] = prof;
				numAsignedProfessor += 1;
				return true; // The professor is successfully added
				
			}
			
		} // The array of professors is filled
		
		return false; // All the array is filled 
	}
	
	
	/**
	 * 
	 * @param prof
	 * @return
	 */
	
	public boolean removeProfessor(Professor prof) {
		int index_professor;
		
		index_professor = findProfessor(prof);
			
		// If the professor it's found
		if ( index_professor != -1 ) {
			
			numAsignedProfessor -= 1;
			assignedProfessor [index_professor] = null; // Remove the professor
			
			
			for (int j = index_professor; j < assignedProfessor.length - 1; j++) {
				
				assignedProfessor [j] = assignedProfessor [j+1];
			
			}
			
			// If the array was filled before executing the method, set to null the last position to avoid repetition
			if (assignedProfessor[assignedProfessor.length - 1] != null) {
				assignedProfessor[assignedProfessor.length - 1] = null; 
			}
			
			
			return true; // The professor is successfully removed 
		
		} // The professor isn't found
		
		
		return false; //The professor isn't found --> The professor isn't removed
	}
	
	
	/**
	 * 
	 * @param prof
	 * @return -1 if professor is not part of the department, their position on the vector otherwise
	 */
	public int findProfessor(Professor prof) { 
		for (int i = 0; i < assignedProfessor.length; i++) {
			if ( assignedProfessor[i].equals( prof ) ) {
				return i;
			}
		}
		
		return -1; 
	}
	
	
	// ***********
	// EXTRA METHODS
	// ***********


}
