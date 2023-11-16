package christmas.exception;

public class InvalidMoneyException extends BaseException {
    private static final String MESSAGE = "잔액은 음수가 될 수 없습니다.";

    public InvalidMoneyException() {
        super(MESSAGE);
    }
}
