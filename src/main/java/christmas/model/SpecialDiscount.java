package christmas.model;

public class SpecialDiscount {
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int NO_DISCOUNT = 0;

    private final Money discountAmount;

    public SpecialDiscount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money calculate(Date eventDate) {
        for (StarredDate specialDate : StarredDate.values()) {
            if (Date.isStarred(eventDate)) {
                return new Money(SPECIAL_DISCOUNT);
            }
        }
        return new Money(NO_DISCOUNT);
    }
}
