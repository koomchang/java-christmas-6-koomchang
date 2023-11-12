package christmas.model.enums;

import christmas.exception.OrderInvalidException;

public enum Menu {
    양송이수프(MenuType.애피타이저, 6_000),
    타파스(MenuType.애피타이저, 5_500),
    시저샐러드(MenuType.애피타이저, 8_000),
    티본스테이크(MenuType.메인, 55_000),
    바비큐립(MenuType.메인, 54_000),
    해산물파스타(MenuType.메인, 35_000),
    크리스마스파스타(MenuType.메인, 25_000),
    초코케이크(MenuType.디저트, 15_000),
    아이스크림(MenuType.디저트, 5_000),
    제로콜라(MenuType.음료, 3_000),
    레드와인(MenuType.음료, 60_000),
    샴페인(MenuType.음료, 25_000);

    enum MenuType {
        애피타이저, 메인, 디저트, 음료
    }

    private final MenuType type;
    private final int price;

    Menu(MenuType type, int price) {
        this.type = type;
        this.price = price;
    }

    public static Menu of(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.name().equals(menuName)) {
                return menu;
            }
        }
        throw new OrderInvalidException();
    }

    public static Menu gift() {
        return 샴페인;
    }

    public boolean isBeverage() {
        return type == MenuType.음료;
    }

    public boolean isMain() {
        return type == MenuType.메인;
    }

    public boolean isDessert() {
        return type == MenuType.디저트;
    }

    public String getType() {
        return type.name();
    }

    public int getPrice() {
        return price;
    }
}
