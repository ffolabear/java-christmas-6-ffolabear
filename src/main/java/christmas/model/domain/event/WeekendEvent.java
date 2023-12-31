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

public class WeekendEvent implements Event, Discount {

    private static final int ORDER_QUANTITY_INDEX = 0;
    public static final int WEEKEND_DISCOUNT = 2_023;
    private int mainCount;
    private Order order;
    private final List<LocalDate> eventDates = EventConfig.getInstance().weekendDate();

    @Override
    public EventApplyResponse applyEvent() {
        countMain(order.getOrderMenu());
        int discountedAmount = applyDiscount();
        return new EventApplyResponse(EventConstant.WEEKEND, discountedAmount);
    }

    @Override
    public boolean isApplicable(Order order) {
        if (eventDates.contains(order.getVisitDate())) {
            this.order = order;
            return true;
        }
        return false;
    }

    private void countMain(EnumMap<Menu, List<Integer>> orderMenu) {
        mainCount = orderMenu.entrySet().stream()
                .filter(entry -> entry.getKey().getType() == MenuType.MAIN)
                .map(entry -> entry.getValue().get(ORDER_QUANTITY_INDEX))
                .reduce(0, Integer::sum);
    }

    @Override
    public int applyDiscount() {
        return mainCount * WEEKEND_DISCOUNT;
    }
}
