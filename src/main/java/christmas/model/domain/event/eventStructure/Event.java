package christmas.model.domain.event.eventStructure;

import christmas.model.domain.Order;
import christmas.model.domain.event.EventApplyResponse;

public interface Event {

    EventApplyResponse applyEvent();

    boolean isApplicable(Order order);

}
