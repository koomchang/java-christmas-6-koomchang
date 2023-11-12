package christmas.model;

import static christmas.exception.ExceptionMessages.ORDER_INVALID;

import java.util.EnumSet;
import java.util.Map;

public class Order {
    private static final int MINIMUM_PRICE_FOR_EVENT = 10000;
    private static final Money MINIMUM_PURCHASE_AMOUNT = new Money(120_000);

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
        EnumSet<Menu> uniqueMenus = EnumSet.noneOf(Menu.class);
        for (Menu menu : menus.keySet()) {
            if (!uniqueMenus.add(menu)) {
                throw new IllegalArgumentException(ORDER_INVALID.getMessage());
            }
        }
    }

    private void validateMenuCount(Map<Menu, Integer> menus) {
        if (menus.values().stream()
                .mapToInt(Integer::intValue)
                .sum() > 20) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    private void validateOnlyBeverage(Map<Menu, Integer> menus) {
        if (hasOnlyBeverage(menus)) {
            throw new IllegalArgumentException(ORDER_INVALID.getMessage());
        }
    }

    public Money getTotalPrice() {
        int totalPriceValue = menus.keySet().stream()
                .mapToInt(menu -> menu.getPrice() * menus.get(menu))
                .sum();
        return new Money(totalPriceValue);
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

    public boolean isGiftEventEligible() {
        return getTotalPrice().isGreaterThanOrEqual(MINIMUM_PURCHASE_AMOUNT);
    }

    public int getCountOfMainMenu() {
        return (int) menus.keySet().stream()
                .filter(menu -> menu.getType().equals("메인"))
                .count();
    }

    public int getCountOfDesertMenu() {
        return (int) menus.keySet().stream()
                .filter(menu -> menu.getType().equals("디저트"))
                .count();
    }
}
