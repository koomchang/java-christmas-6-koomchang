package christmas.validator;

import christmas.exception.InvalidDateException;

public class VisitDateInputValidator implements BasicValidator<String> {
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    @Override
    public void validate(String input) {
        validateDateIfNumeric(input);
        validateIfDateOutOfRange(input);
    }

    private void validateDateIfNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidDateException();
        }
    }

    private void validateIfDateOutOfRange(String input) {
        int date = Integer.parseInt(input);
        if (isOutOfRange(date)) {
            throw new InvalidDateException();
        }
    }

    private boolean isOutOfRange(int date) {
        return date < MIN_DATE || date > MAX_DATE;
    }
}
