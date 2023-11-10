package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;
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
//        outputView.printOrderedMenu();
//        outputView.printTotalPriceBeforeDiscount();
        outputView.printGiftMenu();
        outputView.printBenefits();
        outputView.printTotalBenefitsPrice();
        outputView.printTotalPriceAfterDiscount();
//        outputView.printEventBadge();
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
