package christmas.model.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateTest {
    private Date date;

    @DisplayName("날짜가 1~31 사이의 값이 아닌 입력값은 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void validateIfDateOutOfRange(int input) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> date = new Date(input)
        ).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("특별 할인이벤트에 해당하는 날인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void isSpecial(int input) {
        date = new Date(input);
        assertThat(date.isSpecial()).isTrue();
    }

    @DisplayName("특별 할인이벤트에 해당하지 않는 날인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 15, 20, 30})
    void isNotSpecial(int input) {
        date = new Date(input);
        assertThat(date.isSpecial()).isFalse();
    }

    @DisplayName("날짜가 주말인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void isWeekend(int input) {
        date = new Date(input);
        assertThat(Date.isWeekend(date)).isTrue();
    }

    @DisplayName("날짜가 평일인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 18, 19, 20, 31})
    void isWeekday(int input) {
        date = new Date(input);
        assertThat(Date.isWeekday(date)).isTrue();
    }

    @DisplayName("날짜가 크리스마스 이벤트 기간인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 25})
    void isChristmasEventPeriod(int input) {
        date = new Date(input);
        assertThat(Date.isChristmasEventPeriod(date)).isTrue();
    }

    @DisplayName("날짜가 크리스마스 이벤트 기간이 아닌지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 31})
    void isNotChristmasEventPeriod(int input) {
        date = new Date(input);
        assertThat(Date.isChristmasEventPeriod(date)).isFalse();
    }
}