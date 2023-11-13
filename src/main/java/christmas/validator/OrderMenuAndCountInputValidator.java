package christmas.validator;

import christmas.exception.OrderInvalidException;
import christmas.model.enums.Menu;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderMenuAndCountInputValidator implements BasicValidator<String> {
    private static final int MAX_MENU_COUNT = 20;

    private final Map<Menu, Integer> menuAndCount;

    public OrderMenuAndCountInputValidator() {
        this.menuAndCount = new EnumMap<>(Menu.class);
    }

    @Override
    public void validate(String input) {
        validateMenuFormat(input);
        validateDuplicateMenu(input);
        validateMenuInMenuList(menuAndCount);
        validateOnlyBeverage(menuAndCount);
        validateMenuCount(menuAndCount);
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
            validateEndsWithComma(input);
            this.menuAndCount.put(Menu.of(menu), Integer.parseInt(count));
        }
    }

    private void validateOnlyBeverage(Map<Menu, Integer> menus) {
        if (hasOnlyBeverage(menus)) {
            throw new OrderInvalidException();
        }
    }

    private boolean hasOnlyBeverage(Map<Menu, Integer> menus) {
        return menus.keySet().stream()
                .allMatch(Menu::isBeverage);
    }

    private void validateIfCountNumeric(String count) {
        try {
            Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new OrderInvalidException();
        }
    }

    private void validateEndsWithComma(String input) {
        if (input.endsWith(",")) {
            throw new OrderInvalidException();
        }
    }

    private void validateIfMenuAndCountEmpty(String menu, String count) {
        if (menu.isEmpty() || count.isEmpty()) {
            throw new OrderInvalidException();
        }
    }

    private void validateMenuAndCountLength(String[] menuAndCount) {
        if (menuAndCount.length != 2) {
            throw new OrderInvalidException();
        }
    }

    private void validateMenuInMenuList(Map<Menu, Integer> menus) {
        for (Menu menu : menus.keySet()) {
            if (!menuAndCount.containsKey(menu)) {
                throw new OrderInvalidException();
            }
        }
    }

    private void validateDuplicateMenu(String input) {
        Set<String> uniqueMenus = new HashSet<>();
        String[] orders = input.split(",");
        for (String order : orders) {
            String[] menuAndCount = order.split("-");
            String menuName = menuAndCount[0];
            if (!uniqueMenus.add(menuName)) {
                throw new OrderInvalidException();
            }
        }
    }

    private void validateMenuCount(Map<Menu, Integer> menus) {
        if (menus.values().stream()
                .mapToInt(Integer::intValue)
                .sum() > MAX_MENU_COUNT) {
            throw new OrderInvalidException();
        }
    }
}
