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
        Date visitDate = new Date(exceptionHandleAndRetry(inputView::inputVisitDate));
        Map<Menu, Integer> orders = exceptionHandleAndRetry(inputView::inputOrderMenuAndCount);
        processEventPlan(visitDate, orders);
    }

    private void processEventPlan(Date visitDate, Map<Menu, Integer> orders) {
        outputView.printEventMessage(visitDate.date());
        outputView.printOrderedMenu(orders);
        Order order = new Order(orders);
        Money totalDiscount = eventPlanner.getTotalDiscountAmount(visitDate, order);
        outputView.printTotalPriceBeforeDiscount(order.getTotalPrice().value());
        outputView.printGiftMenu(order.isGiftEventEligible());
        printBenefits(order, visitDate);
        outputView.printTotalBenefitsPrice(totalDiscount.value());
        outputView.printTotalPriceAfterDiscount(order.getTotalPrice().minus(totalDiscount).value());
        outputView.printEventBadge(eventPlanner.getBadge(visitDate, order));
    }

    private void printBenefits(Order order, Date visitDate) {
        boolean isVisitDateWeekend = Date.isWeekend(visitDate);
        int christmasDiscount = eventPlanner.getChristmasDiscount(visitDate, order).value();
        int dayDiscount = eventPlanner.getDayDiscount(visitDate, order).value();
        int giftEventDiscount = eventPlanner.getGiftEventDiscount(visitDate, order).value();
        if (!order.canParticipateInEvent() || (christmasDiscount == 0 && dayDiscount == 0 && giftEventDiscount == 0)) {
            outputView.printBenefits();
        }
        if (order.canParticipateInEvent()) {
            outputView.printBenefits(
                    isVisitDateWeekend,
                    christmasDiscount,
                    dayDiscount,
                    giftEventDiscount
            );
        }
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
