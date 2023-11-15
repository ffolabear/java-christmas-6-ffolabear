package christmas.model.domain.event;

import christmas.model.domain.event.constant.EventConstant;

public final class EventApplyResponse {

    private final EventConstant event;
    private final int discountAmount;

    public EventApplyResponse(EventConstant event, int discountAmount) {
        this.event = event;
        this.discountAmount = discountAmount;
    }

    public EventConstant event() {
        return event;
    }

    public int discountAmount() {
        return discountAmount;
    }

    public EventConstant getEvent() {
        return event;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

}