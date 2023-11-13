package christmas.model.vo;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("잔액끼리 더할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {2000})
    void plus(int value) {
        money = new Money(1000);
        Money moneyToPlus = new Money(value);
        Money expected = new Money(3000);
        assertThat(money.plus(moneyToPlus)).isEqualTo(expected);
    }

    @DisplayName("잔액끼리 뺄 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1000})
    void minus(int value) {
        money = new Money(2000);
        Money moneyToMinus = new Money(value);
        Money expected = new Money(1000);
        assertThat(money.minus(moneyToMinus)).isEqualTo(expected);
    }

}