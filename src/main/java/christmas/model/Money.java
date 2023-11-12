package christmas.model;

public record Money(int value) {
    private static int MINIMUM_PRICE_FOR_EVENT = 10000;

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

    public boolean canParticipateInEvent() {
        return value >= MINIMUM_PRICE_FOR_EVENT;
    }
}
