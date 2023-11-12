package christmas.view;

import christmas.model.enums.Badge;
import christmas.model.enums.Menu;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!" + LINE_SEPARATOR;
    private static final String ORDERED_MENU_MESSAGE = LINE_SEPARATOR + "<주문 메뉴>";
    private static final String EACH_MENU_MESSAGE = "%s %d개" + LINE_SEPARATOR;
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE = LINE_SEPARATOR + "<할인 전 총주문 금액>";
    private static final String TOTAL_PRICE_BEFORE_DISCOUNT = "%s원" + LINE_SEPARATOR;
    private static final String GIFT_MENU_MESSAGE = LINE_SEPARATOR + "<증정 메뉴>";
    private static final String GIFT_MENU = "%s" + LINE_SEPARATOR;
    private static final String BENEFITS_MESSAGE = LINE_SEPARATOR + "<혜택 내역>";
    private static final String CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인: -%s원" + LINE_SEPARATOR;
    private static final String WEEKDAY_DISCOUNT = "평일 할인: -%s원" + LINE_SEPARATOR;
    private static final String WEEKEND_DISCOUNT = "주말 할인: -%s원" + LINE_SEPARATOR;
    private static final String SPECIAL_DISCOUNT = "특별 할인: -%s원" + LINE_SEPARATOR;
    private static final String GIFT_EVENT = "증정 이벤트: -%s원" + LINE_SEPARATOR;
    private static final String TOTAL_BENEFITS_PRICE_MESSAGE = LINE_SEPARATOR + "<총혜택 금액>";
    private static final String TOTAL_BENEFITS_PRICE = "%s원" + LINE_SEPARATOR;
    private static final String TOTAL_PRICE_AFTER_DISCOUNT_MESSAGE = LINE_SEPARATOR + "<할인 후 예상 결제 금액>";
    private static final String TOTAL_PRICE_AFTER_DISCOUNT = "%s원" + LINE_SEPARATOR;
    private static final String EVENT_BADGE_MESSAGE = LINE_SEPARATOR + "<12월 이벤트 배지>";
    private static final String EVENT_BADGE = "%s" + LINE_SEPARATOR;
    private static final String NOTHING = "없음";

    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");

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
        String formatPrice = formatPriceWithComma(price);
        System.out.println(TOTAL_PRICE_BEFORE_DISCOUNT_MESSAGE);
        System.out.printf(TOTAL_PRICE_BEFORE_DISCOUNT, formatPrice);
    }

    public void printGiftMenu(boolean isGiftEventEligible) {
        System.out.println(GIFT_MENU_MESSAGE);
        if (isGiftEventEligible) {
            System.out.printf(GIFT_MENU, Menu.gift());
            return;
        }
        System.out.println(NOTHING);
    }

    public void printBenefits(boolean isWeekend, int christmasDiscount, int dayDiscount, int specialDiscount) {
        System.out.println(BENEFITS_MESSAGE);
        printBenefitsForChristmasDiscount(christmasDiscount);
        printBenefitsForWeekday(isWeekend, dayDiscount);
        printBenefitsForWeekend(isWeekend, dayDiscount);
        printBenefitsForSpecialDiscount(specialDiscount);
    }

    public void printBenefits() {
        System.out.println(BENEFITS_MESSAGE);
        System.out.println(NOTHING);
    }

    private void printBenefitsForChristmasDiscount(int christmasDiscount) {
        if (christmasDiscount > 0) {
            System.out.printf(CHRISTMAS_DISCOUNT, formatPriceWithComma(christmasDiscount));
        }
    }

    private void printBenefitsForSpecialDiscount(int specialDiscount) {
        if (specialDiscount > 0) {
            System.out.printf(SPECIAL_DISCOUNT, formatPriceWithComma(specialDiscount));
        }
    }

    private void printBenefitsForWeekend(boolean isWeekend, int weekendDiscount) {
        if (isWeekend && weekendDiscount > 0) {
            System.out.printf(WEEKEND_DISCOUNT, formatPriceWithComma(weekendDiscount));
        }
    }

    private void printBenefitsForWeekday(boolean isWeekend, int weekdayDiscount) {
        if (!isWeekend && weekdayDiscount > 0) {
            System.out.printf(WEEKDAY_DISCOUNT, formatPriceWithComma(weekdayDiscount));
        }
    }

    public void printTotalBenefitsPrice(int price) {
        System.out.println(TOTAL_BENEFITS_PRICE_MESSAGE);
        System.out.printf(TOTAL_BENEFITS_PRICE, formatPriceWithComma(price));
    }

    public void printTotalPriceAfterDiscount(int price) {
        System.out.println(TOTAL_PRICE_AFTER_DISCOUNT_MESSAGE);
        System.out.printf(TOTAL_PRICE_AFTER_DISCOUNT, formatPriceWithComma(price));
    }

    public void printEventBadge(Badge badge) {
        System.out.println(EVENT_BADGE_MESSAGE);
        System.out.printf(EVENT_BADGE, badge);
    }

    private String formatPriceWithComma(int price) {
        return decimalFormat.format(price);
    }
}
