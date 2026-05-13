package domain;

public class Professor {
		//TODO: Add attributes and methods as described
	
	private String name, lastName, userName, title;
	private double salary;
	
	
	//CONSTRUCTOR
	
	public Professor(String name, String lastName, String userName, 
			String title, double salary) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.title = title;
		this.salary = salary;
	}
	
	
	//GETTERS
	
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getSalary() {
		return salary;
	}
	
	
}
