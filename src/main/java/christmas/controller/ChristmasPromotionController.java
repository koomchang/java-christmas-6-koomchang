package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private final OutputView outputView;
    private final InputView inputView;

    public ChristmasPromotionController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        int visitDate = inputView.inputVisitDate();
        inputView.inputOrderMenuAndCount();
        outputView.printEventMessage(visitDate);
//        outputView.printOrderedMenu();
//        outputView.printTotalPriceBeforeDiscount();
        outputView.printGiftMenu();
        outputView.printBenefits();
        outputView.printTotalBenefitsPrice();
        outputView.printTotalPriceAfterDiscount();
//        outputView.printEventBadge();
    }
}
