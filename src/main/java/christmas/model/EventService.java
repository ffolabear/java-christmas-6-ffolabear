package christmas.model;

import christmas.model.domain.Customer;
import christmas.model.domain.Order;
import christmas.model.domain.constant.Menu;
import christmas.model.domain.event.EventApplyResponse;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.List;

public class EventService {

    private Customer customer;
    private List<EventApplyResponse> eventApplyResponses;
    private final OutputView outputView = new OutputView();

    public void inputCustomerDetail() {
        this.customer = new Customer();
        customer.inputDetail();
    }

    public void checkOrder() {
        Order order = customer.getOrder();
        EventChecker eventChecker = new EventChecker();
        this.eventApplyResponses = eventChecker.checkOrder(order);
        customer.setBadge(eventChecker.checkBadge(eventApplyResponses));
    }

    public void printAppliedEvent() {
        printOrderMenu(customer.getOrder().getOrderMenu());
        printTotalBeforeDiscount(customer.getOrder().getOrderMenu());
        printGiveaway();
        printDiscountDetail();
        printTotalDiscount();
        printTotalAfterDiscount();
        printBadge();
    }

    private void printOrderMenu(EnumMap<Menu, List<Integer>> orderMenu) {
        outputView.printOrderMenu(orderMenu);
    }

    private void printTotalBeforeDiscount(EnumMap<Menu, List<Integer>> orderMenu) {
        outputView.printTotalPriceBeforeDiscount(orderMenu);
    }

    private void printGiveaway() {
        outputView.printGiveaway(eventApplyResponses);
    }

    private void printDiscountDetail() {
        outputView.printDiscountDetail(eventApplyResponses);
    }

    private void printTotalDiscount() {
        outputView.printTotalDiscount(eventApplyResponses);
    }

    private void printTotalAfterDiscount() {
        outputView.printTotalAfterDiscount(customer.getOrder().getTotalPrice());
    }

    private void printBadge() {
        outputView.printCustomerBadge(customer.getBadge());
    }
}
