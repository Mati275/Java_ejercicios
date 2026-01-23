package domain;

public class Department {
	
	//TODO: Add attributes
	
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
		
		return assignedProfessor [position]; //TODO: Complete/update if necessary
	}
	
	/**
	 * Adds professor to department if not already there
	 * @param prof
	 * @return
	 */
	public boolean addProfessor(Professor prof) {
		for (int i = 0; i < assignedProfessor.length; i++) {
			if(assignedProfessor [i] .equals(prof) ) { // TODO: si no esta dentro == true
				return true;
			}
		}
		
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param prof
	 * @return
	 */
	public boolean removeProfessor(Professor prof) {
		
		for (int i = 0; i < assignedProfessor.length; i++) { // Buscar el index prof para eliminar
			
			if (assignedProfessor [i] .equals (prof)) {
				assignedProfessor [i] = null;
			}
		
			for (int j = 0; j < assignedProfessor.length; j++) {
				if (assignedProfessor [i] == null) {
					assignedProfessor [i] = assignedProfessor [i+1];
					return true;
				}
			}
		
		}
		
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param prof
	 * @return -1 if professor is not part of the department, their position on the vector otherwise
	 */
	public int findProfessor(Professor prof) { 
		for (int i = 0; i < assignedProfessor.length; i++) {
			if (assignedProfessor [i] .equals (prof)) {
				return i;
			}
		}
		
		return -1; //TODO: Complete/update if necessary
	}
	
	

}
