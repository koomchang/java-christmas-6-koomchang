package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateInputValidatorTest {
    BasicValidator<String> visitDateInputValidator = new VisitDateInputValidator();

    @DisplayName("날짜가 숫자가 아닌 입력값은 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "!", "", " "})
    void validateDateIfNumeric(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> visitDateInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("날짜가 1~31 사이의 값이 아닌 입력값은 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "32"})
    void validateIfDateOutOfRange(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> visitDateInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}