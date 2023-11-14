package christmas.model.domain.event;

import christmas.model.domain.Order;
import christmas.model.domain.event.config.EventConfig;
import christmas.model.domain.event.constant.EventConstant;
import christmas.model.domain.event.eventStructure.Discount;
import christmas.model.domain.event.eventStructure.Event;
import java.time.LocalDate;
import java.util.List;

public class SpecialEvent implements Event, Discount {

    public static final int SPECIAL_DISCOUNT = 1_000;
    private final List<LocalDate> eventDates = EventConfig.getInstance().specialDate();

    @Override
    public EventApplyResponse applyEvent() {
        int discountedAmount = applyDiscount();
        return new EventApplyResponse(EventConstant.SPECIAL, discountedAmount);
    }

    @Override
    public boolean isApplicable(Order order) {
        return eventDates.contains(order.getVisitDate());
    }

    @Override
    public int applyDiscount() {
        return SPECIAL_DISCOUNT;
    }
}
