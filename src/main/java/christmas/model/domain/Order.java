package christmas.model.domain;

import christmas.model.domain.constant.Menu;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;

public class Order {

    private final LocalDate visitDate;
    private final EnumMap<Menu, List<Integer>> orderMenu;
    private final int totalPrice;

    public Order(LocalDate visitDate, EnumMap<Menu, List<Integer>> orderMenu) {
        this.visitDate = visitDate;
        this.orderMenu = orderMenu;
        this.totalPrice = calculateOrder();
    }

    private int calculateOrder() {
        return orderMenu.values().stream().mapToInt(menu -> menu.get(1)).sum();
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public EnumMap<Menu, List<Integer>> getOrderMenu() {
        return orderMenu;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
