package tracker;

import java.util.*;

public class AuthorshipTrackerMap implements AuthorshipTracker{ //TODO: Complete header (Implementa la interficie)

	/* DO NOT MODIFY THIS */
	private Map<Author, Set<Book>> authorBookMap;
	/* DO NOT ADD MORE ATTRIBUTES */

	//Constructor

	/*
		autorBookMap.put (author, book); no se puede ya que es una lista
		autorBookMap.put (author, set<book>); espera un set
	 */
	public AuthorshipTrackerMap() {
		/* COMPLETE */
		this.authorBookMap = new HashMap<>();
	}
	
	/* COMPLETE */

	/* Complimentary method */ //Esta hecho
	private Author findAuthorByName(String name) {
		for (Author a : authorBookMap.keySet())
			if (name.equalsIgnoreCase(a.getName())) {
				return a;
			}
		return null;
	}

	/**
	 *
	 * @param author
	 * @param books
	 * @return
	 */
	@Override
	public int addBooksToAuthor(Author author, Collection<Book> books) {
		int count = 0;
		Set<Book> authorBook = authorBookMap.get(author);

		for (Book book : books){

			if ( ! (authorBook.contains(book)) ){
				authorBook.add(book);
				count++;
			}
		}
		return count;
	}

	/**
	 *
	 * @param authors
	 * @param book
	 * @return
	 */
	@Override
	public int addAuthorsToBook(Collection<Author> authors, Book book) {
		int count = 0;
		Set<Book> authorBook;

		for (Author author : authors){
			authorBook = authorBookMap.get(author);

			if  ( ! (authorBook.contains(book)) ){
				authorBook.add(book);
				count++;
			}
		}
		return count;
	}


	/**
	 *
	 * @param tag
	 * @return
	 */
	@Override
	public SortedSet<Author> findAuthors(BookTag tag) {
		SortedSet <Author> sortedAuthors = new TreeSet<>(new AuthorNameComparator());

		for (Map.Entry<Author,Set<Book>> entry : authorBookMap.entrySet()) {
			for (Book book : entry.getValue()) {

				if (book.getTag().equals(tag)) {
					sortedAuthors.add(entry.getKey());

				}
			}
		}

		return sortedAuthors;
	}

	/**
	 *
	 * @param authorName
	 * @return
	 */
	@Override
	public SortedSet<Book> findBooks(String authorName) {
		SortedSet<Book> sortedBook = new TreeSet<>();

		for (Author author : authorBookMap.keySet()) {

			if (author.getName().equalsIgnoreCase(authorName)) {
				sortedBook.addAll(authorBookMap.get(author));
				return sortedBook;
			}

		}

		return sortedBook;
	}

	@Override
	public SortedSet<Book> findBooksInCommon(String name1, String name2) {
		return null;
	}

	// Implementar comparator
	public static class BookTitleComparator { //TODO: Complete header (implementa interficie comparator)
		//TODO: Write a comparator that compares book titles


	}

	// Implementar comparator
	public static class AuthorNameComparator { //TODO: Complete header
		//TODO: Write a comparator that compares author names


	}
	
}
