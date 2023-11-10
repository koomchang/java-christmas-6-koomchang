package christmas.validator;

import static christmas.exception.ExceptionMessages.ORDER_INVALID;

import christmas.model.Menu;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class OrderMenuAndCountInputValidator implements BasicValidator<String> {

    private final Map<Menu, Integer> menuAndCount;

    public OrderMenuAndCountInputValidator() {
        this.menuAndCount = new EnumMap<>(Menu.class);
    }

    @Override
    public void validate(String input) {
        validateMenuFormat(input);
        validateMenuInMenuList(menuAndCount);
        validateDuplicateMenu(menuAndCount);
    }

    private void validateMenuFormat(String input) {
        String[] orders = input.split(",");
        for (String order : orders) {
            String[] menuAndCount = order.split("-");
            validateMenuAndCountLength(menuAndCount);
            String menu = menuAndCount[0];
            String count = menuAndCount[1];
            validateIfMenuAndCountEmpty(menu, count);
            validateIfCountNumeric(count);
            this.menuAndCount.put(Menu.of(menu), Integer.parseInt(count));
        }
    }

    private void validateIfCountNumeric(String count) {
        try {
            Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    private void validateIfMenuAndCountEmpty(String menu, String count) {
        if (menu.isEmpty() || count.isEmpty()) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    private void validateMenuAndCountLength(String[] menuAndCount) {
        if (menuAndCount.length != 2) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    private void validateMenuInMenuList(Map<Menu, Integer> menus) {
        for (Menu menu : menus.keySet()) {
            if (!menuAndCount.containsKey(menu)) {
                throw new IllegalArgumentException(ORDER_INVALID.getMessage());
            }
        }
    }

    private void validateDuplicateMenu(Map<Menu, Integer> menus) {
        EnumSet<Menu> uniqueMenus = EnumSet.noneOf(Menu.class);
        for (Menu menu : menus.keySet()) {
            if (!uniqueMenus.add(menu)) {
                throw new IllegalArgumentException(ORDER_INVALID.getMessage());
            }
        }
    }
}
