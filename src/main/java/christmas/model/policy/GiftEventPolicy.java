package christmas.model.policy;

import christmas.model.Order;
import christmas.model.vo.Date;
import christmas.model.vo.Money;

public class GiftEventPolicy implements DiscountPolicy {
    private static final Money MINIMUM_PURCHASE_AMOUNT = new Money(120_000);
    private static final Money GIFT_EVENT_DISCOUNT = new Money(25_000);

    @Override
    public Money calculate(Date eventDate, Order order) {
        if (isGiftEventEligible(order.getTotalPrice())) {
            return GIFT_EVENT_DISCOUNT;
        }
        return Money.zero();
    }

    public boolean isGiftEventEligible(Money orderAmount) {
        return orderAmount.isGreaterThanOrEqual(MINIMUM_PURCHASE_AMOUNT);
    }
}