package org.example.weekday;

public final class Weekday {

    private Weekday() {
    }

    public static void main(String[] args) {
        System.out.println(stringToWeekday("dom"));
    }

    public static int stringToWeekday(String weekday) {
        final var weekdays = new String[]{"domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sábado"};
        final var shortWeekdays = new String[]{"dom", "seg", "ter", "qua", "qui", "sex", "sab"};

        var result = -1;

        for (int i = 0; i < weekdays.length; i++) {
            if (weekday.equals(shortWeekdays[i])) {
                result = i;
                break;
            }
            if (weekday.equals(weekdays[i])) {
                result = i;
                break;
            }
        }

        return result;
    }
}
