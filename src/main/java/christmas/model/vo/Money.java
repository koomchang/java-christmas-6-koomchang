package christmas.model.vo;

import christmas.exception.InvalidMoneyException;

public record Money(int value) {
    private static final int ZERO = 0;

    public Money {
        validate(value);
    }

    private void validate(int value) {
        validateMoneyNotNegative(value);
    }

    private void validateMoneyNotNegative(int value) {
        if (value < ZERO) {
            throw new InvalidMoneyException();
        }
    }

    public static Money zero() {
        return new Money(ZERO);
    }

    public Money plus(Money money) {
        return new Money(value + money.value);
    }

    public boolean isGreaterThanOrEqual(Money minimumPurchaseAmount) {
        return value >= minimumPurchaseAmount.value;
    }

    public Money minus(Money money) {
        return new Money(value - money.value);
    }
}
