package domain;

public class Student {
    
	//TODO: Complete with attributes and methods as described
	private String name;
	private String lastName;
	private String userName;
	private double grades [][];
	private Course courses [];
	private int numCourses;
	private static final int INCREMENT = 5;
	
	
	// CONSTRUCTOR
	public Student (String name, String lastName, String userName) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		
		courses = new Course [5];
		grades = new double [5][Course.MAX_ATTEMPTS];
		
		for (int i = 0; i < grades.length; i++) {
			for (int j = 0; j < grades[i].length; j++) {
				grades [i][j] = -1.0;
			}
		}
	}
	
	
	//*******
	//METHODS
	//*******
		
	// GETTERS
	
	public String getName() {
		return name;
	}

	
	public String getLastName() {
		return lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int totalEnrolledCourses() {
		return numCourses;
	}
	
	public double getLastGrade(Course course) {
		int idxCourse = getIdxCourse(course);
		
		for (int j = grades[idxCourse].length-1; j >= 0; j--) {
				if (grades [idxCourse][j] != -1) {
					return grades [idxCourse][j];
				}
			}
		
		return -1;
	}
	
	public double getGradeAverage () {
		double average = 0.0, lastGrade;
		int count = 0;

		
		// Suma de todas las notas
		for (int i = 0; i < grades.length; i++ ) {
			lastGrade = getLastGrade(courses[i]);
			if (lastGrade != -1) {
				average += lastGrade;
				count++;
			}
		} // fin del for
		
		if (count > 0 ) {
			return (average /=count);
		}		
		
		return -1.0;
	}
			
	
	public boolean addGradeToCourse (Course course, double grade) {
		int  idxCourse = getIdxCourse (course);

		// No encuentra el curso
		if (idxCourse == -1 ) {
			if (courses.length == numCourses) { //Mira si esta lleno
				//esta lleno --> tenemos que ampliar la matriz + poner el course + nota
				ampliateCourseAndGrades();
			} 
			//No esta lleno --> poner el course + nota
			courses [numCourses] = course;
			grades [numCourses][0] = grade;
			numCourses = numCourses + 1;
			
			return true;

		// Encuentra el curso
		} else {
			for (int j = 0; j < grades[idxCourse].length; j++) {
				if (grades[idxCourse][j] == -1) {
					grades[idxCourse] [j] = grade;
					return true;
				}
			}
	
			return false;
			
		}
				
		
	}
	
	
	
	
	//*******
	//EXTRA METHODS
	//*******
		
	private int getIdxCourse (Course course) {
		for (int i = 0; i < numCourses; i++) {
			if (courses[i] .equals(course)) {
				
				// return >= 0 --> Course found
				return i;
			}
				
		}
		
		// return -1 --> Course not found
		return -1; 
	}
		
	
	private void ampliateCourseAndGrades () {
		//Creas un vector y lo amplias
		Course moreCourses [] = new Course [courses.length + INCREMENT ];
		double moreGrades [][] = new double [grades.length + INCREMENT][grades[0].length];
		
		for (int i = 0; i <courses.length; i++) {
			moreCourses [i] = courses [i];
		}
		
		courses = moreCourses;
		
		for (int i = 0; i < grades.length; i++) { //Este solo copia las notas antes puestas
			for (int j = 0; j < grades[i].length; j++) {
				moreGrades [i][j] = grades [i][j];
			}
		}
		
		// ahora rellenar lo que falta con -1

		for (int i = grades.length; i < moreGrades.length; i++) { //Este solo copia las notas antes puestas
			for (int j = 0; j < moreGrades [i].length; j++) {
				moreGrades [i][j] = -1;
			}
		}
		
		grades = moreGrades;
		
	}
		
	
}
