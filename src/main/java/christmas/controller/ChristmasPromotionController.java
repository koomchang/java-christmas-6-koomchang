package christmas.controller;

import christmas.model.EventPlanner;
import christmas.model.Order;
import christmas.model.enums.Menu;
import christmas.model.vo.Date;
import christmas.model.vo.Money;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.function.Supplier;

public class ChristmasPromotionController {
    private final OutputView outputView;
    private final InputView inputView;
    private final EventPlanner eventPlanner;

    public ChristmasPromotionController(OutputView outputView, InputView inputView, EventPlanner eventPlanner) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.eventPlanner = eventPlanner;
    }

    public void run() {
        outputView.printWelcomeMessage();
        Date eventDate = new Date(exceptionHandleAndRetry(inputView::inputVisitDate));
        Map<Menu, Integer> orders = exceptionHandleAndRetry(inputView::inputOrderMenuAndCount);
        processEventPlan(eventDate, orders);
    }

    private void processEventPlan(Date eventDate, Map<Menu, Integer> orders) {
        outputView.printEventMessage(eventDate.date());
        outputView.printOrderedMenu(orders);
        Order order = new Order(orders);
        Money totalDiscount = eventPlanner.getTotalDiscountAmount(eventDate, order);
        outputView.printTotalPriceBeforeDiscount(order.getTotalPrice().value());
        outputView.printGiftMenu(order.isGiftEventEligible());
        printBenefits(order, eventDate);
        outputView.printTotalBenefitsPrice(totalDiscount.value());
        outputView.printTotalPriceAfterDiscount(order.getTotalPrice().minus(totalDiscount).value());
        outputView.printEventBadge(eventPlanner.getBadge(eventDate, order));
    }

    private void printBenefits(Order order, Date eventDate) {
        boolean isVisitDateWeekend = Date.isWeekend(eventDate);
        int christmasDiscount = eventPlanner.getChristmasDiscount(eventDate, order).value();
        int dayDiscount = eventPlanner.getDayDiscount(eventDate, order).value();
        int giftEventDiscount = eventPlanner.getGiftEventDiscount(eventDate, order).value();
        int specialDiscount = eventPlanner.getSpecialDiscount(eventDate, order).value();
        if (!order.canParticipateInEvent() ||
                hasNoDiscount(christmasDiscount, dayDiscount, giftEventDiscount, specialDiscount)) {
            outputView.printBenefits();
        }
        if (order.canParticipateInEvent()) {
            outputView.printBenefits(
                    isVisitDateWeekend, christmasDiscount, dayDiscount, specialDiscount, giftEventDiscount
            );
        }
    }

    private boolean hasNoDiscount(int christmasDiscount, int dayDiscount, int giftEventDiscount,
                                  int specialDiscount) {
        return christmasDiscount == 0 && dayDiscount == 0 && giftEventDiscount == 0
                && specialDiscount == 0;
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
