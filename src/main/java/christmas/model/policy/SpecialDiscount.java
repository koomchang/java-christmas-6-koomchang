package christmas.model.policy;

import christmas.model.vo.Date;
import christmas.model.vo.Money;

public class SpecialDiscount {
    private static final int SPECIAL_DISCOUNT = 1000;

    private final Money discountAmount;

    public SpecialDiscount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Money calculate(Date eventDate) {
        if (Date.isSpecial(eventDate)) {
            return new Money(SPECIAL_DISCOUNT);
        }
        return Money.zero();
    }
}
