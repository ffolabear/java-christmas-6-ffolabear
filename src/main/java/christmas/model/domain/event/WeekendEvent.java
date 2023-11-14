package christmas.model.domain.event;

import christmas.model.domain.Order;
import christmas.model.domain.constant.Menu;
import christmas.model.domain.constant.Type;
import christmas.model.domain.event.config.EventConfig;
import christmas.model.domain.event.constant.EventConstant;
import christmas.model.domain.event.eventStructure.Discount;
import christmas.model.domain.event.eventStructure.Event;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;

public class WeekendEvent implements Event, Discount {

    public static final int WEEKEND_DISCOUNT = 2_023;
    private int mainCount;
    private Order order;
    private final List<LocalDate> eventDates = EventConfig.getInstance().weekendDate();


    @Override
    public EventApplyResponse applyEvent() {
        countMain(order.getOrderMenu());
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

    private void countMain(EnumMap<Menu, List<Integer>> orderMenu) {
        mainCount = (int) orderMenu.keySet().stream()
                .filter(menu -> menu.getType() == Type.MAIN)
                .count();
    }

    @Override
    public int applyDiscount() {
        return mainCount * WEEKEND_DISCOUNT;
    }
}
