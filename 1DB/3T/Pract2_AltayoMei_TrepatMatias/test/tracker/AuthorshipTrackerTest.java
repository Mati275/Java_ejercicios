package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AuthorshipTrackerTest {
    private Author[] authors =  {
        new Author("Mary Doe"),
                new Author("Natalie Figgs"),
                new Author("Pamela Hat"),
                new Author("Carlos Brown"),
                new Author("John Smith")
    };
    private List[] initialLists;
    private List[] fullLists;
    private Book [] books;
    private List[] authorsPerBook;
    private AuthorshipTracker mau;

    @BeforeEach
    void setUp(){
        BookTag [] sortedTags = {
                new BookTag("AAAA", 23, "ZZ"),
                new BookTag("AAAA", 28, "ZZ"),
                new BookTag("AAAA", 28, "JJ"),
                new BookTag("AAAA", 28, "BA"),
                new BookTag("AAAK", 23, "ZZ"),
                new BookTag("AAAK", 28, "ZZ"),
                new BookTag("AAAK", 28, "JJ"),
                new BookTag("AAAK", 28, "BA"),
                new BookTag("DAAA", 21, "BB"),
                new BookTag("DAAA", 22, "CC"),
                new BookTag("DAAA", 23, "JJ"),
                new BookTag("DAAA", 24, "ZZ"),
                new BookTag("TAAA", 99, "RR")
        };



        String [] titles = {
                "monster-sized lists", // 0
                "exposed attributes of a shy collection", // 1
                "Eden Garden of data iterators", // 2
                "arraylists in action", // 3
                "a vector with two arrays", // 4
                "two arrays and a list with a vector", // 5
                "linkedlists store it better", // 6
                "a house of full lists", // 7
                "glamourous collections", // 8
                "after-school hours of a collection", // 9
                "Iterate me after class!", // 10
                "ArrayLists shows their attributes", // 11
                "fully-equipped iterators" // 12
        };

        int [] years = {
                1996,
                1996,
                2000, // 2
                2000,
                2000,
                2005, // 5
                2005,
                2010, // 7
                2011, // 8
                2012, // 9
                2018, // 10
                2018,
                2019 // 12
        };


        // shuffle(authors);


        books = new Book[sortedTags.length];

        for (int i=0; i<sortedTags.length; i++) {
            books[i] = new Book(sortedTags[i], years[i], titles[i]);
        }

        // shuffle(books);

        createFullBookLists();

        createInitialBookLists();

        mau = new AuthorshipTrackerMap();

    }

    @Test
    void addBooksToAuthors_returnsNumberOfBooksAdded(){
        for (int i=0; i<authors.length; i++ ) {
            int numAdded = mau.addBooksToAuthor(authors[i],initialLists[i]);
            assertEquals(initialLists[i].size(),numAdded);
        }
    }


    @Test
    void addAuthorsToBook_returnsNumberOfAuthorsAdded(){
        givenAuthorsPerBookPrepared();
        assertEquals(3,mau.addAuthorsToBook(authorsPerBook[4], books[4]));
        assertEquals(3,mau.addAuthorsToBook(authorsPerBook[7], books[7]));
        assertEquals(2,mau.addAuthorsToBook(authorsPerBook[8], books[8]));
    }


    @Test
    void findAuthors_unknownBook_returnsEmptySet() {
        givenStandardDataset();

        assertEquals(0,mau.findAuthors(new BookTag("XXXX", 79, "XX")).size());
    }

    @Test
    void findAuthors_knownBooks_returnsCorrectAuthors() {
        givenStandardDataset();

        for (int i=0; i<books.length; i++) {
            Set<Author> authorSet = mau.findAuthors(books[i].getTag().clone());
            List<Author> result = new LinkedList<>(authorSet);

            assertEquals(authorsPerBook[i].size(),result.size());
            //Double inclusion
            assertTrue(result.containsAll(authorsPerBook[i]));
            assertTrue(authorsPerBook[i].containsAll(result));
        }
    }

    @Test
    void findAuthors_knownBooks_returnsAuthorsSortedByName() {
        givenStandardDataset();

        for (int i = 0; i < books.length; i++) {
            List<Author> result = new LinkedList<>(mau.findAuthors(books[i].getTag().clone()));

            assertTrue(sortedByName(result));
        }
    }

    @Test
    void findBooks_knownAuthor_returnsCorrectBooks(){
        givenStandardDataset();
        for (int i=0; i<authors.length; i++) {
            List<Book> result = new LinkedList<>(mau.findBooks(new String(authors[i].getName())));

            assertEquals(fullLists[i].size(),result.size());
            assertTrue(result.containsAll(fullLists[i]));
            assertTrue(fullLists[i].containsAll(result));
        }
    }


    @Test
    void findBooksInCommon_knownAuthors_returnsBooksInCommon(){
        givenStandardDataset();
        for (int i=0; i<authors.length; i++) {
            for (int j=i+1; j<authors.length; j++) {
                List<Book> expected = new ArrayList<Book>(fullLists[i]);
                expected.retainAll(fullLists[j]);
                Set<Book> result = mau.findBooksInCommon(new String(authors[i].getName()), new String(authors[j].getName()));

                assertEquals(expected.size(),result.size());
                assertTrue(expected.containsAll(result));
                assertTrue(result.containsAll(expected));
            }
        }
    }

    @Test
    void findBooksInCommon_knownAuthors_returnsBooksSorted(){
        givenStandardDataset();
        for (int i=0; i<authors.length; i++) {
            for (int j=i+1; j<authors.length; j++) {
                List<Book> expected = new ArrayList<Book>(fullLists[i]);
                expected.retainAll(fullLists[j]);
                Set<Book> result = mau.findBooksInCommon(new String(authors[i].getName()), new String(authors[j].getName()));

                assertEquals(expected.size(),result.size());
                assertTrue(sortedNatural(new ArrayList<>(result)));

            }
        }
    }

    private void createFullBookLists(){
        fullLists = new List[]{
                asList(books[0], books[1], books[2], books[3], books[4]),
                asList(books[4], books[5], books[6], books[7], books[8]),
                asList(books[7], books[8], books[9], books[10], books[11]),
                asList(books[4], books[7], books[12]),
                Collections.emptyList()
        };
    }

    private void createInitialBookLists(){
        initialLists = new List[]{
                asList(books[0], books[1], books[2], books[3]),
                asList(books[5], books[6]),
                asList(books[9], books[10], books[11]),
                asList(books[12]),
                Collections.emptyList()
        };
    }

    private void prepareAuthorsForBooks(){

        authorsPerBook = new List[]{
                asList(authors[0]),
                asList(authors[0]),
                asList(authors[0]),
                asList(authors[0]),
                asList(authors[0], authors[1], authors[3]), //books[4]
                asList(authors[1]),
                asList(authors[1]),
                asList(authors[1], authors[2], authors[3]), //books[7]
                asList(authors[1], authors[2]), //books[8]
                asList(authors[2]),
                asList(authors[2]),
                asList(authors[2]),
                asList(authors[3]),
                asList(authors[3])
        };

    }


    private void givenAuthorsAssignedToInitialBooks() {
        for (int i = 0; i < authors.length; i++) {
            mau.addBooksToAuthor(authors[i], initialLists[i]);
        }
    }

    private void givenAuthorsPerBookPrepared() {
        prepareAuthorsForBooks();
    }

    private void givenAdditionalAuthorsAssigned() {
        mau.addAuthorsToBook(authorsPerBook[4], books[4]);
        mau.addAuthorsToBook(authorsPerBook[7], books[7]);
        mau.addAuthorsToBook(authorsPerBook[8], books[8]);
    }

    private void givenStandardDataset() {
        givenAuthorsPerBookPrepared();
        givenAuthorsAssignedToInitialBooks();
        givenAdditionalAuthorsAssigned();
    }


    private <T> List<T> asList(T... items){
        return Arrays.asList(items);
    }

    private static boolean sortedNatural (List<Book> list) {
        if (list.size()<=1) return true;
        Book current = list.get(0);
        for (int i=1; i<list.size(); i++) {
            if (current.compareTo(list.get(i))>0)
                return false;
            current = list.get(i);
        }
        return true;
    }

    private boolean sortedByName (List<Author> list) {
        if (list.size()<=1) return true;
        String current = list.get(0).getName();
        for (int i=1; i<list.size(); i++) {
            if (!(current.compareTo(list.get(i).getName())<=0))
                return false;
            current = list.get(i).getName();
        }
        return true;
    }

}
