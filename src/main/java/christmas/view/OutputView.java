package christmas.view;

import christmas.model.Badge;
import christmas.model.Menu;
import java.util.Map;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDERED_MENU_MESSAGE = "<주문 메뉴>";
    private static final String EACH_MENU_MESSAGE = "%s %d개" + LINE_SEPARATOR;
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE = "<할인 전 총주문 금액>" + LINE_SEPARATOR + "%d원";
    private static final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>" + LINE_SEPARATOR + "%s";

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printEventMessage(int visitDay) {
        System.out.printf(EVENT_MESSAGE, visitDay);
    }

    public void printOrderedMenu(Map<Menu, Integer> menus) {
        System.out.println(ORDERED_MENU_MESSAGE);
        for (Menu menu : menus.keySet()) {
            System.out.printf(EACH_MENU_MESSAGE, menu, menus.get(menu));
        }
    }

    public void printTotalPriceBeforeDiscount(int price) {
        System.out.printf(TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE, price);
    }

    public void printGiftMenu() {

    }

    public void printBenefits() {

    }

    public void printTotalBenefitsPrice() {

    }

    public void printTotalPriceAfterDiscount() {

    }

    public void printEventBadge(Badge badge) {
        System.out.printf(EVENT_BADGE_MESSAGE, badge);
    }
}
