package strings;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Ex02_CountCodeDefuseTest {

    @Test
    public void testStringLength1() {
        assertEquals(0, Ex02_CountCodeDefuse.countCodeDefuse("a"));
    }

    @Test
    public void testSingleCode() {
        assertEquals(1, Ex02_CountCodeDefuse.countCodeDefuse("code"));
    }

    @Test
    public void testMultipleCode() {
        assertEquals(2, Ex02_CountCodeDefuse.countCodeDefuse("codecode"));
    }

    @Test
    public void testCodeAndDefuseMixed() {
        assertEquals(2, Ex02_CountCodeDefuse.countCodeDefuse("codedefuse"));
    }

}
