package domain;

public class Student {
    
	//ATTRIBUTES

	
	private String name;
	private String lastName;
	private String username;
	
	private double [][] grades;
	private Course [] courses;
	private int numCourses;
	
	private static final int INCREMENT = 5;
	
	// CONSTRUCTOR
	public Student(String name, String lastName, String username) {
		
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		
		courses = new Course[5];
		grades = getVoidGradesArray( 5, Course.MAX_ATTEMPTS );
		
	}
	
	
	//*******
	//METHODS
	//*******
	
	// GETTERS
	
	public String getName() {
		return name;
	}
	
	public String getlastName() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	
	public int totalEnrolledCourses() {
		
		return numCourses; // TODO: CHECK IF THIS IS GOOD
		
	}
	
	public double getLastGrade(Course course) {
		int idxCourse;
		
		idxCourse = getIdxCourse(course);
		
		// The course it's found
		if ( idxCourse != -1 ) {
			
			// Start from the right int the row where the course is in the array of grades
			for(int j = grades[idxCourse].length - 1; j >= 0 ; j --) {
				
				if ( grades[idxCourse][j] != -1.0 ) {
					return grades[idxCourse][j];
				}
				
			} // All the grades of the course aren't valid 
			
		} // The course isn't found
		
		return -1.0; // The course isn't found or all the grades of the course aren't valid --> return -1.0
		
		
	}
	
	
	public double getGradeAvarage() {
		double currentGrade;
		double gradeAvarage = 0;
		int validCourses = 0;
		
		
		for (int i = 0; i < courses.length; i++) {
			currentGrade = getLastGrade(courses[i]); // Get the number of the lastGrade in the course that is being checked
			
			// If the course has any valid grade (the number is valid)
			if( currentGrade != -1.0 ) {
				gradeAvarage += currentGrade; // Add the number
				validCourses += 1; // Add 1 to the counter of valid courses
			}
			
		} // End of the for
		
		// If there is any valid course
		if( validCourses > 0 ) {
			gradeAvarage /= validCourses;
			return gradeAvarage;
		}
		
		// If there isn't any valid course
		return -1.0;
		
	}
	
	
	public boolean addGradeToCourse(Course course, double grade) {
		int idxCourse;
		idxCourse = getIdxCourse(course);
		
		// The course isn't on the array of courses
		if( idxCourse == -1 ) {
			
			// The array of courses is full --> make it bigger this array and grades array (keeping the information)
			if( coursesIsFull() ) {
				double [][] newGrades = getVoidGradesArray(grades.length + INCREMENT, Course.MAX_ATTEMPTS);
				Course [] newCourses = new Course[courses.length + INCREMENT];
				
				 // Fill both arrays
				
				
				
				
			}
			
			
			
			
		}
		
		
		return false;
	}
	
	
	//*******
	// EXTRA METHODS
	//*******
	
	// Returns and array of grades that has all it's values in -1.0 (the "null" values in this array are set in -1.0)
	private double[][] getVoidGradesArray( int rows, int columns ) {
		double [][] newGrades = new double[rows][columns];
		
		for(int i = 0; i < newGrades.length; i++) {
			for(int j = 0; j < newGrades[i].length; j++) {
				
				newGrades[i][j] = -1.0;
				
			}
		}
		
		return newGrades;
	}
	
//	private Course[] getVoidCoursesArray() {
//		Course[] newCourse = new double[rows][columns];
//		
//		for(int i = 0; i < newGrades.length; i++) {
//				
//			newCourse[i] = -1.0;
//				
//			
//		}
//		
//		return newGrades;
//	
//	}
	
	
	
	
	// Get the specific idx of the course in the array of courses
	private int getIdxCourse(Course course) {
		

		for(int i = 0; i < courses.length; i++) {
			
			if ( courses[i].equals(course) ) {
				return i;
			}
			
		} // The specific course isn't found
		
		return -1;
		
	}
	
	// Check if the array of courses is full
	private boolean coursesIsFull() {
		
		for (int i = 0; i < courses.length; i++) {
			
			if( courses[i] != null ) {
				return false;
			}
			
		}
		
		return true;
		
	}
	
	
}
