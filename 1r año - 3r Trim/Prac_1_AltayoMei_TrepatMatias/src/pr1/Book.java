package pr1;

public class Book implements Comparable<Book> {

	// ATTRIBUTES
	private BookTag tag;
	private int year;
	private String title;
	private int numCopies;

	// CONSTRUCTOR

	/**
	 * Creates a Book with a tag, year, title and one copy
	 * @param tag
	 * @param year
	 * @param title
	 */
	public Book (BookTag tag, int year, String title) {
		this.tag = tag;
		this.year = year;
		this.title = title.toUpperCase();
		this.numCopies = 1;
	}

	// GETTERS
	public BookTag getTag () {return this.tag;}
	public int getYear() {return this.year;}
	public String getTitle() {return this.title;}
	public int getNumCopies() {return this.numCopies;}

	// ************
	// OTHER FUNCTIONS
	// ************

	/**
	 * Adds num to numCopies. Can be used to increase or decrease number of copies by the given parameter.
	 * numCopies is always >=0
	 * @param num
	 * @return
	 */
	public int modifyNumCopies(int num) {
		numCopies = Math.max(0, numCopies+num);
		return numCopies;
	}
	
	@Override
	public int compareTo(Book other) {
		
		/* Books are sorted by booktag. Books with lowest booktags go first */

		// Lowest booktag --> Go first (Sorted in ascending order)
		return this.tag.compareTo(other.getTag());

	}
	
	@Override
	public boolean equals(Object arg) {
		if (arg == this) {
			return true;
		}
		if (!(arg instanceof Book)) {
			return false;
		}
		Book other = (Book) arg;
		return this.compareTo(other) == 0;
	}
	
	@Override
	public String toString () {
		return tag+"-("+year+")-"+title;
	}
	
	@Override
	public int hashCode () {
		return tag.hashCode();
	}
	
	@Override
	public Book clone () {
		return new Book(tag.clone(), year, new String(title));
	}
	
}
