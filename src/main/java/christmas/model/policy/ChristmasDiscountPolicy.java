package christmas.model.policy;

import christmas.model.Order;
import christmas.model.vo.Date;
import christmas.model.vo.Money;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int CHRISTMAS_BASIC_DISCOUNT = 1_000;
    private static final int CHRISTMAS_DISCOUNT_RATE = 100;

    @Override
    public Money calculate(Date eventDate, Order order) {
        int discountValue = CHRISTMAS_BASIC_DISCOUNT + ((eventDate.date() - 1) * CHRISTMAS_DISCOUNT_RATE);
        if (!Date.isChristmasEventPeriod(eventDate)) {
            return Money.zero();
        }
        return new Money(discountValue);
    }
}
