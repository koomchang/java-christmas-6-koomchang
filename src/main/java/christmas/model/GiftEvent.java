package christmas.model;

public class GiftEvent {
    private static final int MINIMUM_PURCHASE_AMOUNT = 120_000;
    private static final int GIFT_EVENT_DISCOUNT = 25000;

    private final boolean isGiftEventEligible;

    public GiftEvent(boolean isGiftEventEligible) {
        this.isGiftEventEligible = isGiftEventEligible;
    }

    public Money calculateDiscount(Money orderAmount) {
        if (isGiftEventEligible) {
            return new Money(GIFT_EVENT_DISCOUNT);
        }
        return new Money(0);
    }

    public boolean isGiftEventEligible(Money orderAmount) {
        return orderAmount.isGreaterThanOrEqual(MINIMUM_PURCHASE_AMOUNT);
    }
}