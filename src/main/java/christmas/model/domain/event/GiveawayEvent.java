package christmas.model.domain.event;

import christmas.model.domain.Order;
import christmas.model.domain.event.config.EventConfig;
import christmas.model.domain.event.constant.EventConstant;
import christmas.model.domain.event.eventStructure.Event;
import christmas.model.domain.event.eventStructure.Giveaway;
import java.time.LocalDate;
import java.util.List;

public class GiveawayEvent implements Event, Giveaway {

    public static final int PRICE_QUALIFICATION = 120_000;
    public static final int GIVEAWAY_PRICE = 25_000;
    private Order order;
    private final List<LocalDate> eventDates = EventConfig.getInstance().specialDate();

    @Override
    public EventApplyResponse applyEvent() {
        return new EventApplyResponse(EventConstant.GIVEAWAY, GIVEAWAY_PRICE);
    }

    @Override
    public boolean isApplicable(Order order) {
        if (eventDates.contains(order.getVisitDate()) && isPromotionAvailable()) {
            this.order = order;
            return true;
        }
        return false;
    }

    @Override
    public boolean isPromotionAvailable() {
        return order.getTotalPrice() >= PRICE_QUALIFICATION;
    }
}
