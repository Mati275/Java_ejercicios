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
		
		// The professor isn't in the array of professors && the array of professors is full
		if ( findProfessor(prof) == -1 && isFull() ) {
			
			// Num assignedProfessor is the first null index in the array if the array isn't full
			assignedProfessor[numAsignedProfessor] = prof;
			numAsignedProfessor ++;
			return true; // The professor is successfully added
			
//			for (int i = 0; i < assignedProfessor.length; i++) {
//				
//				// Find the first void position
//				if( assignedProfessor[i] == null ) {
//					
//					assignedProfessor[i] = prof;
//					numAsignedProfessor += 1;
//					return true; // The professor is successfully added
//					
//				} 

//			}
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
			
			numAsignedProfessor --;
			assignedProfessor [indexProfessor] = null; // Remove the professor
			
			// Make a walkthrought, "i" is the index position to be replaced, replace all positions exept the last one which is filled (because it might be null if the array is full)
			for (int i = indexProfessor; i < numAsignedProfessor; i++) {
				
				assignedProfessor [i] = assignedProfessor [i+1];
			
			}
			
			// Set the last index + 1 position of the array to null, because it will be always be duplicated
			
			assignedProfessor[numAsignedProfessor] = null; 
			
			
			
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


}
