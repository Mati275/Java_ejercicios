package tracker;


public class Book implements Comparable<Book> {

	private BookTag tag;
	private int year;
	private String title;
	private int copies;

	public Book (BookTag tag, int year, String title) {
		this.tag = tag;
		this.year = year;
		this.title = title.toUpperCase();
		this.copies = 1;
	}

	public BookTag getTag () {return this.tag;}
	public int getYear() {return this.year;}
	public String getTitle() {return this.title;}
	public int getCopies() {return this.copies;}

	public int addCopy () {
		this.copies++;
		return this.copies;
	}

	public int removeCopy() {
		this.copies--;
		if (copies<0) throw new IllegalStateException("book with negative copies!!! "+this);
		return this.copies;
	}

	@Override
	public int compareTo(Book other) {
		/* Books are sorted by booktag. Books with lowest booktags go first */

		if (this.tag.compareTo(other.getTag()) == 0) {  return 0; }
		else if (this.tag.compareTo(other.getTag()) > 0) { return 1; }
		else { return -1; }

	}

	@Override
	public boolean equals(Object arg) {
		try {
			return this.compareTo((Book)arg)==0;
		}
		catch (ClassCastException cce) {
			return false;
		}
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