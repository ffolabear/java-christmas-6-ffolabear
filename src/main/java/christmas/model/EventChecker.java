package christmas.model;

import christmas.model.domain.Order;
import christmas.model.domain.constant.Badge;
import christmas.model.domain.event.ChristmasEvent;
import christmas.model.domain.event.EventApplyResponse;
import christmas.model.domain.event.GiveawayEvent;
import christmas.model.domain.event.SpecialEvent;
import christmas.model.domain.event.WeekDayEvent;
import christmas.model.domain.event.WeekendEvent;
import christmas.model.domain.event.eventStructure.Event;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EventChecker {

    private final List<Event> discountEvents;
    private static final int EVENT_THRESHOLD = 10_000;

    public EventChecker() {
        this.discountEvents = Arrays.asList(
                new WeekDayEvent(), new WeekendEvent(), new GiveawayEvent(), new ChristmasEvent(), new SpecialEvent());
    }

    public List<EventApplyResponse> checkOrder(Order order) {
        if (order.getTotalPrice() < EVENT_THRESHOLD) {
            return new ArrayList<>();
        }
        return discountEvents.stream()
                .filter(event -> event.isApplicable(order))
                .map(Event::applyEvent)
                .sorted(Comparator.comparingInt(response -> response.event().ordinal()))
                .toList();
    }

    public Badge checkBadge(List<EventApplyResponse> eventResponses) {
        int totalDiscount = eventResponses.stream().mapToInt(EventApplyResponse::getDiscountAmount).sum();
        return Badge.findHighestBadge(totalDiscount);
    }
}
