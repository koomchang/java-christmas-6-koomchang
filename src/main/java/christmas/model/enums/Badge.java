package christmas.model.enums;

import christmas.model.vo.Money;

public enum Badge {
    산타(20_000),
    트리(10_000),
    별(5_000),
    없음(0);

    private final Money discountRate;

    Badge(int discountRate) {
        this.discountRate = new Money(discountRate);
    }

    public static Badge getBadge(Money totalDiscountAmount) {
        if (totalDiscountAmount.isGreaterThanOrEqual(산타.discountRate)) {
            return 산타;
        }
        if (totalDiscountAmount.isGreaterThanOrEqual(트리.discountRate)) {
            return 트리;
        }
        if (totalDiscountAmount.isGreaterThanOrEqual(별.discountRate)) {
            return 별;
        }
        return 없음;
    }
}
