package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

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
