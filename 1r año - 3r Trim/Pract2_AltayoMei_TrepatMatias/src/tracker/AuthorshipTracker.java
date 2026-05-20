package tracker;

import java.util.*;

public interface AuthorshipTracker {

	/**
	 * Binds the given author with the books in the second argument. Books
	 * already bound to the author are not bound again.
	 *
	 * @param author
	 * @param books
	 * @return Number of newly bound books to the author.
	 */
	public int addBooksToAuthor (Author author, Collection<Book> books);

	/**
	 * Binds each author in the first argument with the given book. If an
	 * author is already bound to the book, no binding occurs for that author.
	 * Authors may or may not be previously known.
	 *
	 * @param authors
	 * @param book
	 * @return Returns the number of authors affected by the operation
	 */
	public int addAuthorsToBook (Collection<Author> authors, Book book);

	/**
	 *
	 * @param tag
	 * @return A SortedSet of all the authors of the given book, identified by
	 * tag. Result is sorted by author's name (ascending). If the book
	 * is unknown returns an empty set.
	 */
	public SortedSet<Author> findAuthors (BookTag tag);

	/**
	 *
	 * @param authorName
	 * @return A SortedSet containing all books by the given author. Result is
	 * sorted by title of the book (ascending). If the author is
	 * unknown returns an empty set.
	 */
	public SortedSet<Book> findBooks (String authorName);

	/**
	 *
	 * @param name1 Name of author 1
	 * @param name2 Name of author 2
	 * @return A SortedSet of books that the authors name1 and name2 have in
	 * common, sorted according to the natural order of books. If any
	 * of the authors is unknown the result is an empty set.
	 */
	public SortedSet<Book> findBooksInCommon (String name1, String name2);

}
