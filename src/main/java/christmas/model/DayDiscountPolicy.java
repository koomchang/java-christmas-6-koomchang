package christmas.model;

public class DayDiscount {
    private static final int DISCOUNT_RATE = 2023;

    private final Money discountAmount;

    public DayDiscount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money calculate(Date eventDate, int menuCount) {
        return new Money(menuCount * DISCOUNT_RATE);
    }
}
