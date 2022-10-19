package org.example.weekday;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.weekday.Weekday.stringToWeekday;
import static org.junit.jupiter.api.Assertions.*;

public class WeekdayTest {

    @Test
    @DisplayName("Given a weekday, when it's 'domingo', then must return 0.")
    void testDomingo() {
        assertEquals(0, stringToWeekday("dom"));
        assertEquals(0, stringToWeekday("domingo"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'segunda', then must return 1.")
    void testSegunda() {
        assertEquals(1, stringToWeekday("seg"));
        assertEquals(1, stringToWeekday("segunda"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'terça', then must return 2.")
    void testTerca() {
        assertEquals(2, stringToWeekday("ter"));
        assertEquals(2, stringToWeekday("terça"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'quarta', then must return 3.")
    void testQuarta() {
        assertEquals(3, stringToWeekday("qua"));
        assertEquals(3, stringToWeekday("quarta"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'quinta', then must return 4.")
    void testQuinta() {
        assertEquals(4, stringToWeekday("qui"));
        assertEquals(4, stringToWeekday("quinta"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'sexta', then must return 5.")
    void testSexta() {
        assertEquals(5, stringToWeekday("sex"));
        assertEquals(5, stringToWeekday("sexta"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'sábado', then must return 6.")
    void testSabado() {
        assertEquals(6, stringToWeekday("sab"));
        assertEquals(6, stringToWeekday("sábado"));
    }

    @Test
    @DisplayName("Given a non-existing weekday, then must return -1.")
    void testNonExistingWeekday() {
        assertEquals(-1, stringToWeekday("inexistente"));
    }
}
