package christmas.view;

import christmas.model.enums.Menu;
import java.util.EnumMap;
import java.util.Map;

public class InputParser {

    public Map<Menu, Integer> parseOrderMenuAndCount(String ordersInput) {
        Map<Menu, Integer> ordersMap = new EnumMap<>(Menu.class);
        String[] orders = ordersInput.split(",");
        for (String order : orders) {
            String[] menuAndCount = order.split("-");
            String menuName = menuAndCount[0];
            String countStr = menuAndCount[1];
            Menu menu = Menu.of(menuName);
            int count = Integer.parseInt(countStr);
            ordersMap.put(menu, count);
        }
        return ordersMap;
    }
}
