package arrays;

import org.junit.jupiter.api.Test;

import static arrays.Ex04_AlternateArraySum.alternateArraySum;
import static org.junit.jupiter.api.Assertions.*;

public class Ex04_AlternateArraySumTest {

    @Test
    public void testEmptyArray() {
        assertEquals(0, alternateArraySum(new int[] {}));
    }


    @Test
    public void testLargeArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Expected: 1-2+3-4+5-6+7-8+9-10 = -5
        assertEquals(-5, alternateArraySum(arr));
    }

}
