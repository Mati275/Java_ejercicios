package domain;

public abstract class Person  {

	//ATTRIBUTES
	private String name, lastName, username;
	private int id;

	private static int nextAvailableId; // It's static --> It's from the class Person (not for every instance of the object)
	
	//CONSTRUCTOR
	public Person(String name, String lastName, String username) {
		
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		
		id = nextAvailableId;
		nextAvailableId ++;
	}
	
	//*******
	//METHODS
	//*******
	
	// GETTERS (for attributes)
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getId() {
		return id;
	}
	
	
	

	// OTHER METHODS
	public abstract String getEmail();
	
	
	
	public String getDisplayName() {
		return name + " " + lastName;
	}

	
	@Override
	public boolean equals(Object obj) {
		Person person;
		
		if( !( obj instanceof Person ) ) {
			return false;
		}
		
		person = (Person) obj;
		
		if( this.id == person.id ) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Person ID: " + id + "\tName: " + getDisplayName() + "\tUsername: " + username;
	}
	
}
