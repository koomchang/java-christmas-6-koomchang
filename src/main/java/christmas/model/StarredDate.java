package christmas.model;

public enum StarredDate {
    THIRD(3),
    TEN(10),
    SEVENTEEN(17),
    TWENTY_FOURTH(24),
    TWENTY_FIVE(25),
    THIRTY_FIRST(31);

    private final int date;

    StarredDate(int date) {
        this.date = date;
    }
}
