package christmas.model;

import static christmas.exception.ExceptionMessages.DATE_OUT_OF_RANGE;

public record Date(int date) {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int CHRISTMAS = 25;

    public Date {
        validate(date);
    }

    private void validate(int date) {
        if (isOutOfRange(date)) {
            throw new IllegalArgumentException(DATE_OUT_OF_RANGE.getMessage());
        }
    }

    private boolean isOutOfRange(int date) {
        return date < MIN_DATE || date > MAX_DATE;
    }

    public boolean isStarred() {
        for (StarredDate starredDate : StarredDate.values()) {
            if (date == starredDate.getDate()) {
                return true;
            }
        }
        return false;
    }

    public boolean isWeekend() {
        return Day.of(date).isWeekend();
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isChristmas() {
        return date == CHRISTMAS;
    }

    public static boolean isChristmasEventPeriod(int date) {
        return date >= MIN_DATE && date <= CHRISTMAS;
    }

}