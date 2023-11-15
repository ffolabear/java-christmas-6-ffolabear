package christmas.model.domain.event;

import christmas.model.domain.Order;
import christmas.model.domain.event.config.EventConfig;
import christmas.model.domain.event.constant.EventConstant;
import christmas.model.domain.event.eventStructure.Discount;
import christmas.model.domain.event.eventStructure.Event;
import java.time.LocalDate;

public class ChristmasEvent implements Event, Discount {

    public static final int CHRISTMAS_DISCOUNT_START = 3_400;
    public static final int CHRISTMAS_DISCOUNT_INCREMENT = 100;
    public static int currentChristmasDiscount = 0;


    @Override
    public EventApplyResponse applyEvent() {
        int discountedAmount = applyDiscount();
        return new EventApplyResponse(EventConstant.CHRISTMAS, discountedAmount);
    }

    @Override
    public boolean isApplicable(Order order) {
        LocalDate visitDate = order.getVisitDate();
        if (visitDate.getDayOfMonth() <= EventConfig.CHRISTMAS.getDayOfMonth()) {
            updateDiscountAmount(visitDate);
            return true;
        }
        return false;
    }

    @Override
    public int applyDiscount() {
        return currentChristmasDiscount;
    }

    private void updateDiscountAmount(LocalDate visitDate) {
        int daysUntilChristmas = EventConfig.CHRISTMAS.getDayOfMonth() - visitDate.getDayOfMonth();
        currentChristmasDiscount = CHRISTMAS_DISCOUNT_START - daysUntilChristmas * CHRISTMAS_DISCOUNT_INCREMENT;
    }
}
