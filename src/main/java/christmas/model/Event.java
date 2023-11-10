package christmas.model;

public class Event {
    private static final int CHRISTMAS_BASIC_DISCOUNT = 1000;
    private static final int CHRISTMAS_DISCOUNT_RATE = 100;
    private static final int DISCOUNT_RATE = 2023;
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int GIFT_EVENT_AMOUNT = 120000;
    private static final int NO_DISCOUNT = 0;

    public int totalDiscount(int date, int menuCount) {
        if (Date.isChristmasEventPeriod(date)) {
            return christmasDiscount(date) + menuDiscount(menuCount) + specialDiscount(date);
        }
        return menuDiscount(menuCount) + specialDiscount(date);
    }

    public boolean isGiftEventEligible(int orderAmount) {
        return orderAmount >= GIFT_EVENT_AMOUNT;
    }

    public int christmasDiscount(int date) {
        return CHRISTMAS_BASIC_DISCOUNT + (date * CHRISTMAS_DISCOUNT_RATE);
    }

    public int menuDiscount(int menuCount) {
        return menuCount * DISCOUNT_RATE;
    }

    public int specialDiscount(int date) {
        for (StarredDate specialDate : StarredDate.values()) {
            if (date == specialDate.getDate()) {
                return SPECIAL_DISCOUNT;
            }
        }
        return NO_DISCOUNT;
    }
}
