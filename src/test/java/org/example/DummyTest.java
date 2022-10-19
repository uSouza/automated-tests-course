package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class DummyTest {

    @Test
    void testOne() {
        assertTrue(true);
    }

    @Test
    void testTwo() {
        assertEquals(5, 25 / 5);
    }

    @Test
    void testThree() {
        assertNotEquals(10, 25 / 5);
    }
}
