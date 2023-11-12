package christmas.model;

public interface DiscountPolicy {

    Money calculate(Date eventDate, Order order);
}
