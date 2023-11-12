package christmas.model.policy;

import christmas.model.Order;
import christmas.model.vo.Date;
import christmas.model.vo.Money;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int CHRISTMAS_BASIC_DISCOUNT = 1000;
    private static final int CHRISTMAS_DISCOUNT_RATE = 100;
    private static final int NO_DISCOUNT = 0;

    @Override
    public Money calculate(Date eventDate, Order order) {
        int discountValue = CHRISTMAS_BASIC_DISCOUNT + ((eventDate.date() - 1) * CHRISTMAS_DISCOUNT_RATE);
        if (!Date.isChristmasEventPeriod(eventDate)) {
            return new Money(NO_DISCOUNT);
        }
        return new Money(discountValue);
    }
}
