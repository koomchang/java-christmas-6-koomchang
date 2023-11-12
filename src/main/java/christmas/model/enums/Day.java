package christmas.model.enums;

import christmas.model.vo.Date;

public enum Day {
    THURSDAY(false),
    FRIDAY(true),
    SATURDAY(true),
    SUNDAY(false),
    MONDAY(false),
    TUESDAY(false),
    WEDNESDAY(false);

    private static final int DAYS_IN_WEEK = 7;
    private final boolean isWeekend;

    Day(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public static Day of(Date date) {
        return Day.values()[date.date() % DAYS_IN_WEEK];
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
