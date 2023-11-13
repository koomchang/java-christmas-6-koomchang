package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.enums.Badge;
import christmas.model.enums.Menu;
import christmas.model.policy.ChristmasDiscountPolicy;
import christmas.model.policy.DayDiscountPolicy;
import christmas.model.policy.GiftEventPolicy;
import christmas.model.policy.SpecialDiscountPolicy;
import christmas.model.vo.Date;
import christmas.model.vo.Money;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventPlannerTest {
    private final EventPlanner eventPlanner = new EventPlanner(
            new ChristmasDiscountPolicy(),
            new DayDiscountPolicy(),
            new GiftEventPolicy(),
            new SpecialDiscountPolicy()
    );

    private final Map<Menu, Integer> menuAndCount = Map.of(
            Menu.바비큐립, 3,
            Menu.레드와인, 5,
            Menu.초코케이크, 7
    );
    private final Order order = new Order(menuAndCount);

    @DisplayName("전체 할인 금액을 반환한다")
    @Test
    void calculateTotalDiscountAmount() {
        Date eventDate = new Date(17);
        assertThat(eventPlanner.getTotalDiscountAmount(eventDate, order))
                .isEqualTo(new Money(17_761));
    }

    @DisplayName("전체 할인 금액에 따른 뱃지를 반환한다")
    @Test
    void getBadge() {
        Date eventDate = new Date(17);
        assertThat(eventPlanner.getBadge(eventDate, order))
                .isEqualTo(Badge.트리);
    }
}