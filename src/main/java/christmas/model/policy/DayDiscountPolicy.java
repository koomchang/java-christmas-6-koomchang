package christmas.model.policy;

import christmas.model.Order;
import christmas.model.vo.Date;
import christmas.model.vo.Money;

public class DayDiscountPolicy implements DiscountPolicy {
    private static final int DISCOUNT_RATE = 2_023;

    @Override
    public Money calculate(Date eventDate, Order order) {
        if (Date.isWeekday(eventDate)) {
            return new Money(order.getCountOfDesertMenu() * DISCOUNT_RATE);
        }
        return new Money(order.getCountOfMainMenu() * DISCOUNT_RATE);
    }
}
