package christmas.view;

import christmas.model.domain.constant.Badge;
import christmas.model.domain.constant.Menu;
import christmas.model.domain.event.EventApplyResponse;
import christmas.model.domain.event.constant.EventConstant;
import christmas.view.constant.VisitDetailMessage;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class MessageGenerator {

    private static final int MONTH = 12;
    private static final int ORDER_QUANTITY_INDEX = 0;
    private static final int ORDER_PRICE_INDEX = 1;

    public String orderedMenu(EnumMap<Menu, List<Integer>> orderMenu) {
        return VisitDetailMessage.ORDER_MENU_DETAIL_HEADER.getHeader() +
                orderMenu.entrySet().stream()
                        .map(entry -> VisitDetailMessage.getMenuMessage(entry.getKey(),
                                entry.getValue().get(ORDER_QUANTITY_INDEX)))
                        .collect(Collectors.joining("\n"));
    }

    public String totalPriceBeforeDiscount(EnumMap<Menu, List<Integer>> orderMenu) {
        int totalPrice = orderMenu.values().stream().mapToInt(orderDetail -> orderDetail.get(ORDER_PRICE_INDEX)).sum();
        return generateSingleMessage(VisitDetailMessage.ORIGINAL_TOTAL_PRICE_HEADER, VisitDetailMessage.formatMoney(totalPrice));
    }

    public String giveaway(List<EventApplyResponse> eventApplyResponses) {
        StringBuilder sb = new StringBuilder();
        sb.append(VisitDetailMessage.GIVEAWAY_HEADER.getHeader());
        boolean isGiveaway = eventApplyResponses.stream()
                .anyMatch(eventApplyResponse -> eventApplyResponse.getEvent().equals(EventConstant.GIVEAWAY));
        if (isGiveaway) {
            sb.append(VisitDetailMessage.getMenuMessage(Menu.CHAMPAGNE, 1));
            return sb.toString();
        }
        sb.append(VisitDetailMessage.NONE.getMessage());
        return sb.toString();
    }

    public String discountDetail(List<EventApplyResponse> eventApplyResponses) {
        StringBuilder sb = new StringBuilder();
        sb.append(VisitDetailMessage.BENEFIT_DETAILS_HEADER.getHeader());
        if (eventApplyResponses.isEmpty()) {
            sb.append(VisitDetailMessage.NONE.getMessage());
            return sb.toString();
        }
        return VisitDetailMessage.BENEFIT_DETAILS_HEADER.getHeader() +
                eventApplyResponses.stream()
                        .map(response -> VisitDetailMessage.getDiscountMessage(response.getEvent().getName(),
                                response.discountAmount()))
                        .collect(Collectors.joining("\n"));
    }

    public String totalDiscount(List<EventApplyResponse> eventApplyResponses) {
        int totalDiscount = eventApplyResponses.stream().mapToInt(EventApplyResponse::getDiscountAmount).sum();
        return generateSingleMessage(VisitDetailMessage.TOTAL_BENEFIT_HEADER, VisitDetailMessage.formatNegativeMoney(totalDiscount));
    }

    public String totalAfterDiscount(int money) {
        return generateSingleMessage(VisitDetailMessage.DISCOUNT_TOTAL_PRICE_HEADER, VisitDetailMessage.formatMoney(money));
    }

    public String customerBadge(Badge badge) {
        return VisitDetailMessage.BADGE_HEADER.getHeader(MONTH) + badge.getName();
    }

    private String generateSingleMessage(VisitDetailMessage header, String money) {
        return header.getHeader() + money;
    }
}
