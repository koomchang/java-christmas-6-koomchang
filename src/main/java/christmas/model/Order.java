package christmas.model;

import static christmas.exception.ExceptionMessages.ORDER_INVALID;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {
    private static final int MINIMUM_PRICE_FOR_EVENT = 10000;

    private final Map<Menu, Integer> menus;

    public Order(Map<Menu, Integer> menus) {
        validate(menus);
        this.menus = menus;
    }

    public void validate(Map<Menu, Integer> menus) {
        validateDuplicateMenu(menus);
        validateMenuCount(menus);
        validateOnlyBeverage(menus);
    }

    private void validateDuplicateMenu(Map<Menu, Integer> menus) {
        Set<Menu> uniqueMenus = new HashSet<>(menus.keySet());
        if (uniqueMenus.size() != menus.size()) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    private void validateMenuCount(Map<Menu, Integer> menus) {
        if (menus.size() > 20) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    private void validateOnlyBeverage(Map<Menu, Integer> menus) {
        if (hasOnlyBeverage(menus)) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    private boolean hasOnlyBeverage(Map<Menu, Integer> menus) {
        return menus.keySet().stream()
                .allMatch(menu -> menu.getType().equals("음료"));
    }

    public boolean canParticipateInEvent() {
        return menus.keySet().stream()
                .mapToInt(Menu::getPrice)
                .sum() >= MINIMUM_PRICE_FOR_EVENT;
    }
}
