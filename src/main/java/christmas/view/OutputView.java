package christmas.view;

public class OutputView {
    private static final String WELECOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printWelcomeMessage() {
        System.out.println(WELECOME_MESSAGE);
    }

    public void printEventMessage(int visitDay) {
        System.out.printf(EVENT_MESSAGE, visitDay);
    }

    public void printOrderedMenu() {

    }

    public void printGiftMenu() {

    }

    public void printBenefits() {

    }

    public void printTotalBenefitsPrice() {

    }

    public void printTotalPriceAfterDiscount() {

    }

    public void printEventBadge() {

    }
}
