package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.example.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class StringArrayListTest {

    private final StringArrayList out = new StringArrayList();

    @Test
    void shouldAddItems() {
        assertEquals(LEGITIMATE_STRING, out.add(LEGITIMATE_STRING));
        assertEquals(LEGITIMATE_STRING, out.add(LEGITIMATE_INDEX, LEGITIMATE_STRING));
        assertThrows(NullItemException.class, () -> out.add(ILLEGAL_STRING));
        assertThrows(InvalidIndexException.class, () -> out.add(ILLEGAL_INDEX, LEGITIMATE_STRING));
        assertThrows(NullItemException.class, () -> out.add(LEGITIMATE_INDEX, ILLEGAL_STRING));
    }

    @Test
    void shouldSetItem() {
        assertEquals(LEGITIMATE_STRING, out.set(LEGITIMATE_INDEX, LEGITIMATE_STRING));
    }

    @Test
    void shouldRemoveItem() {
        assertThrows(NotFoundException.class, () -> out.remove(LEGITIMATE_STRING));
        out.add(LEGITIMATE_STRING);
        assertEquals(LEGITIMATE_STRING, out.remove(LEGITIMATE_STRING));
        out.add(LEGITIMATE_STRING);
        assertEquals(LEGITIMATE_STRING, out.remove(LEGITIMATE_INDEX));
    }

    @Test
    void shouldReturnContainsResult() {
        assertFalse(out.contains(LEGITIMATE_STRING));
        out.add(LEGITIMATE_STRING);
        assertTrue(out.contains(LEGITIMATE_STRING));
    }

    @Test
    void shouldReturnIndex() {
        assertEquals(-1, out.indexOf(LEGITIMATE_STRING));
        out.add(LEGITIMATE_STRING);
        assertEquals(0, out.indexOf(LEGITIMATE_STRING));
    }

    @Test
    void shouldReturnLastIndex() {assertEquals(-1, out.lastIndexOf(LEGITIMATE_STRING));
        out.add(LEGITIMATE_STRING);
        out.add(LEGITIMATE_STRING);
        assertEquals(1, out.lastIndexOf(LEGITIMATE_STRING));
    }

    @Test
    void shouldReturnItemByIndex() {
        out.add(LEGITIMATE_STRING);
        assertEquals(LEGITIMATE_STRING, out.get(LEGITIMATE_INDEX));
    }

    @Test
    void shouldReturnIfListsAreEqual() {
        out.add(LEGITIMATE_STRING);
        StringList out2 = new StringArrayList();
        assertFalse(out.equals(out2));
        out2.add(LEGITIMATE_STRING);
        assertTrue(out.equals(out2));
    }

    @Test
    void size() {
        out.add(LEGITIMATE_STRING);
        out.add(LEGITIMATE_STRING);
        out.add(LEGITIMATE_STRING);
        assertEquals(3, out.size());
    }

    @Test
    void shouldReturnIfEmpty() {
        assertTrue(out.isEmpty());
        out.add(LEGITIMATE_STRING);
        assertFalse(out.isEmpty());
    }

    @Test
    void ShouldClear() {
        out.add(LEGITIMATE_STRING);
        out.clear();
        assertTrue(out.isEmpty());
        assertEquals(0, out.size());
    }

    @Test
    void toArray() {
        out.add(LEGITIMATE_STRING);
        String[] expected = Arrays.copyOf(out.getList(), out.getSize());
        assertArrayEquals(expected, out.toArray());
    }
}