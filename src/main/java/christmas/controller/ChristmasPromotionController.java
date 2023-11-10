package christmas.controller;

import christmas.model.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class ChristmasPromotionController {
    private final OutputView outputView;
    private final InputView inputView;

    public ChristmasPromotionController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        int visitDate = exceptionHandleAndRetry(inputView::inputVisitDate);
        String orders = exceptionHandleAndRetry(inputView::inputOrderMenuAndCount);
        outputView.printEventMessage(visitDate);
        outputView.printOrderedMenu(parseOrderMenuAndCount(orders));
//        outputView.printTotalPriceBeforeDiscount();
        outputView.printGiftMenu();
        outputView.printBenefits();
        outputView.printTotalBenefitsPrice();
        outputView.printTotalPriceAfterDiscount();
//        outputView.printEventBadge();
    }

    private Map<Menu, Integer> parseOrderMenuAndCount(String ordersInput) {
        Map<Menu, Integer> ordersMap = new EnumMap<>(Menu.class);
        String[] orders = ordersInput.split(",");
        for (String order : orders) {
            String[] menuAndCount = order.split("-");
            String menuName = menuAndCount[0];
            String countStr = menuAndCount[1];
            Menu menu = Menu.of(menuName);
            int count = Integer.parseInt(countStr);
            ordersMap.put(menu, count);
        }
        return ordersMap;
    }

    private <T> T exceptionHandleAndRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return exceptionHandleAndRetry(supplier);
        }
    }
}
