package christmas.model.domain.event;

import christmas.model.domain.event.constant.EventConstant;

public record EventApplyResponse(EventConstant event, int discountAmount) {

}