package numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Ex01_TripleOccurrencesTest {

    @Test
    @DisplayName("Single occurrence of digit")
    void testSingleOccurrence() {
        assertEquals(1, Ex01_TripleOccurrences.countDigit(7, 7));
        assertEquals(1, Ex01_TripleOccurrences.countDigit(1234, 3));
    }

    @Test
    @DisplayName("Multiple normal occurrences")
    void testMultipleOccurrences() {
        assertEquals(3, Ex01_TripleOccurrences.countDigit(7177, 7));
        assertEquals(2, Ex01_TripleOccurrences.countDigit(1212, 1));
    }

    @Test
    @DisplayName("Special case: digit followed by two same digits to the left")
    void testSpecialTripleCountCase() {
        assertEquals(5, Ex01_TripleOccurrences.countDigit(111, 1));
    }

}
