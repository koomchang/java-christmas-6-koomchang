package christmas.controller;

import christmas.model.Badge;
import christmas.model.Day;
import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Order;
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
        Order order = new Order(parseOrderMenuAndCount(orders));
        Event event = new Event();
        int totalPrice = order.getTotalPrice();
        outputView.printTotalPriceBeforeDiscount(totalPrice);
        boolean giftEventEligible = event.isGiftEventEligible(totalPrice);
        outputView.printGiftMenu(giftEventEligible);
        int christmasDiscount = event.christmasDiscount(visitDate);
        int menuDiscount = 0;
        if (Day.of(visitDate).isWeekend()) {
            menuDiscount = event.menuDiscount(order.getCountOfMainMenu());
        }
        if (!Day.of(visitDate).isWeekend()) {
            menuDiscount = event.menuDiscount(order.getCountOfDesertMenu());
        }
        int specialDiscount = event.specialDiscount(visitDate);
        outputView.printBenefits(visitDate, christmasDiscount, menuDiscount, specialDiscount, giftEventEligible);
        int totalBenefitsPrice = 0;
        if (giftEventEligible) {
            totalBenefitsPrice = christmasDiscount + menuDiscount + specialDiscount + Menu.gift().getPrice();
        }
        if (!giftEventEligible) {
            totalBenefitsPrice = christmasDiscount + menuDiscount + specialDiscount;
        }
        outputView.printTotalBenefitsPrice(totalBenefitsPrice);
        outputView.printTotalPriceAfterDiscount(totalPrice - totalBenefitsPrice);
        Badge badge = Badge.getBadge(totalBenefitsPrice);
        outputView.printEventBadge(badge);
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
