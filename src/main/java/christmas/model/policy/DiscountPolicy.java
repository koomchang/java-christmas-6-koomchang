package christmas.model.policy;

import christmas.model.Order;
import christmas.model.vo.Date;
import christmas.model.vo.Money;

public interface DiscountPolicy {
    Money calculate(Date eventDate, Order order);
}
