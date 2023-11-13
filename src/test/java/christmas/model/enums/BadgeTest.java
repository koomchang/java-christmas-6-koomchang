package christmas.model.enums;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @DisplayName("2만원 이상 할인 시 산타 배지를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {20_000, 30_000})
    void getSantaBadge(int value) {
        Money totalDiscountAmount = new Money(value);
        Badge badge = Badge.getBadge(totalDiscountAmount);
        assertThat(badge).isEqualTo(Badge.산타);
    }

    @DisplayName("1만원 이상 할인 시 트리 배지를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {10_000, 19_999})
    void getTreeBadge(int value) {
        Money totalDiscountAmount = new Money(value);
        Badge badge = Badge.getBadge(totalDiscountAmount);
        assertThat(badge).isEqualTo(Badge.트리);
    }

    @DisplayName("5천원 이상 할인 시 별 배지를 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {5_000, 9_999})
    void getStarBadge(int value) {
        Money totalDiscountAmount = new Money(value);
        Badge badge = Badge.getBadge(totalDiscountAmount);
        assertThat(badge).isEqualTo(Badge.별);
    }

    @DisplayName("5천원 미만 할인 시 뱃지는 없다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 4_999})
    void getNoneBadge(int value) {
        Money totalDiscountAmount = new Money(value);
        Badge badge = Badge.getBadge(totalDiscountAmount);
        assertThat(badge).isEqualTo(Badge.없음);
    }
}