package domain;

public class University {
	
	//TODO: Add attributes here
	private String name;
	private String address;
	private Course offerdCourses [];
	private Student enrolledStudent [];
	private Professor professors [];
	private Department departments [] ;
	
	
	//Posiciones palans validas
	
	
	//estas son posiciones que esta vacia
	private int numCourses; //Nombre de asignaturas ofertes
	private int numStudent; // nombre estudiantes matriculados
	private int numProfessors; // Numero de profesores que trabajan en la uni
	private int numDepartments; //Numero de departamentos
 	
	
	// CONSTRUCTOR
	public University(String name, String address) {
		//TODO: Complete
		this.name = name;
		this.address = address;
		
		offerdCourses = new Course [10]; // 10
		enrolledStudent = new Student [100]; // 100
		professors = new Professor [30]; // 30
		departments = new Department [3]; // 3
		
	}
	
	
	
	//*******
	//METHODS
	//*******
		
	
	public String getName() {
		return name; //TODO: Complete/update if necessary
	}
	
	
	public String getAddress() {
		return address; //TODO: Complete/update if necessary
	}
	
	/**
	 * Returns true if department already exists, false otherwise
	 * @param department
	 * @return
	 */
	public boolean containsDepartment(Department department) {
		// buscar el departamento si esta
		for (int i = 0; i < numDepartments; i++) {
			if (department.equals(departments [i])) {
				return true;
			}
		}	
		
		return false; //TODO: Complete/update if necessary
	}
	
	/**
	 * Returns true if professor is already linked to uni, false otherwise
	 * @param professor
	 * @return
	 */
	public boolean containsProfessor(Professor professor) {
		// buscar el profesor si esta
		for (int i = 0; i < numProfessors; i++) {
			if (professor.equals(professors [i])) {
				return true;
			}
		}	
		
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * Returns true if course is already linked to uni, false otherwise
	 * @param course
	 * @return
	 */
	public boolean containsCourse(Course course) {
		// buscar el curso si esta
		for (int i = 0; i < numCourses; i++) {
			if (course.equals(offerdCourses [i])) {
				return true;
			}
		}	
		
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * Returns true if student is already linked to uni, false otherwise
	 * @param student
	 * @return
	 */
	public boolean containsStudent(Student student) {
		// buscar el estudiante si esta
		for (int i = 0; i < numStudent; i++) {
			if (student.equals(enrolledStudent [i])) {
	
				return true;
			}
		}	
		
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param course
	 * @return
	 */
	public boolean addCourse(Course course) { 
		if (!containsCourse (course)) {
			for (int i = 0; i < offerdCourses.length ; i++) {
				if (offerdCourses[i] == null) { // Si no esta lleno
					course = offerdCourses [i];
					return true; //Entra cada vez que si un lugar esta vacío
				} 
			}	
		}
				
		// Si esta lleno return false
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param student
	 * @return
	 */
	public boolean addStudent(Student student) {
		if (!containsStudent (student)) {
			for (int i = 0; i < enrolledStudent.length ; i++) {
				if (enrolledStudent[i] == null) { // Si no esta lleno
					student = enrolledStudent [i];
					return true; //Entra cada vez que si un lugar esta vacío
				} 
			}	
		}
				
		// Si esta lleno return false
		return false; //TODO: Complete/update if necessary
	}
	
	
	/**
	 * 
	 * @param department
	 * @return
	 */
	public boolean addDepartment(Department department) { 
		if (!containsDepartment(department)) {
			for (int i = 0; i < departments.length ; i++) {
				if (departments[i] == null) { // Si no esta lleno
					department = departments [i];
					return true; //Entra cada vez que si un lugar esta vacío
				} 
			}	
		}
				
		// Si esta lleno return false
		return false; //TODO: Complete/update if necessary
	}

	
	/**
	 * Only use for new professors. Department should already be added to department list.
	 * @return
	 */
	public boolean addProfessorToDepartment(Professor professor, Department department) {
		for (int i = 0; i < professors.length; i++) { //recorre los profesores
			//Pertetece a la univerdidad
			if (professors[i].equals(professor)) {
				return false;
			}
		}
		
//		//Departamento si esta lleno
		for (int i = 0; i < departments.length; i++) { 
			if (departments[i] != null) {
				return false;
			}
		}
		
		// max de prof
		for (int i = 0; i < professors.length; i++) { 
			if (professors[i] == null) {
				professors[i] = professor; 
				return true; 
			} 
		}
				
				
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
