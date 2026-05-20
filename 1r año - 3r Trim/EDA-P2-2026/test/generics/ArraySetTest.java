package generics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

public class ArraySetTest {

    @Test
    void testMaxSize(){
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);
        assertEquals(5, set.maxSize());
    }

    @Test
    void testAddElement() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertEquals(2, set.size());
    }


    @Test
    void testContains() {
        ArraySet<String> set = new ArraySet<>(String.class, 5);

        set.add("hello");
        set.add("world");

        assertTrue(set.contains("hello"));
        assertFalse(set.contains("java"));
    }

    @Test
    void testRemoveElement() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        set.add(1);
        set.add(2);
        set.add(3);

        assertTrue(set.remove(2));
        assertFalse(set.contains(2));
        assertEquals(2, set.size());
    }

    @Test
    void testRemoveNonExisting() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        set.add(1);

        assertFalse(set.remove(2));
        assertEquals(1, set.size());
    }

    @Test
    void testIsEmpty() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        assertTrue(set.isEmpty());

        set.add(1);

        assertFalse(set.isEmpty());
    }

    @Test
    void testGetValid() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        set.add(10);
        set.add(20);

        assertEquals(10, set.get(0));
        assertEquals(20, set.get(1));
    }

    @Test
    void testGetInvalidIndex() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        set.add(1);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            set.get(2);
        });
    }

    @Test
    void testIteratorTraversal() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        set.add(1);
        set.add(2);
        set.add(3);

        Iterator<Integer> it = set.iterator();

        int count = 0;
        while (it.hasNext()) {
            it.next();
            count++;
        }

        assertEquals(3, count);
    }

    @Test
    void testIteratorNoSuchElement() {
        ArraySet<Integer> set = new ArraySet<>(Integer.class, 5);

        set.add(1);

        Iterator<Integer> it = set.iterator();

        it.next(); // first OK

        assertThrows(java.util.NoSuchElementException.class, () -> {
            it.next(); // should fail
        });
    }
    
}
