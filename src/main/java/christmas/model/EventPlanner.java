package christmas.model;

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
                .add(getChristmasDiscount(eventDate, order))
                .add(getDayDiscount(eventDate, order))
                .add(getGiftEventDiscount(eventDate, order));
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
