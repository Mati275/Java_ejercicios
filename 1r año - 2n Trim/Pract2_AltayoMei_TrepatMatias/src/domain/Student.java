package domain;

public class Student extends Person{
    
	//ATTRIBUTES
	private double [][] grades;
	private Course [] courses;
	private int numCourses;
	
	private static final int INCREMENT = 5;
	
	//CONSTRUCTOR
	public Student(String name, String lastName, String username) {
		
		super(name, lastName, username);
		
		courses = new Course[5];
		
		
		grades = new double[5][Course.MAX_ATTEMPTS];
				
		// Fill the array of grades
		for (int i = 0; i < grades.length; i ++) {
			
			for (int j = 0; j < grades[i].length; j++) {
				grades[i][j] = -1.0;
			}
			
		}
		
	}
	
	
	//*******
	//METHODS
	//*******
	
	// GETTERS	
	public int totalEnrolledCourses() {
		return numCourses;
	}
	
	
	// OTHER METHODS
	
	
	public double getLastGrade(Course course) {
		int idxCourse;
		
		idxCourse = getIdxCourse(course);
		
		// The course it's found --> idxCourse > -1
		if ( courses[idxCourse].equals(course) ) {
			
			// Start from the right in the row where the course is in the array of grades
			for(int j = grades[idxCourse].length - 1; j >= 0 ; j --) {
				
				// Find a valid grade (the last one)
				if ( grades[idxCourse][j] != -1.0 ) {
					return grades[idxCourse][j];
				}
				
			} // All the grades of the course aren't valid 
			
		} // The course isn't found
		
		return -1.0; // The course isn't found or all the grades of the course aren't valid --> return -1.0
		
		
	}
	
	
	public double getGradeAverage() {
		double currentFinalGrade; // La nota amb la que s'està operant en cada iteració
		double gradeAvarage = 0; 
		
		int validCourses = 0; // Comptador de cuantes assignatures vàlides té l'estudiant
		
		// Make a walkthrough until the last matriculate course
		for (int i = 0; i < numCourses; i++) {
			currentFinalGrade = getLastGrade(courses[i]); // Get the number of the lastGrade in the course that is being checked
			
			// If the course has any valid grade (the number is valid)
			if( currentFinalGrade != -1.0 ) {
				gradeAvarage += currentFinalGrade; // Add the number
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
			
			// CASE 1: The array of courses is full && the course isn't on the array of courses --> make it bigger this array and grades array (keeping the information)
			if( courses.length == numCourses ) {
				ampliateCoursesAndGrades(); // Ampliate the arrays
			}
			
			// CASE 2: The array of courses isn't full && the course isn't on the array of courses (also the case one execute that code)
				
			// Put the course and the grade in both arrays
			courses[numCourses] = course; // The first null position of the array add the course
			grades[numCourses][0] = grade; // The first unvalid grade in the array of grades in column 0
			
			

			
			numCourses ++;
				
			return true;
			}
		
		
		
		// The course is on the array of courses -> idxCourse != -1
		else {
			
			for( int j = 0; j < grades[idxCourse].length; j ++ ) {
				// CASE 3:  The course is on the array of courses && it's possible to add one more grade
				if( grades[idxCourse][j] == -1.0 ) {		
					grades[idxCourse][j] = grade;

					return true;
				}
			}
			
			// CASE 4:  The course is on the array of courses  && it's NOT possible to add one more grade
			return false;
			
		}
	}
	
	
	@Override
	public String getEmail() {
		// TODO: Revisar si han de ser atributs privats o protected els de la classe Person
		return getUsername() + "@stu.university.edu";
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tNum courses: " + numCourses;
	}
	
	
	//*******
	// EXTRA METHODS
	//*******
	
	
	// Get the specific idx of the course in the array of courses
	private int getIdxCourse(Course course) {
		

		for(int i = 0; i < numCourses; i++) {
			
			if ( courses[i].equals(course) ) {
				return i;
			}
			
		} // The specific course isn't found
		
		return -1;
		
	}
	
	private void ampliateCoursesAndGrades() {
		
		double[][] newGrades = new double[grades.length + INCREMENT][grades[0].length];
		Course[] newCourses = new Course[courses.length + INCREMENT];
		
		// Fill newGrades (with the array that was created before)
		
		for ( int i = 0; i < grades.length ; i++ ) {
			
			for (int j = 0; j < grades[i].length; j++) {
				newGrades[i][j] = grades[i][j];
			}
			
		}
		
		// Fill the null values to -1.0 
		for ( int i = grades.length; i < newGrades.length ; i++ ) {
			
			for (int j = 0; j < newGrades[i].length; j++) {
				newGrades[i][j] = -1.0;
			}
			
		}
		
		
		
		// Fill newCourses
		for ( int i = 0; i < courses.length ; i++ ) {
			
			newCourses[i] = courses[i];
			
		}
		// Don't mind if there are null values in the array of new courses
		
		
		// Asign the new arrays to the attributes
		grades = newGrades;
		courses = newCourses;	
		
		

	}
	
	
}
