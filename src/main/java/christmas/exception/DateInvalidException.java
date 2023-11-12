package christmas.exception;

public class DateInvalidException extends BaseException {
    private static final String MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public DateInvalidException() {
        super(MESSAGE);
    }
}
