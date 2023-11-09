package christmas.model;

public enum Day {

    THURSDAY(false),
    FRIDAY(false),
    SATURDAY(true),
    SUNDAY(true),
    MONDAY(false),
    TUESDAY(false),
    WEDNESDAY(false);

    private static final int DAYS_IN_WEEK = 7;
    private final boolean isWeekend;

    Day(boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public static Day of(int date) {
        return Day.values()[date % DAYS_IN_WEEK];
    }
}
