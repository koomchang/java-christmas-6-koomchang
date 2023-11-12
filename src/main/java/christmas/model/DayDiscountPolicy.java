package christmas.model;

public class DayDiscountPolicy {
    private static final int DISCOUNT_RATE = 2023;

    private final Money discountAmount;

    public DayDiscountPolicy(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money calculate(Date eventDate, int menuCount) {
        return new Money(menuCount * DISCOUNT_RATE);
    }
}
