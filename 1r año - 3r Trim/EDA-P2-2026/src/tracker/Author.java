package tracker;

/* DO NOT MODIFY THIS CLASS */
public class Author {
	
	private static int nextId = 1;
	
	private int id;
	private String name;
	
	public Author (String name) {
		this.name = name.toUpperCase();
		this.id = nextId;
		nextId++;
	}
	
	public String getName() {return this.name;}

	@Override
	public boolean equals (Object o) {
		try {
			return id==((Author)o).id;
		}
		catch(ClassCastException cce) {return false;}
	}

	@Override
	public int hashCode () {
		return id;
	}
	
}
