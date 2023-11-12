package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.model.policy.ChristmasDiscountPolicy;
import christmas.model.policy.DayDiscountPolicy;
import christmas.model.EventPlanner;
import christmas.model.policy.GiftEventPolicy;
import christmas.validator.OrderMenuAndCountInputValidator;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner(
                new ChristmasDiscountPolicy(),
                new DayDiscountPolicy(),
                new GiftEventPolicy()
        );

        ChristmasPromotionController christmasPromotionController = new ChristmasPromotionController(
                new OutputView(),
                new InputView(
                        new VisitDateInputValidator(),
                        new OrderMenuAndCountInputValidator()
                ),
                eventPlanner);

        christmasPromotionController.run();
    }
}
