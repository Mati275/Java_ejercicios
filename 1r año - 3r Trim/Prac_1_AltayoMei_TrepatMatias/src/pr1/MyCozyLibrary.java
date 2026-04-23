package pr1;

import java.util.*;

import pr1.exceptions.UnexpectedDuplicateException;
import pr1.exceptions.UnknownBookException;


public class MyCozyLibrary implements SmallLibrary
{

	/* You must use a List. Never downcast the list when working with attribute allBooks*/
	// ATTRIBUTES
	private final List<Book> allBooks; // TODO: CHECK IF WE CAN PUT FINAL HERE

	// CONSTRUCTOR

	/**
	 * Creates a void library
	 */
	public MyCozyLibrary () {
		allBooks = new LinkedList<>();
	}

	

	/* COMPLETE */

	
	// inner comparator class, compares the year of the books.
	private static class ByYearComparator implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {

			/* COMPLETE */

			// Both books have the same year
			if(book1.getYear() == book2.getYear()){
				return 0;
			}

			// The book1 is newer (grater) than the second one
			else if( book1.getYear() >= book2.getYear() ){
				return 1;
			}

			// The book1 is older (lower) than the second one
			else{
				return -1;
			}


		}
		
	}



	@Override
	public int getNumBooks() {
		return allBooks.size(); // Gets the total books that this library has
	}

	@Override
	public int totalNumCopies() {
		int numCopies = 0;

		for( Book book : allBooks ){
			numCopies += book.getNumCopies(); // Adds all the number of copies that the library has book by book
		}

		return numCopies;
	}

	@Override
	public void addBook(Book book) {

		// The book is null --> throw NullPointerException
		if(book == null){
			throw new NullPointerException("The book that is requested to be added is null");
		}


		for(Book currentBook: allBooks){
			// The book is on the library --> throw UnexpectedDuplicateException
			if(currentBook.equals(book)){
				throw new UnexpectedDuplicateException("The book that is requested to be added is already added to the library");
			}
		}

		// The book is not null and is not on the library
		allBooks.add(book);

	}

	@Override
	public void removeBook(Book book) {

		boolean removed = false;

		Iterator<Book> iterator = allBooks.iterator();


		// The book is null --> throw NullPointerException
		if(book == null){
			throw new NullPointerException("The book that is requested to be removed is null.");
		}

		while ( iterator.hasNext() ){
			// The book is on the library --> Delete the book
			if(iterator.next().equals(book)){
				iterator.remove();
				removed = true;
			}

		}
		// The book isn't on the library
		if( !removed ){
			throw new UnknownBookException("The book: " + book + " that is requested to be deleted is not on the library");
		}

	}

	@Override
	public Book getBook(BookTag tag) {

		for(Book currentBook: allBooks){

			// The tag of the book is the same of the parameters --> Return the book
			if(currentBook.getTag().equals(tag)){
				return currentBook;
			}

		} // There isn't any book in the library with this tag --> Return null

		return null;
	}

	@Override
	public List<Book> booksFromYear(int year) {

		// List<Book> booksFromYearList = new LinkedList<>(allBooks); --> Add all the books in that list, without doing "addAll(allBooks)"

		// Creates a list in ascending order by year (Lowest years first, highest number last)
		List<Book> booksFromYearList = new LinkedList<>();
		booksFromYearList.addAll(allBooks);

		// Sorts the booksFromYearlist
		Collections.sort( booksFromYearList, new ByYearComparator() ); // TODO: CHECK WHY IS IT REDUNDANT THE INTERFACE COMPARABLE

		// Creating the iterator
		Iterator<Book> iterator = booksFromYearList.iterator();

		while (iterator.hasNext()){

			/*
			 * The first elements of the list are the lowest number (of the year)
			 * Removing all the elements until it's found an element that it's year is grater or equals than the year of the parameter
			 */

			// Book's year is lower than the minimum
			if( iterator.next().getYear() < year) {
				iterator.remove();
			}
			// Book's year is equals or grater than the minimum (all the years are equals or grater than the parameter)
			else{
				return booksFromYearList;
			}



		} // All the years are lower than the year in parameter --> Return a void list (not a null value)

		return booksFromYearList;
	}

	@Override
	public Book[] containsWord(String word) {

		List<Book> booksContainingWord = new LinkedList<>();

		// This for is for getting all the books from the library list that cointains the word in parameters
		for(Book book : allBooks){

			// The title contain the word in parameters --> Add that book to the local list
			if( book.getTitle().contains(word) ){
				booksContainingWord.add(book);
			}

		}


		return booksContainingWord.toArray( new Book[0] ); // Returns an array of books sorted and stored in the type of the array in the parameters
	}

	@Override
	public int modifyBookCopies(BookTag tag, int num) {

		for(Book book : allBooks){
			if(book.getTag().equals(tag)){
				return book.modifyNumCopies(num);
			}
		}

		throw new UnknownBookException("The book tag: " + tag +  " passed to modify the number of copies is not on the library.");

	}

}
