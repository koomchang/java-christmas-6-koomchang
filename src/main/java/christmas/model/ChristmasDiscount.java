package christmas.model;

public class ChristmasDiscount {
    private static final int CHRISTMAS_BASIC_DISCOUNT = 1000;
    private static final int CHRISTMAS_DISCOUNT_RATE = 100;
    private static final int NO_DISCOUNT = 0;

    private final Money discountAmount;

    public ChristmasDiscount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money calculate(Date eventDate) {
        int discountValue = CHRISTMAS_BASIC_DISCOUNT + ((eventDate.date() - 1) * CHRISTMAS_DISCOUNT_RATE);
        if (!Date.isChristmasEventPeriod(eventDate)) {
            return new Money(NO_DISCOUNT);
        }
        return new Money(discountValue);
    }
}
