package domain;

public class University {
	
	//TODO: Add attributes here
	
	
	public University(String name, String address) {
		//TODO: Complete
	}
	
	
	
	public String getName() {
		return null; //TODO: Complete/update if necessary
	}
	
	
	public String getAddress() {
		return null; //TODO: Complete/update if necessary
	}
	
	/**
	 * Returns true if department already exists, false otherwise
	 * @param department
	 * @return
	 */
	public boolean containsDepartment(Department department) {
		return true; //TODO: Complete/update if necessary
	}
	
	/**
	 * Returns true if professor is already linked to uni, false otherwise
	 * @param professor
	 * @return
	 */
	public boolean containsProfessor(Professor professor) {
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * Returns true if course is already linked to uni, false otherwise
	 * @param course
	 * @return
	 */
	public boolean containsCourse(Course course) {
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * Returns true if student is already linked to uni, false otherwise
	 * @param student
	 * @return
	 */
	public boolean containsStudent(Student student) {
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param course
	 * @return
	 */
	public boolean addCourse(Course course) { 
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	public boolean addDepartment(Department department) { 
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * Only use for new professors. Department should already be added to department list.
	 * @return
	 */
	public boolean addProfessorToDepartment(Professor professor, Department department) {
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * Changes professor's department to the new one
	 * @param professor
	 * @param targetDepartment
	 * @return True if professor has been changed correctly, false otherwise
	 */
	public boolean changeProfessorDepartment(Professor professor, Department originalDepartment, Department targetDepartment) {
		return false; //TODO: Complete/update if necessary
	}


}
