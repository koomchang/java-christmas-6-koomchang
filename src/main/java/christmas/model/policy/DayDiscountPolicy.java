package christmas.model;

public class DayDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_RATE = 2023;

    @Override
    public Money calculate(Date eventDate, Order order) {
        if (Date.isWeekday(eventDate)) {
            return new Money(order.getCountOfDesertMenu() * DISCOUNT_RATE);
        }
        return new Money(order.getCountOfMainMenu() * DISCOUNT_RATE);
    }
}
