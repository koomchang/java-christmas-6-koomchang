package christmas.model;

public enum Badge {
    산타(20000),
    트리(10000),
    별(5000),
    없음(0);

    private static final Money Santa = new Money(20000);
    private static final Money Tree = new Money(10000);
    private static final Money Star = new Money(5000);


    private final Money discountRate;

    Badge(int discountRate) {
        this.discountRate = new Money(discountRate);
    }

    public static Badge getBadge(Money totalDiscountAmount) {
        if (totalDiscountAmount.isGreaterThanOrEqual(Santa)) {
            return 산타;
        }
        if (totalDiscountAmount.isGreaterThanOrEqual(Tree)) {
            return 트리;
        }
        if (totalDiscountAmount.isGreaterThanOrEqual(Star)) {
            return 별;
        }
        return 없음;
    }
}
