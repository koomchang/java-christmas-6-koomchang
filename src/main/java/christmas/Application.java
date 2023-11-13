package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.model.EventPlanner;
import christmas.model.policy.ChristmasDiscountPolicy;
import christmas.model.policy.DayDiscountPolicy;
import christmas.model.policy.GiftEventPolicy;
import christmas.model.policy.SpecialDiscountPolicy;
import christmas.validator.OrderMenuAndCountInputValidator;
import christmas.validator.VisitDateInputValidator;
import christmas.view.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final EventPlanner eventPlanner = new EventPlanner(
                new ChristmasDiscountPolicy(),
                new DayDiscountPolicy(),
                new GiftEventPolicy(),
                new SpecialDiscountPolicy()
        );

        final ChristmasPromotionController christmasPromotionController = new ChristmasPromotionController(
                new OutputView(),
                new InputView(
                        new VisitDateInputValidator(),
                        new OrderMenuAndCountInputValidator(),
                        new InputParser()),
                eventPlanner);

        christmasPromotionController.run();
    }
}
