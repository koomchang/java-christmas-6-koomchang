package christmas.model.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("메뉴판에 있는 메뉴를 입력하면 해당 메뉴를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립", "레드와인", "초코케이크"})
    void validateIfMenuInMenuList(String input) {
        assertThat(Menu.of(input)).isEqualTo(Menu.valueOf(input));
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력하면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"딸기스무디", "코카콜라"})
    void validateIfMenuNotInMenuList(String input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> Menu.of(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}