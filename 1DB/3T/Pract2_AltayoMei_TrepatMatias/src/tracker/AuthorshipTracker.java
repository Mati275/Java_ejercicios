package tracker;

import java.util.*;

//CORRESPONDENCIA DE LOS AUTORES
public interface AuthorshipTracker {


	// Añadir los autores a los libros
	public int addBooksToAuthor (Author author, Collection<Book> books);
	// Si no estan se ponen todos, return los libros nuevos que se añaden


	// Añadir el libro a los autores
	public int addAuthorsToBook (Collection<Author> authors, Book book);
	// si el autor tiene el libro, no esta efectado, pero si no lo tenia,
	// lo añaden e incrementa el contador


	// Buscar autores de esos libros (Set ordenado)
	public SortedSet<Author> findAuthors (BookTag tag);
	// Se ordena por nombre de autores
	// se busca que libro se vea con el autor,
	// si no corresponde, se tiene que poner una lista vacia


	// Retorna libros ordenados
	public SortedSet<Book> findBooks (String authorName);
	// Retur el libro de autor por orden de titulo,
	// si autor desconocido, autor vacio


	//Retorna los libros que tienen en comun los autores
	public SortedSet<Book> findBooksInCommon (String name1, String name2);
	// Orden natural de los libros, si no conocemos return lista vacia

}
