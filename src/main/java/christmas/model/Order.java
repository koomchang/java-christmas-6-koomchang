package christmas.model;

import christmas.exception.OrderInvalidException;
import christmas.model.enums.Menu;
import christmas.model.vo.Money;
import java.util.Map;

public class Order {
    private static final int MINIMUM_PRICE_FOR_EVENT = 10_000;
    private static final Money MINIMUM_PURCHASE_AMOUNT = new Money(120_000);
    private static final int MAX_MENU_COUNT = 20;

    private final Map<Menu, Integer> menus;

    public Order(Map<Menu, Integer> menus) {
        validate(menus);
        this.menus = menus;
    }

    public void validate(Map<Menu, Integer> menus) {
        validateMenuCount(menus);
        validateOnlyBeverage(menus);
    }

    private void validateMenuCount(Map<Menu, Integer> menus) {
        if (menus.values().stream()
                .mapToInt(Integer::intValue)
                .sum() > MAX_MENU_COUNT) {
            throw new OrderInvalidException();
        }
    }

    private void validateOnlyBeverage(Map<Menu, Integer> menus) {
        if (hasOnlyBeverage(menus)) {
            throw new OrderInvalidException();
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
                .allMatch(Menu::isBeverage);
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
        return menus.entrySet().stream()
                .filter(entry -> entry.getKey().isMain())
                .mapToInt(Map.Entry::getValue)
                .sum();

    }

    public int getCountOfDessertMenu() {
        return menus.entrySet().stream()
                .filter(entry -> entry.getKey().isDessert())
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
