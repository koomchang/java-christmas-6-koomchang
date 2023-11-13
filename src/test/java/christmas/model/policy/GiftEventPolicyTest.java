package christmas.model.policy;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.Order;
import christmas.model.enums.Menu;
import christmas.model.vo.Date;
import christmas.model.vo.Money;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GiftEventPolicyTest {
    private static final Money MINIMUM_PURCHASE_AMOUNT = new Money(120_000);
    private static final Money GIFT_EVENT_DISCOUNT = new Money(25_000);

    private final GiftEventPolicy giftEventPolicy = new GiftEventPolicy();
    private final Map<Menu, Integer> menuAndCount = Map.of(
            Menu.바비큐립, 3,
            Menu.레드와인, 5,
            Menu.초코케이크, 7
    );
    private final Order order = new Order(menuAndCount);

    @DisplayName("증정 이벤트가 적용되는지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {120_000, 150_000})
    void calculateIfGiftEventEligible(int orderAmount) {
        assertThat(giftEventPolicy.isGiftEventEligible(new Money(orderAmount))).isTrue();
    }

    @DisplayName("증정 이벤트가 적용되지 않는지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {119_000, 100_000})
    void calculateIfGiftEventNotEligible(int orderAmount) {
        assertThat(giftEventPolicy.isGiftEventEligible(new Money(orderAmount))).isFalse();
    }
}