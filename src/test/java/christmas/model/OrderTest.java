package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import christmas.model.enums.Menu;
import christmas.model.vo.Money;
import christmas.view.InputParser;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {
    InputParser inputParser = new InputParser();

    @DisplayName("주문 개수가 20개를 초과하는 경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"바비큐립-10,레드와인-11,초코케이크-1", "레드와인-1,레드와인-1,초코케이크-20"})
    void validateMenuCount(String orders) {
        Map<Menu, Integer> menuAndCount = inputParser.parseOrderMenuAndCount(orders);
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Order(menuAndCount)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 객체는 음료수만 가질 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"레드와인-1", "제로콜라-2,샴페인-1"})
    void validateOnlyBeverage(String orders) {
        Map<Menu, Integer> menuAndCount = inputParser.parseOrderMenuAndCount(orders);
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Order(menuAndCount)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("전체 주문 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "바비큐립-2,레드와인-1,초코케이크-1:183000",
            "티본스테이크-2,제로콜라-3,아이스크림-1,샴페인-1:149000"
    }, delimiter = ':')
    void getTotalPrice(String orders, int expected) {
        Map<Menu, Integer> menuAndCount = inputParser.parseOrderMenuAndCount(orders);
        Order order = new Order(menuAndCount);
        assertThat(order.getTotalPrice()).isEqualTo(new Money(expected));
    }

    @DisplayName("이벤트 참여 가능 여부를 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "양송이수프-1:false",
            "아이스크림-1,제로콜라-1:false",
            "티본스테이크-2,제로콜라-3,아이스크림-1,샴페인-1:true"
    }, delimiter = ':')
    void canParticipateInEvent(String orders, boolean expected) {
        Map<Menu, Integer> menuAndCount = inputParser.parseOrderMenuAndCount(orders);
        Order order = new Order(menuAndCount);
        assertThat(order.canParticipateInEvent()).isEqualTo(expected);
    }

    @DisplayName("증정 이벤트 대상 여부를 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "양송이수프-1:false",
            "아이스크림-1,제로콜라-1:false",
            "티본스테이크-2,제로콜라-3,아이스크림-1,샴페인-1:true",
            "해산물파스타-3,레드와인-1,아이스크림-1,샴페인-1:true"
    }, delimiter = ':')
    void isGiftEventEligible(String orders, boolean expected) {
        Map<Menu, Integer> menuAndCount = inputParser.parseOrderMenuAndCount(orders);
        Order order = new Order(menuAndCount);
        assertThat(order.isGiftEventEligible()).isEqualTo(expected);
    }

    @DisplayName("메인 메뉴 개수를 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "양송이수프-1:0",
            "아이스크림-1,제로콜라-1:0",
            "티본스테이크-2,제로콜라-3,아이스크림-1,샴페인-1:2",
            "해산물파스타-3,레드와인-1,아이스크림-1,샴페인-1:3"
    }, delimiter = ':')
    void getCountOfMainMenu(String orders, int expected) {
        Map<Menu, Integer> menuAndCount = inputParser.parseOrderMenuAndCount(orders);
        Order order = new Order(menuAndCount);
        assertThat(order.getCountOfMainMenu()).isEqualTo(expected);
    }

    @DisplayName("디저트 메뉴 개수를 계산한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "양송이수프-1:0",
            "아이스크림-1,초코케이크-4:5",
            "티본스테이크-2,제로콜라-3,아이스크림-3,샴페인-1:3",
            "해산물파스타-3,레드와인-1,초코케이크-5,샴페인-1:5"
    }, delimiter = ':')
    void getCountOfDessertMenu(String orders, int expected) {
        Map<Menu, Integer> menuAndCount = inputParser.parseOrderMenuAndCount(orders);
        Order order = new Order(menuAndCount);
        assertThat(order.getCountOfDessertMenu()).isEqualTo(expected);
    }
}