package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderMenuAndCountInputValidatorTest {
    BasicValidator<String> orderMenuAndCountInputValidator = new OrderMenuAndCountInputValidator();

    @DisplayName("메뉴와 개수가 콤마로 구분되지 않은 입력값은 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립-2레드와인-1초코케이크-1", "바비큐립-2,레드와인-1,초코케이크-1,", ",바비큐립-2,레드와인-1,초코케이크-1"})
    void validateMenuFormat(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> orderMenuAndCountInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}