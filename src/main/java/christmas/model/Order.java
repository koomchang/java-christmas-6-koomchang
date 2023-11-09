package christmas.model;

import java.util.Map;

public class Order {
    private static final int MINIMUM_PRICE_FOR_EVENT = 10000;

    private final Map<Menu, Integer> menus;

    public Order(Map<Menu, Integer> menus) {
        this.menus = menus;
    }

    public boolean canParticipateInEvent() {
        return menus.keySet().stream()
                .mapToInt(Menu::getPrice)
                .sum() >= MINIMUM_PRICE_FOR_EVENT;
    }
}
