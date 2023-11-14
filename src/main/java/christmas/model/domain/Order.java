package christmas.model.domain;

import christmas.model.domain.constant.Menu;
import christmas.model.domain.event.EventApplyResponse;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;

public class Order {

    private final LocalDate visitDate;
    private final EnumMap<Menu, List<Integer>> orderMenu;
    private List<EventApplyResponse> applyEvent;
    private final int totalPrice;

    public Order(LocalDate visitDate, EnumMap<Menu, List<Integer>> orderMenu) {
        this.visitDate = visitDate;
        this.orderMenu = orderMenu;
        this.totalPrice = calculateOrder();
    }

    private int calculateOrder() {
        return orderMenu.values().stream().mapToInt(menu -> menu.get(1)).sum();
    }

    public void checkOrder() {
        EventChecker eventChecker = new EventChecker();
        this.applyEvent = eventChecker.checkOrder(this);
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public EnumMap<Menu, List<Integer>> getOrderMenu() {
        return orderMenu;
    }

    public List<EventApplyResponse> getApplyEvent() {
        return applyEvent;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
