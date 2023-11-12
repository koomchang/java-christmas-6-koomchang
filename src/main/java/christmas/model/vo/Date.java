package christmas.model.vo;

import christmas.exception.DateInvalidException;
import christmas.model.enums.SpecialDate;
import java.time.DayOfWeek;
import java.time.LocalDate;

public record Date(int date) {
    private static final int YEAR = 2_023;
    private static final int MONTH = 12;
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final int CHRISTMAS = 25;

    public Date {
        validate(date);
    }

    private void validate(int date) {
        if (isOutOfRange(date)) {
            throw new DateInvalidException();
        }
    }

    private boolean isOutOfRange(int date) {
        return date < MIN_DATE || date > MAX_DATE;
    }

    public static boolean isSpecial(Date date) {
        for (SpecialDate specialDate : SpecialDate.values()) {
            if (date.date() == specialDate.getDate()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWeekend(Date date) {
        LocalDate localDate = LocalDate.of(YEAR, MONTH, date.date());
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public static boolean isWeekday(Date date) {
        return !isWeekend(date);
    }

    public static boolean isChristmasEventPeriod(Date date) {
        return date.date >= MIN_DATE && date.date <= CHRISTMAS;
    }
}