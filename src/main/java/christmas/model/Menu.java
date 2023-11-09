package christmas.model;

public enum Menu {

    양송이수프("애피타이저", 6_000),
    타파스("애피타이저", 5_500),
    시저샐러드("애피타이저", 8_000),
    티본스테이크("메인", 55_000),
    바비큐립("메인", 54_000),
    해산물파스타("메인", 35_000),
    크리스마스파스타("메인", 25_000),
    초코케이크("디저트", 15_000),
    아이스크림("디저트", 5_000),
    제로콜라("음료", 3_000),
    레드와인("음료", 60_000),
    샴페인("음료", 25_000);

    private final String type;
    private final int price;

    Menu(String type, int price) {
        this.type = type;
        this.price = price;
    }
}
