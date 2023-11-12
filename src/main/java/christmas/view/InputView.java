package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.enums.Menu;
import christmas.validator.BasicValidator;
import java.util.EnumMap;
import java.util.Map;

public class InputView {
    private static final String VISIT_DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final BasicValidator<String> visitDateInputValidator;
    private final BasicValidator<String> orderMenuAndCountInputValidator;

    public InputView(
            BasicValidator<String> visitDateInputValidator,
            BasicValidator<String> orderMenuAndCountInputValidator
    ) {
        this.visitDateInputValidator = visitDateInputValidator;
        this.orderMenuAndCountInputValidator = orderMenuAndCountInputValidator;
    }

    public int inputVisitDate() {
        System.out.println(VISIT_DATE_PROMPT);
        String input = Console.readLine();
        visitDateInputValidator.validate(input);
        return Integer.parseInt(input);
    }

    public Map<Menu, Integer> inputOrderMenuAndCount() {
        System.out.println(ORDER_PROMPT);
        String input = Console.readLine();
        orderMenuAndCountInputValidator.validate(input);
        return parseOrderMenuAndCount(input);
    }

    private Map<Menu, Integer> parseOrderMenuAndCount(String ordersInput) {
        Map<Menu, Integer> ordersMap = new EnumMap<>(Menu.class);
        String[] orders = ordersInput.split(",");
        for (String order : orders) {
            String[] menuAndCount = order.split("-");
            String menuName = menuAndCount[0];
            String countStr = menuAndCount[1];
            Menu menu = Menu.of(menuName);
            int count = Integer.parseInt(countStr);
            ordersMap.put(menu, count);
        }
        return ordersMap;
    }
}
