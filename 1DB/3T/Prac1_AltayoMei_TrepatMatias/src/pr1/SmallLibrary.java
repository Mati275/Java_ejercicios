package pr1;

import java.util.*;

public interface SmallLibrary  {

	/**
	 *
	 * @return Returns the number of books the library has
	 */
	public int getNumBooks();


	/**
	 *
	 * @return Returns how many copies in total the library has. Remember
	 * that each book has a number of copies.
	 */
	public int totalNumCopies();


	/**
	 * Adds the given book to the library. Throws:
	 * - NullPointerException with a personalized message if book is null
	 * - UnexpectedDuplicateException if the library already contains a
	 * book equal to the argument
	 * @param book
	 */
	public void addBook (Book book);



	/**
	 * Removes book from library. Throws:
	 * - NullPointerException with a personalized message if book is null
	 * - UnknownBookException if book is not present in library (and, as
	 * a result, cannot be removed).
	 * @param book
	 */
	public void removeBook (Book book);


	/**
	 *
	 * @param tag
	 * @return Returns the book with the given tag. Returns null if no
	 * such a book exists.
	 */
	public Book getBook(BookTag tag);


	/**
	 *
	 * @param year
	 * @return List of books containing all the books in the library with
	 * a year equal to or later (greater) than the argument. The list is
	 * sorted by year (ascending). The list may be empty but cannot be
	 * null.
	 *
	 */
	public List<Book> booksFromYear (int year);


	/**
	 *
	 * @param word
	 * @return An array containing the books that have the given word in
	 * their titles. The array is sorted according to the natural
	 * ordering of the books (ascending).
	 * - The resulting array cannot have any null position.
	 * - Its length is 0 if no books contain the word.
	 *
	 */
	public Book [] containsWord(String word);


	/**
	 * Modifies the number of copies of the Book identified by tag.
	 * If there are no copies left after the modification, the book is
	 * deleted from the library.
	 * Throws:
	 * - UnknownBookException if the library does not contain any book
	 * with the given tag.
	 * @param tag
	 * @param num
	 * @return The number of copies of that book in the library
	 *
	 */
	public int modifyBookCopies (BookTag tag, int num);

}
