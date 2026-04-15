package domain;

public class Department {
	
	//ATTRIBUTES
	
	private String name;
	private University university;
	private Professor assignedProfessors [];
	private int numAsignedProfessor;
	private static final int MAX_CAPACITY = 100;

	
	// CONSTRUCTOR
	public Department(String name, University university) {
		//TODO: Complete
		this.name = name;
		this.university = university;
		
		assignedProfessors = new Professor [MAX_CAPACITY];
		
		numAsignedProfessor = 0;
	}
	
	
	
	//*******
	//METHODS
	//*******
	
	// GETTERS
	public String getName() {
		return name; 
	}
	
	public int getNumProfessors() {
		return numAsignedProfessor; 
	}
	
	
	// OTHER METHODS
	
	public boolean isFull() {
		for(int i = 0; i < assignedProfessors.length; i++) {
			if (assignedProfessors [i] == null) {  
				return false; // If one element of assignedProfessors vector is empty
			}
		}
		
		return true; // All the elements of assignedProfessors vector are filled
	} 
	
	public Professor getProfessor(int position) {
		if (position < 0 || position > 99) {
			return null;
		}
		
		return assignedProfessors[position]; 
	}
	
	/**
	 * Adds professor to department if not already there
	 * @param prof
	 * @return
	 */
	public boolean addProfessor(Professor prof) {
		
		// The professor isn't in the array of professors && the array of professors isn't full
		if ( findProfessor(prof) == -1 && !isFull() ) {
			
			assignedProfessors[numAsignedProfessor] = prof; // Num assignedProfessor is the first null index in the array if the array isn't full
			numAsignedProfessor ++;
			return true; // The professor is successfully added
			
		} // The array of professors is full || the professor isn't on the array of professors
		
		return false; // The array of professors is full || the professor isn't on the array of professors
	}
	
	
	/**
	 * 
	 * @param prof
	 * @return
	 */
	
	public boolean removeProfessor(Professor prof) {
		int indexProfessor;
		
		indexProfessor = findProfessor(prof);
			
		// If the professor it's found (the index found is the same as the object of the professor)
		if ( getProfessor( indexProfessor ).equals( prof ) ) {
			
			assignedProfessors [indexProfessor] = null; // Remove the professor
			numAsignedProfessor --;

			// Make a walkthrought, "i" is the index position to be replaced, replace all positions exept the last one which is filled (because it might be null if the array is full)
			for (int i = indexProfessor; i < numAsignedProfessor; i++) {
				
				assignedProfessors [i] = assignedProfessors [i+1];
			
			}
			
			// Set the last index + 1 position of the array to null, because it will be always be duplicated
			assignedProfessors[numAsignedProfessor] = null; 
			
			
			
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
		for (int i = 0; i < numAsignedProfessor; i++) {
			if ( assignedProfessors[i].equals( prof ) ) {
				return i;
			}
		}
		
		return -1; 
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		// Declare a variable of Department
		Department department;
		
		// If the object is not a Department
		if (  !( obj instanceof Department ) ) {
			return false;
		}
		// Change(compulsory) the type of the parameter to a Department, to get the help of the methods
		department = (Department) obj;
		
		// The names are the same and the university is the same (comparing the universities with the operand .equals inside the class of University)
		if ( this.name.equalsIgnoreCase(department.name) && this.university.equals(department.university) ) {
			return true; 
		}
		
		// The names aren't the same
		return false;
		
	}
	
	@Override
	public String toString() {
		return name + " (" + university.getName() + ")\t Num assigned professors: " + numAsignedProfessor;
	}
	
	

}
