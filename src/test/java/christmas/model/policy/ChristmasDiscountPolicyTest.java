package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Order;
import christmas.model.enums.Menu;
import christmas.model.vo.Date;
import christmas.model.vo.Money;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasDiscountPolicyTest {
    private final DiscountPolicy christmasDiscountPolicy = new ChristmasDiscountPolicy();
    private final Map<Menu, Integer> menuAndCount = Map.of(
            Menu.바비큐립, 3,
            Menu.레드와인, 5,
            Menu.초코케이크, 7
    );
    private final Order order = new Order(menuAndCount);

    @DisplayName("디데이 이벤트 기간이 아니면 할인 금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30})
    void calculateIfNotChristmasEventPeriod(int date) {
        Date eventDate = new Date(date);
        assertThat(christmasDiscountPolicy.calculate(eventDate, order)).
                isEqualTo(Money.zero());
    }

    @DisplayName("디데이 할인 정책에 따른 할인 금액을 계산한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 13, 17, 25})
    void calculate(int date) {
        Date eventDate = new Date(date);
        assertThat(christmasDiscountPolicy.calculate(eventDate, order)).
                isEqualTo(new Money(1_000 + 100 * (date - 1)));
    }
}