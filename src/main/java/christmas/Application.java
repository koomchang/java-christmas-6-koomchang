package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasPromotionController christmasPromotionController = new ChristmasPromotionController(
                new OutputView(),
                new InputView(new VisitDateInputValidator()));
        christmasPromotionController.run();
    }
}
