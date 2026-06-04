package numbers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex03_RemoveDuplicateDigitsTest {

    @Test
    public void testNoDuplicates() {
        assertEquals(12345, Ex03_RemoveDuplicateDigits.removeDuplicateDigits(12345));
    }

    @Test
    public void testAllSameDigits() {
        assertEquals(7, Ex03_RemoveDuplicateDigits.removeDuplicateDigits(7777777));
    }

    @Test
    public void testZerosInMiddle() {
        assertEquals(101, Ex03_RemoveDuplicateDigits.removeDuplicateDigits(1001));
    }

}
