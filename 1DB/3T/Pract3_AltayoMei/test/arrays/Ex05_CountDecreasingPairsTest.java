package arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Ex05_CountDecreasingPairsTest {

    @Test
    void testNoDecreasingPairs() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(0, Ex05_CountDecreasingPairs.countDecreasingPairs(arr));
    }

    @Test
    void testAllDecreasingPairs() {
        int[] arr = {5, 4, 3, 2, 1};
        assertEquals(4, Ex05_CountDecreasingPairs.countDecreasingPairs(arr));
    }

    @Test
    void testSomeDecreasingPairs() {
        int[] arr = {2, 1, 3, 2, 4, 3};
        assertEquals(3, Ex05_CountDecreasingPairs.countDecreasingPairs(arr)); // (2>1), (3>2), (4>3)
    }

    @Test
    void testEqualAdjacentElements() {
        int[] arr = {2, 2, 2, 2};
        assertEquals(0, Ex05_CountDecreasingPairs.countDecreasingPairs(arr));
    }

}
