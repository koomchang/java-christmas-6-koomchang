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

class DayDiscountPolicyTest {
    private static final int DISCOUNT_RATE = 2_023;

    private final DiscountPolicy dayDiscountPolicy = new DayDiscountPolicy();
    private final Map<Menu, Integer> menuAndCount = Map.of(
            Menu.바비큐립, 3,
            Menu.레드와인, 5,
            Menu.초코케이크, 7
    );
    private final Order order = new Order(menuAndCount);

    @DisplayName("평일에는 디저트메뉴의 수량에 따라 할인 금액을 계산한다.")
    @ParameterizedTest
    @ValueSource(ints = {3})
    void calculateIfWeekday(int date) {
        assertThat(dayDiscountPolicy.calculate(new Date(date), order))
                .isEqualTo(new Money(DISCOUNT_RATE * menuAndCount.get(Menu.초코케이크)));
    }

    @DisplayName("주말에는 메인메뉴의 수량에 따라 할인 금액을 계산한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9})
    void calculateIfWeekend(int date) {
        assertThat(dayDiscountPolicy.calculate(new Date(date), order))
                .isEqualTo(new Money(DISCOUNT_RATE * menuAndCount.get(Menu.바비큐립)));
    }
}