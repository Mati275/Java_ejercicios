package tracker;

import java.util.*;

public class AuthorshipTrackerMap implements AuthorshipTracker{ //TODO: Complete header (Implementa la interficie)

	private Map<Author, Set<Book>> authorBookMap;

	/*
		autorBookMap.put (author, book); no se puede ya que es una lista
		autorBookMap.put (author, set<book>); espera un set
	 */
	public AuthorshipTrackerMap() {
		this.authorBookMap = new HashMap<>();
	}

	/* Complimentary method */
	private Author findAuthorByName(String name) {
		for (Author a : authorBookMap.keySet())
			if (name.equalsIgnoreCase(a.getName())) {
				return a;
			}
		return null;
	}


	@Override
	public int addBooksToAuthor(Author author, Collection<Book> books) {
		int count = 0;
		Set<Book> authorBook;

		// if the author is inside the map
		if( authorBookMap.containsKey(author) ){

			authorBook = authorBookMap.get(author); // get author's books

			// Iterate the collections of books in parameters
			for (Book book : books){

				// If the book is on the author's books --> Add the book and count it
				if ( ! (authorBook.contains(book)) ){
					authorBook.add(book);
					count++;
				}
			}
			return count;

		} // If the author isn't on the map

		authorBookMap.put(author, new HashSet<Book>( books )); // Put the author and creates a hashSet with the values of the collection in parameters and adds it to the map
		return books.size(); // All the books on the parameters are added


	}

	@Override
	public int addAuthorsToBook(Collection<Author> authors, Book book) {
		int count = 0;
		Set<Book> authorBook;

		for (Author author : authors){
			// If the author is on the map
			if( authorBookMap.containsKey(author)){

				authorBook = authorBookMap.get(author); // Get the reference of memory of the set of books that this author has

				// If the author don't contain the book in parameters
				if  ( ! (authorBook.contains(book)) ){
					authorBook.add(book);
					count++;
				}

			}

			// If the author isn't on the map
			else{
				authorBookMap.put(author, new HashSet<>()); // Put the author on the map

				authorBook = authorBookMap.get(author); // Get the reference of memory of the set of books that this author has

				authorBook.add(book);
				count++;
			}

		}

		return count;
	}


	@Override
	public SortedSet<Author> findAuthors(BookTag tag) {
		SortedSet <Author> sortedAuthors = new TreeSet<>(new AuthorNameComparator());

		// Iterate each entry of the map
		for (Map.Entry<Author,Set<Book>> entry : authorBookMap.entrySet()) {

			// Obtain each book of each author
			for (Book book : entry.getValue()) {

				// If the bookTag is the same
				if (book.getTag().equals(tag)) {
					sortedAuthors.add(entry.getKey());

				}
			}
		}

		return sortedAuthors;
	}

	@Override
	public SortedSet<Book> findBooks(String authorName) {
		SortedSet<Book> sortedBook = new TreeSet<>( new BookTitleComparator() );

		// Iterate the keySet of the map (a Set of all the authors )
		for ( Author author : authorBookMap.keySet() ) {

			// If the authorName (in parameters) is the same as one author on the map
			if( author.getName().equalsIgnoreCase(authorName) ){
				sortedBook.addAll(authorBookMap.get( author )); // Add the books on "sortedBook" to sort the books of the author
				return sortedBook;
			}

		}

		return sortedBook; // Returns a void SortedSet
	}

	@Override
	public SortedSet<Book> findBooksInCommon(String name1, String name2) {
		SortedSet<Book> sortedBook = new TreeSet<>();

		// Using the complimentary method to find the object of the author
		Author author1 = findAuthorByName( name1 );
		Author author2 = findAuthorByName( name2 );

		Set<Book> author2Books = authorBookMap.get(author2);

		// Iterate the books of the author1
		for( Book book : authorBookMap.get(author1) ){
			// If one of the books of the author2 is the same as one book of the author1
			if( author2Books.contains(book) ){
				sortedBook.add(book); // Add the book to the sortedBook
			}
		}

		return sortedBook;
	}

	public static class BookTitleComparator implements Comparator<Book> {
		@Override
		public int compare(Book book1, Book book2) {
			return book1.getTitle().compareTo( book2.getTitle() ); // Compare the books depending on the title
		}

	}

	public static class AuthorNameComparator implements Comparator<Author>{

		@Override
		public int compare(Author author1, Author author2) {
			return author1.getName().compareTo(author2.getName()); // Compare the authors depending on the name
		}


	}

}