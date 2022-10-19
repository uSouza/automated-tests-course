package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 15})
    void testNumberIsOdd(int number) {
        assertTrue(number % 2 != 0);
    }
}