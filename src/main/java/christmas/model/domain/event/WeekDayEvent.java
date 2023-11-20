package christmas.model.domain.event;

import christmas.model.domain.Order;
import christmas.model.domain.constant.Menu;
import christmas.model.domain.constant.MenuType;
import christmas.model.domain.event.config.EventConfig;
import christmas.model.domain.event.constant.EventConstant;
import christmas.model.domain.event.eventStructure.Discount;
import christmas.model.domain.event.eventStructure.Event;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;

public class WeekDayEvent implements Event, Discount {

    private static final int ORDER_QUANTITY_INDEX = 0;
    public static final int WEEKDAY_DISCOUNT = 2_023;
    private int dessertCount;
    private Order order;
    private final List<LocalDate> eventDates = EventConfig.getInstance().weekdayDate();

    @Override
    public EventApplyResponse applyEvent() {
        countDessert(order.getOrderMenu());
        int discountedAmount = applyDiscount();
        return new EventApplyResponse(EventConstant.WEEKDAY, discountedAmount);
    }

    @Override
    public boolean isApplicable(Order order) {
        if (eventDates.contains(order.getVisitDate())) {
            this.order = order;
            return true;
        }
        return false;
    }

    private void countDessert(EnumMap<Menu, List<Integer>> orderMenu) {
        dessertCount = orderMenu.entrySet().stream()
                .filter(entry -> entry.getKey().getType() == MenuType.DESSERT)
                .map(entry -> entry.getValue().get(ORDER_QUANTITY_INDEX))
                .reduce(0, Integer::sum);
    }

    @Override
    public int applyDiscount() {
        return dessertCount * WEEKDAY_DISCOUNT;
    }

}
