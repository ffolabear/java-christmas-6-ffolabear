package christmas.view;

import christmas.model.domain.constant.Badge;
import christmas.model.domain.constant.Menu;
import christmas.model.domain.event.EventApplyResponse;
import christmas.view.constant.SystemMessage;
import java.util.EnumMap;
import java.util.List;

public class OutputView {

    private final MessageGenerator messageGenerator = new MessageGenerator();

    public void printSystemMessage(SystemMessage systemMessage) {
        System.out.println(systemMessage.getMessage());
    }

    public void printSystemMessage(SystemMessage systemMessage, int month) {
        System.out.println(systemMessage.getMessage(month));
    }

    public void printEventHeader(int visitDate) {
        System.out.println(messageGenerator.eventHeader(visitDate));
    }

    public void printOrderMenu(EnumMap<Menu, List<Integer>> orderMenu) {
        System.out.println(messageGenerator.orderedMenu(orderMenu));
    }

    public void printTotalPriceBeforeDiscount(EnumMap<Menu, List<Integer>> orderMenu) {
        System.out.println(messageGenerator.totalPriceBeforeDiscount(orderMenu));
    }

    public void printGiveaway(List<EventApplyResponse> eventApplyResponses) {
        System.out.println(messageGenerator.giveaway(eventApplyResponses));
    }

    public void printDiscountDetail(List<EventApplyResponse> eventApplyResponses) {
        System.out.println(messageGenerator.discountDetail(eventApplyResponses));
    }

    public void printTotalDiscount(List<EventApplyResponse> eventApplyResponses) {
        System.out.println(messageGenerator.totalDiscount(eventApplyResponses));
    }

    public void printTotalAfterDiscount(int money) {
        System.out.println(messageGenerator.totalAfterDiscount(money));
    }

    public void printCustomerBadge(Badge badge) {
        System.out.println(messageGenerator.customerBadge(badge));
    }
}
