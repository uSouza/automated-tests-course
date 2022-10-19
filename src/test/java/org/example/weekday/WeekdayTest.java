package org.example.weekday;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.example.weekday.Weekday.stringToWeekday;
import static org.example.weekday.Weekday.WeekdayNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class WeekdayTest {

    @Test
    @DisplayName("Given a weekday, when it's 'domingo', then must return 0.")
    void testDomingo() {
        assertEquals(0, stringToWeekday("dom"));
        assertEquals(0, stringToWeekday("domingo"));
        assertEquals(0, stringToWeekday("  DOMinGO  "));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'segunda', then must return 1.")
    void testSegunda() {
        assertEquals(1, stringToWeekday("seg"));
        assertEquals(1, stringToWeekday("segunda"));
        assertEquals(1, stringToWeekday("  SegUNDA  "));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'terça', then must return 2.")
    void testTerca() {
        assertEquals(2, stringToWeekday("ter"));
        assertEquals(2, stringToWeekday("terça"));
        assertEquals(2, stringToWeekday("  TeR  "));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'quarta', then must return 3.")
    void testQuarta() {
        assertEquals(3, stringToWeekday("qua"));
        assertEquals(3, stringToWeekday("quarta"));
        assertEquals(3, stringToWeekday("\n QuARTA \n"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'quinta', then must return 4.")
    void testQuinta() {
        assertEquals(4, stringToWeekday("qui"));
        assertEquals(4, stringToWeekday("quinta"));
        assertEquals(4, stringToWeekday("\tQuI\t"));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'sexta', then must return 5.")
    void testSexta() {
        assertEquals(5, stringToWeekday("sex"));
        assertEquals(5, stringToWeekday("sexta"));
        assertEquals(5, stringToWeekday("  sEXTa  "));
    }

    @Test
    @DisplayName("Given a weekday, when it's 'sábado', then must return 6.")
    void testSabado() {
        assertEquals(6, stringToWeekday("sab"));
        assertEquals(6, stringToWeekday("sábado"));
    }

    @Test
    @DisplayName("Given a non-existing weekday, then must throw a WeekdayNotFound exception.")
    void testNonExistingWeekday() {
        assertThrows(WeekdayNotFoundException.class, () -> stringToWeekday("inexistente"));
    }

    @Test
    @DisplayName("Given a null weekday, then must throw a WeekdayNotFound exception.")
    void testNullWeekday() {
        assertThrows(WeekdayNotFoundException.class, () -> stringToWeekday(null));
    }
}
