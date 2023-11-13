package christmas.exception;

public class InvalidOrderException extends BaseException {
    private static final String MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public InvalidOrderException() {
        super(MESSAGE);
    }
}
