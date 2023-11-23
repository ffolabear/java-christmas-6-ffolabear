package christmas.model.domain.event;

import christmas.model.domain.Order;
import christmas.model.domain.event.config.EventConfig;
import christmas.model.domain.event.constant.EventConstant;
import christmas.model.domain.event.eventStructure.Discount;
import christmas.model.domain.event.eventStructure.Event;
import java.time.LocalDate;

public class ChristmasEvent implements Event, Discount {

    private static final int CHRISTMAS_DISCOUNT_START = 3_400;
    private static final int CHRISTMAS_DISCOUNT_INCREMENT = 100;
    private final int currentChristmasDiscount;

    public ChristmasEvent(LocalDate visitDate) {
        this.currentChristmasDiscount = updateDiscountAmount(visitDate);
    }

    @Override
    public EventApplyResponse applyEvent() {
        int discountedAmount = applyDiscount();
        return new EventApplyResponse(EventConstant.CHRISTMAS, discountedAmount);
    }

    @Override
    public boolean isApplicable(Order order) {
        LocalDate visitDate = order.getVisitDate();
        return visitDate.getDayOfMonth() <= EventConfig.CHRISTMAS.getDayOfMonth();
    }

    @Override
    public int applyDiscount() {
        return currentChristmasDiscount;
    }

    private int updateDiscountAmount(LocalDate visitDate) {
        int daysUntilChristmas = EventConfig.CHRISTMAS.getDayOfMonth() - visitDate.getDayOfMonth();
        return CHRISTMAS_DISCOUNT_START - daysUntilChristmas * CHRISTMAS_DISCOUNT_INCREMENT;
    }
}
