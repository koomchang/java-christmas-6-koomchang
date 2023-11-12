package christmas.model;

import christmas.model.enums.Badge;
import christmas.model.vo.Date;
import christmas.model.policy.DiscountPolicy;
import christmas.model.vo.Money;

public class EventPlanner {
    private final DiscountPolicy christmasDiscountPolicy;
    private final DiscountPolicy dayDiscountPolicy;
    private final DiscountPolicy giftEventPolicy;

    public EventPlanner(
            DiscountPolicy christmasDiscountPolicy,
            DiscountPolicy dayDiscountPolicy,
            DiscountPolicy giftEventPolicy
    ) {
        this.christmasDiscountPolicy = christmasDiscountPolicy;
        this.dayDiscountPolicy = dayDiscountPolicy;
        this.giftEventPolicy = giftEventPolicy;
    }

    public Money getTotalDiscountAmount(Date eventDate, Order order) {
        return Money.init()
                .plus(getChristmasDiscount(eventDate, order))
                .plus(getDayDiscount(eventDate, order))
                .plus(getGiftEventDiscount(eventDate, order));
    }

    public Money getChristmasDiscount(Date eventDate, Order order) {
        return christmasDiscountPolicy.calculate(eventDate, order);
    }

    public Money getDayDiscount(Date eventDate, Order order) {
        return dayDiscountPolicy.calculate(eventDate, order);
    }

    public Money getGiftEventDiscount(Date eventDate, Order order) {
        return giftEventPolicy.calculate(eventDate, order);
    }

    public Badge getBadge(Date eventDate, Order order) {
        return Badge.getBadge(getTotalDiscountAmount(eventDate, order));
    }
}
