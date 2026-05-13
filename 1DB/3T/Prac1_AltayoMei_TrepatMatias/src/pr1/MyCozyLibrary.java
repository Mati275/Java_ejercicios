package pr1;

import java.util.*;

import pr1.exceptions.UnexpectedDuplicateException;
import pr1.exceptions.UnknownBookException;


public class MyCozyLibrary implements SmallLibrary //TODO: Complete header
{

	/* You must use a List. Never downcast the list when working with attribute allBooks*/
	private List<Book> allBooks;

	/**
	 * Constructor
	 */
	public MyCozyLibrary () {
		/* COMPLETE */
		allBooks = new LinkedList<>();
	}

	@Override
	public int getNumBooks() {
		return allBooks.size();
	}

	@Override
	public int totalNumCopies() {
		int count = 0;

		for (Book b : allBooks){
			count += b.getNumCopies();
		}

		return count;
	}

	@Override
	public void addBook(Book book) {
		if (book == null){
			throw new NullPointerException ("Se intento añadir un libro nulo: " + book);
		}
		else if (allBooks.contains(book)){
			throw new UnexpectedDuplicateException("El libro duplicado és: " + book);
		}
		else {
			allBooks.add(book);
		}

	}

	@Override
	public void removeBook(Book book) {
		if (book == null){
			throw new NullPointerException ("Se intento eliminar un libro nulo: " + book);
		}
		else if (!(allBooks.contains(book))) {
			throw new UnknownBookException( "El libro no se encuentra en la libreria, no se puede eliminar: " + book);
		}
		else {
			allBooks.remove(book);
		}
	}

	@Override
	public Book getBook(BookTag tag) {
		for ( Book book : allBooks){
			if (book.getTag().equals(tag)){ return book; }
		}
		return null;
	}

	@Override
	public List<Book> booksFromYear(int year) {
		List<Book> booksFromYears = new LinkedList<>();

		for (Book book : allBooks){
			if (book.getYear() >= year){
				booksFromYears.add(book);
			}
		}

		Collections.sort(booksFromYears, new ByYearComparator());

		return booksFromYears;
	}

	@Override
	public Book[] containsWord(String word) {

		List<Book> booksContainsWord = new LinkedList<>(); // --> Lista vacia

		for (Book book : allBooks){
			if (book.getTitle().contains(word)){
				booksContainsWord.add(book);
			}
		}

		Collections.sort(booksContainsWord);

		return booksContainsWord.toArray(new Book[0]); //Longitud minima
		/* Crea un array a partir de una lista, la array creada
		 entra dentro de la array que pasas por parametros
		 (tmb la array pasada por parametros, indica el tipo que es
		 el tamaño de la lista, es superior el tamaño mayor,
		 se crea un array que tenga el tamaño justo
		*/

	}

	@Override
	public int modifyBookCopies(BookTag tag, int num) {
		Book book = getBook(tag);
		if (book == null) {
			throw new UnknownBookException("The book tag: " + tag +  " passed to modify the number of copies is not on the library.");
		}

		return book.modifyNumCopies(num);

	}

	/* COMPLETE */

	
	// inner comparator class.
	private static class ByYearComparator implements Comparator<Book> {

		@Override
		public int compare(Book book1, Book book2) {

			/* COMPLETE */
			if (book1.getYear() > book2.getYear()) { return 1; }
			else if (book1.getYear() == book2.getYear()) { return 0;}
			else { return -1; }

			// return book1.getYear() - book2.getYear();

		}
	}
}
