package christmas.exception;

public class BaseException extends IllegalArgumentException {
    private static final String MESSAGE_FORMAT = "[ERROR] %s";

    public BaseException(String message) {
        super(String.format(MESSAGE_FORMAT, message));
    }
}
