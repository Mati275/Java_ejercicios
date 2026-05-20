package pr1;

import java.util.*;

import pr1.exceptions.UnexpectedDuplicateException;
import pr1.exceptions.UnknownBookException;


public class MyCozyLibrary implements SmallLibrary {

	/* You must use a List. Never downcast the list when working with attribute allBooks*/
	// ATTRIBUTES
	private List<Book> allBooks;

	// CONSTRUCTOR

	/**
	 * Creates a void library
	 */
	public MyCozyLibrary() {
		allBooks = new LinkedList<>();
	}



	/* COMPLETE */


	// inner comparator class, compares the year of the books.
	private static class ByYearComparator implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {

			/* COMPLETE */

			return book1.getYear() - book2.getYear();

		}

	}


	@Override
	public int getNumBooks() {
		return allBooks.size(); // Gets the total books that this library has
	}

	@Override
	public int totalNumCopies() {
		int numCopies = 0;

		for (Book book : allBooks) {
			numCopies += book.getNumCopies(); // Adds all the number of copies that the library has book by book
		}

		return numCopies;
	}

	@Override
	public void addBook(Book book) {

		// The book is null --> throw NullPointerException
		if (book == null) {
			throw new NullPointerException("The book that is requested to be added is null");
		}

		// The book is on the list --> throw UnexpectedDuplicateException
		else if (allBooks.contains(book)) {
			throw new UnexpectedDuplicateException("The book that is requested to be added is already added to the library");
		}

		// The book is not null and is not on the library
		allBooks.add(book);

	}

	@Override
	public void removeBook(Book book) {

		// The book is null --> throw NullPointerException
		if (book == null) {
			throw new NullPointerException("The book that is requested to be removed is null.");
		}

		// The book is not on the library --> throw UnknownBookException
		else if (!(allBooks.contains(book))) {
			throw new UnknownBookException("The book: " + book + " that is requested to be deleted is not on the library");
		}

		// The book isn't null && is on the library
		allBooks.remove(book);

	}

	@Override
	public Book getBook(BookTag tag) {

		for (Book currentBook : allBooks) {

			// The tag of the book is the same of the parameters --> Return the book
			if (currentBook.getTag().equals(tag)) {
				return currentBook;
			}

		} // There isn't any book in the library with this tag --> Return null

		return null;
	}

	@Override
	public List<Book> booksFromYear(int year) {

		List<Book> booksFromYears = new LinkedList<>(); // Creating a void linkedList


		for (Book book : allBooks){

			// If the year of the book is greater or equal than the year in the param
			if (book.getYear() >= year){
				booksFromYears.add(book); // Add the book to the void list
			}
		}

		Collections.sort(booksFromYears, new ByYearComparator()); // Sort the books by year

		return booksFromYears;
	}

	@Override
	public Book[] containsWord(String word) {

		List<Book> booksContainingWord = new LinkedList<>();

		// This for is for getting all the books from the library list that cointains the word in parameters
		for (Book book : allBooks) {

			// The title contain the word in parameters --> Add that book to the local list
			if (book.getTitle().contains(word)) {
				booksContainingWord.add(book);
			}

		}

		Collections.sort(booksContainingWord);

		return booksContainingWord.toArray(new Book[0]); // Returns an array of books sorted and stored in the type of the array in the parameters
	}

	@Override
	public int modifyBookCopies(BookTag tag, int num) {
		Book book = getBook(tag);
		if (book == null) {
			throw new UnknownBookException("The book tag: " + tag + " passed to modify the number of copies is not on the library.");
		}

		return book.modifyNumCopies(num);

	}
}