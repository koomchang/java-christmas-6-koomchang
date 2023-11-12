package christmas.model.policy;

import christmas.model.Order;
import christmas.model.vo.Date;
import christmas.model.vo.Money;

public class SpecialDiscountPolicy implements DiscountPolicy {
    private static final int SPECIAL_DISCOUNT = 1_000;

    @Override
    public Money calculate(Date eventDate, Order order) {
        if (Date.isSpecial(eventDate)) {
            return new Money(SPECIAL_DISCOUNT);
        }
        return Money.zero();
    }
}
