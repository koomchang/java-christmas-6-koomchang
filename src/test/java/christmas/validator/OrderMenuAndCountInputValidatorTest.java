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

    @DisplayName("메뉴와 개수가 하이픈으로 구분되지 않은 입력값은 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립2,레드와인1,초코케이크1", "바비큐립2,레드와인1,초코케이크1,", ",바비큐립2,레드와인1,초코케이크1"})
    void validateMenuAndCountLength(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> orderMenuAndCountInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴가 메뉴 목록에 없는 입력값은 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"딸기스무디-1,초코케이크-1", "제로콜라-1,오레오케이크-1"})
    void validateMenuInMenuList(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> orderMenuAndCountInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("입력값이 없는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void validateIfMenuAndCountEmpty(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> orderMenuAndCountInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복된 메뉴가 있는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립-2,바비큐립-1,초코케이크-1", "레드와인-1,레드와인-1,초코케이크-1"})
    void validateDuplicateMenu(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> orderMenuAndCountInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 개수가 20개를 초과하는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립-10,레드와인-11,초코케이크-1", "레드와인-1,레드와인-1,초코케이크-20"})
    void validateMenuCount(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> orderMenuAndCountInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문이 음료만 있는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"레드와인-1", "제로콜라-3", "샴페인-2"})
    void validateOnlyBeverage(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> orderMenuAndCountInputValidator.validate(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}