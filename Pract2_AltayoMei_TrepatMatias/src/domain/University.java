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
		
		// There isn't a duplicated value && The array isn't full (the next idx value is valid)
		if ( !containsCourse(course) && numCourses < offeredCourses.length) {
		
			offeredCourses[numCourses] = course;
			numCourses ++;
			
			return true;
		} 
		
		// There is a duplicated value || the array is full 
		return false; 
		
		
	}
	
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		
		// There isn't a duplicated value && The array isn't full (the next idx value is valid)
		if ( !containsStudent(student) && numStudent < enrolledStudents.length) {
		
			enrolledStudents[numCourses] = student;
			numStudent++;
			
			return true;
		} 
		
		// There is a duplicated value || the array is full 
		return false; 
	}
	
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	public boolean addDepartment(Department department) { 
		
		// There isn't a duplicated value && The array isn't full (the next idx value is valid)
		if ( !containsDepartment(department) && numDepartments < departments.length) {
		
			departments[numCourses] = department;
			numDepartments++;
			
			return true;
		} 
		
		// There is a duplicated value || the array is full 
		return false;
	}
	
	
	
	
	/**
	 * Only use for new professors. Department should already be added to department list.
	 * @return
	 */
	public boolean addProfessorToDepartment(Professor professor, Department department) {
		
		// There isn't a duplicated value &&  the department isn't full && The array isn't full (the next idx value is valid)
		if( !containsProfessor( professor ) && !department.isFull() && numProfessors < professors.length) {
			
			professors[numProfessors] = professor;
			numProfessors ++;
			
			department.addProfessor(professor);
			return true;
			
		}
		
		// There is a duplicated value || the department is full || the array is full 
		return false; 
		
	}
	
	
	/**
	 * Changes professor's department to the new one
	 * @param professor
	 * @param targetDepartment
	 * @return True if professor has been changed correctly, false otherwise
	 */
	public boolean changeProfessorDepartment(Professor professor, Department originalDepartment, Department targetDepartment) {
		
		// The department contains the professor &&  the new department isn't full
		if( originalDepartment.findProfessor(professor) != -1 && !targetDepartment.isFull() ) {
			
			originalDepartment.removeProfessor(professor);
			targetDepartment.addProfessor(professor);
			
			return true;
			
		}
		
		// The originalDepartment don't contain the professor || the targetDepartment is full
		return false; 		
	}

	

}
