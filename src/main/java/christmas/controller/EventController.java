package christmas.controller;

import christmas.model.EventService;

public class EventController {

    private final EventService eventService;

    public EventController() {
        eventService = new EventService();
    }

    public void startService() {
        eventService.inputCustomerDetail();
        checkCustomerVisitDetail();
        printCustomerVisitDetail();
    }

    public void checkCustomerVisitDetail() {
        eventService.checkOrder();
    }

    public void printCustomerVisitDetail() {
        eventService.printAppliedEvent();
    }
}
