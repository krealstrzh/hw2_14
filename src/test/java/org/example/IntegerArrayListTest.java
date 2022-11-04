package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.example.Constants.*;
import static org.example.Constants.LEGITIMATE_INT;
import static org.junit.jupiter.api.Assertions.*;

class IntegerArrayListTest {

    private final IntegerArrayList out = new IntegerArrayList();

    @Test
    void shouldAddItems() {
        assertEquals(LEGITIMATE_INT, out.add(LEGITIMATE_INT));
        assertEquals(LEGITIMATE_INT, out.add(LEGITIMATE_INDEX, LEGITIMATE_INT));
        assertThrows(NullItemException.class, () -> out.add(ILLEGAL_INT));
        assertThrows(InvalidIndexException.class, () -> out.add(ILLEGAL_INDEX, LEGITIMATE_INT));
        assertThrows(NullItemException.class, () -> out.add(LEGITIMATE_INDEX, ILLEGAL_INT));
    }

    @Test
    void shouldSetItem() {
        assertEquals(LEGITIMATE_INT, out.set(LEGITIMATE_INDEX, LEGITIMATE_INT));
    }

    @Test
    void shouldRemoveItem() {
        assertThrows(NotFoundException.class, () -> out.remove(LEGITIMATE_INT));
        out.add(LEGITIMATE_INT);
        assertEquals(LEGITIMATE_INT, out.remove(LEGITIMATE_INT));
        out.add(LEGITIMATE_INT);
        assertEquals(LEGITIMATE_INT, out.remove(LEGITIMATE_INDEX));
    }

    @Test
    void shouldReturnContainsResult() {
        assertFalse(out.contains(LEGITIMATE_INT));
        out.add(LEGITIMATE_INT);
        assertTrue(out.contains(LEGITIMATE_INT));
    }

    @Test
    void shouldReturnIndex() {
        assertEquals(-1, out.indexOf(LEGITIMATE_INT));
        out.add(LEGITIMATE_INT);
        assertEquals(0, out.indexOf(LEGITIMATE_INT));
    }

    @Test
    void shouldReturnLastIndex() {assertEquals(-1, out.lastIndexOf(LEGITIMATE_INT));
        out.add(LEGITIMATE_INT);
        out.add(LEGITIMATE_INT);
        assertEquals(1, out.lastIndexOf(LEGITIMATE_INT));
    }

    @Test
    void shouldReturnItemByIndex() {
        out.add(LEGITIMATE_INT);
        assertEquals(LEGITIMATE_INT, out.get(LEGITIMATE_INDEX));
    }

    @Test
    void shouldReturnIfListsAreEqual() {
        out.add(LEGITIMATE_INT);
        IntegerList out2 = new IntegerArrayList();
        assertFalse(out.equals(out2));
        out2.add(LEGITIMATE_INT);
        assertTrue(out.equals(out2));
    }

    @Test
    void size() {
        out.add(LEGITIMATE_INT);
        out.add(LEGITIMATE_INT);
        out.add(LEGITIMATE_INT);
        assertEquals(3, out.size());
    }

    @Test
    void shouldReturnIfEmpty() {
        assertTrue(out.isEmpty());
        out.add(LEGITIMATE_INT);
        assertFalse(out.isEmpty());
    }

    @Test
    void ShouldClear() {
        out.add(LEGITIMATE_INT);
        out.clear();
        assertTrue(out.isEmpty());
        assertEquals(0, out.size());
    }

    @Test
    void CheckToArray() {
        out.add(LEGITIMATE_INT);
        Integer[] expected = Arrays.copyOf(out.getList(), out.getSize());
        assertArrayEquals(expected, out.toArray());
    }

    @Test
    void ShouldGrow() {
        int newSize = out.size() + out.size() / 2;
        for (int i = 0; i < out.size(); i++) {
            out.add(LEGITIMATE_INT);
        }
        assertEquals(newSize, out.size());
    }

}