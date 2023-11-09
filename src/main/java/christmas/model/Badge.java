package christmas.model;

public enum Badge {
    산타(20000),
    트리(10000),
    별(5000),
    없음(0);

    private final int discountRate;

    Badge(int discountRate) {
        this.discountRate = discountRate;
    }

    public Badge getBadge(int discountRate) {
        if (discountRate >= 20000) {
            return 산타;
        }
        if (discountRate >= 10000) {
            return 트리;
        }
        if (discountRate >= 5000) {
            return 별;
        }
        return 없음;
    }
}
