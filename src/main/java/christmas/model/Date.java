package christmas.model;

import static christmas.exception.ExceptionMessages.DATE_INVALID;

public record Date(int date) {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int CHRISTMAS = 25;

    public Date {
        validate(date);
    }

    private void validate(int date) {
        if (isOutOfRange(date)) {
            throw new IllegalArgumentException(DATE_INVALID.getMessage());
        }
    }

    private boolean isOutOfRange(int date) {
        return date < MIN_DATE || date > MAX_DATE;
    }

    public static boolean isStarred(Date date) {
        for (StarredDate starredDate : StarredDate.values()) {
            if (date.date() == starredDate.getDate()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWeekend(Date date) {
        return Day.of(date).isWeekend();
    }

    public static boolean isWeekday(Date date) {
        return !isWeekend(date);
    }

    public boolean isChristmas() {
        return date == CHRISTMAS;
    }

    public static boolean isChristmasEventPeriod(Date date) {
        return date.date >= MIN_DATE && date.date <= CHRISTMAS;
    }

}