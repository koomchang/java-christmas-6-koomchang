package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.validator.BasicValidator;

//TODO: validation 추가
public class InputView {
    private static final String VISIT_DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MENU_AND_COUNT_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final BasicValidator<String> visitDateInputValidator;

    public InputView(BasicValidator<String> visitDateInputValidator) {
        this.visitDateInputValidator = visitDateInputValidator;
    }

    public int inputVisitDate() {
        System.out.println(VISIT_DATE_PROMPT);
        String input = Console.readLine();
        visitDateInputValidator.validate(input);
        return Integer.parseInt(input);
    }

    public String inputOrderMenuAndCount() {
        System.out.println(ORDER_MENU_AND_COUNT_PROMPT);
        return Console.readLine();
    }
}
