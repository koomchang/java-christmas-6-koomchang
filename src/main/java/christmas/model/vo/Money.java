package christmas.model.vo;

public record Money(int value) {

    public Money {
        validate();
    }

    private void validate() {
        validateMoneyNotNegative();
    }

    private void validateMoneyNotNegative() {
        if (value < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }

    public static Money zero() {
        return new Money(0);
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
