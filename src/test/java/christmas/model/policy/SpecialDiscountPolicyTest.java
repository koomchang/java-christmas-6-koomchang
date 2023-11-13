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

class SpecialDiscountPolicyTest {
    private static final Money SPECIAL_DISCOUNT = new Money(1_000);
    private final DiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
    private final Map<Menu, Integer> menuAndCount = Map.of(
            Menu.바비큐립, 3,
            Menu.레드와인, 5,
            Menu.초코케이크, 7
    );
    private final Order order = new Order(menuAndCount);

    @DisplayName("특별 이벤트 날짜가 아니면 할인 금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 12, 27})
    void calculateIfNotSpecial(int specialDate) {
        assertThat(specialDiscountPolicy.calculate(new Date(specialDate), order)).isEqualTo(Money.zero());
    }

    @DisplayName("특별 이벤트 날짜면 할인 금액을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void calculateIfSpecial(int specialDate) {
        assertThat(specialDiscountPolicy.calculate(new Date(specialDate), order)).isEqualTo(SPECIAL_DISCOUNT);
    }
}