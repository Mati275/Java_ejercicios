package domain;

public class Professor extends Person{
		
	//ATTRIBUTES
	
	private String title;
	private double salary;
	
	
	//CONSTRUCTOR
	
	// Main constructor
	public Professor(String name, String lastName, String userName, 
			String title, double salary) {
		super(name, lastName, userName);
		
		this.title = title;
		this.salary = salary;
	}
	
	// Sub constructor, less parameters (Sobrecàrrega del constructor)
	public Professor(String name, String lastName, String userName, String title) {
		this(name, lastName, userName, title, 30000);
	}
	
	
	//*******
	//METHODS
	//*******
	
	//GETTERS

	public String getTitle() {
		return title;
	}
	
	public double getSalary() {
		return salary;
	}


	// OTHER METHODS
	
	@Override
	public String getDisplayName() {
		// The same than the other function, but here, adding the title
		return title + " " + super.getDisplayName();
	}
	
	@Override
	public String getEmail() {
		return getUsername() + "@university.edu";
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tSalary: " + salary;
	}
}
