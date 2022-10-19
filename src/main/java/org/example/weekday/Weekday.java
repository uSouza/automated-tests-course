package org.example.weekday;

import java.util.List;

public final class Weekday {

    private Weekday() {
    }

    record Day(String fullName, String shortName, int index) {
    }

    static class WeekdayNotFoundException extends RuntimeException {
        WeekdayNotFoundException() {
            super("Weekday not found.");
        }
    }

    private static final List<Day> WEEKDAYS = List.of(
        new Day("domingo", "dom", 0),
        new Day("segunda", "seg", 1),
        new Day("terça", "ter", 2),
        new Day("quarta", "qua", 3),
        new Day("quinta", "qui", 4),
        new Day("sexta", "sex", 5),
        new Day("sábado", "sab", 6)
    );

    public static int stringToWeekday(String rawWeekday) {
        if (rawWeekday == null) {
            throw new WeekdayNotFoundException();
        }

        var cleanWeekday = rawWeekday.trim();

        return WEEKDAYS.stream().filter(weekday
                -> weekday.fullName.equalsIgnoreCase(cleanWeekday) ||
                weekday.shortName.equalsIgnoreCase(cleanWeekday))
            .findFirst()
            .orElseThrow(WeekdayNotFoundException::new)
            .index;
    }

}
