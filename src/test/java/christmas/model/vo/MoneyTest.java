package christmas.model.vo;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
    private Money money;

    @DisplayName("잔액은 음수가 될 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -1000})
    void validateIfNegative(int value) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> money = new Money(value)
        ).withMessage("[ERROR] 잔액은 음수가 될 수 없습니다.");
    }

}