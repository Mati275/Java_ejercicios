package domain;

import java.net.spi.InetAddressResolver;

public class University {
	
	//ATTRIBUTES
	private String name;
	private String address;
	private Course[] offeredCourses;
	private Student[] enrolledStudents;
	private Professor[] professors;
	private Department[] departments;
	 
	private int numCourses;
	private int numStudent;
	private int numProfessors;
	private int numDepartments;
	
	//ATTRIBUTES
	public University(String name, String address) {
		this.name = name;
		this.address = address;
		
		offeredCourses = new Course[10];
		enrolledStudents = new Student[100];
		professors = new Professor[30];
		departments = new Department[3];
		
		numCourses = 0;
		numStudent = 0;
		numProfessors = 0;
		numDepartments = 0;
		
	}
	
	//*******
	//METHODS
	//*******
	
	// GETTERS
	
	public String getName() {
		return name; 
	}
	
	
	public String getAddress() {
		return address; 
	}
	
	/**
	 * Returns true if department already exists, false otherwise
	 * @param department
	 * @return
	 */
	public boolean containsDepartment(Department department) {
		
		for (int i = 0; i < departments.length; i++) {
			if( departments[i].equals(department) ) {
				return true;
			}
		}
		
		return false; 
	}
	
	/**
	 * Returns true if professor is already linked to uni, false otherwise
	 * @param professor
	 * @return
	 */
	public boolean containsProfessor(Professor professor) {
		for (int i = 0; i < professors.length; i++) {
			if( professors[i].equals(professor) ) {
				return true;
			}
		}
		
		return false; 	
	
	}
	
	
	/**
	 * Returns true if course is already linked to uni, false otherwise
	 * @param course
	 * @return
	 */
	public boolean containsCourse(Course course) {
		for (int i = 0; i < offeredCourses.length; i++) {
			if( offeredCourses[i].equals(course) ) {
				return true;
			}
		}
		
		return false; 
		
	}
	
	
	/**
	 * Returns true if student is already linked to uni, false otherwise
	 * @param student
	 * @return
	 */
	public boolean containsStudent(Student student) {
		for (int i = 0; i < enrolledStudents.length; i++) {
			if( enrolledStudents[i].equals(student) ) {
				return true;
			}
		}
		
		return false; 
		
	}
	
	
	/**
	 * 
	 * @param course
	 * @return
	 */
	public boolean addCourse(Course course) { 
		
		// There isn't a duplicated value
		if ( !containsCourse(course) ) {
		
			for (int i = 0; i < offeredCourses.length; i++) {
				
				// There is a null value --> the idx where can be placed the new value
				if( offeredCourses[i] == null ) {
					offeredCourses[i] = course;
					numCourses ++;
					return true;
				}
			}
			
		} 
		
		// The array is full || there is a duplicated value
		return false; 
		
		
	}
	
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		
		// There isn't a duplicated value
		if ( !containsStudent(student) ) {
			
			for (int i = 0; i < enrolledStudents.length; i++) {

				// There is a null value --> the idx where can be placed the new value
				if( enrolledStudents[i] == null ) {
					enrolledStudents[i] = student;
					numStudent ++;
					return true;
				}
			}
			
		}
		
		// The array is full || there is a duplicated value
		return false; 
	}
	
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	public boolean addDepartment(Department department) { 
		
		// There isn't a duplicated value
		if ( !containsDepartment(department) ) {
			
			for (int i = 0; i < departments.length; i++) {

				// There is a null value --> the idx where can be placed the new value
				if( departments[i] == null ) {
					departments[i] = department;
					numDepartments ++;
					return true;
				}
			}
			
		}
		
		// The array is full || there is a duplicated value
		return false; 
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

	
	//*******
	// EXTRA METHODS
	//*******
	

	

}
