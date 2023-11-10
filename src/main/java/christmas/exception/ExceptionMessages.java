package christmas.exception;

public enum ExceptionMessages {
    DATE_INVALID("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDER_INVALID("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String MESSAGE_FORMAT = "[ERROR] %s";
    private final String message;

    ExceptionMessages(String message) {
        this.message = String.format(MESSAGE_FORMAT, message);
    }

    public String getMessage() {
        return message;
    }
}
