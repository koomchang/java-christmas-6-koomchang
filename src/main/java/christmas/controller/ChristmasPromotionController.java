package christmas.controller;

import christmas.model.Badge;
import christmas.model.Day;
import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
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
        Map<Menu, Integer> orders = exceptionHandleAndRetry(inputView::inputOrderMenuAndCount);
        outputView.printEventMessage(visitDate);
        outputView.printOrderedMenu(orders);
        Order order = new Order(orders);
        Event event = new Event();
        int totalPrice = order.getTotalPrice();
        if (!order.canParticipateInEvent()) {
            outputView.printTotalPriceBeforeDiscount(totalPrice);
            outputView.printGiftMenu(false);
            outputView.printBenefits(visitDate, 0, 0, 0, false);
            outputView.printTotalBenefitsPrice(0);
            outputView.printTotalPriceAfterDiscount(totalPrice);
            outputView.printEventBadge(Badge.없음);
            return;
        }
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


    private <T> T exceptionHandleAndRetry(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return exceptionHandleAndRetry(supplier);
        }
    }
}
