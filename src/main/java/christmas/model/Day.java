package christmas.model;

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

    public static Day of(int date) {
        return Day.values()[date % DAYS_IN_WEEK];
    }

    public boolean isWeekend() {
        return isWeekend;
    }
}
