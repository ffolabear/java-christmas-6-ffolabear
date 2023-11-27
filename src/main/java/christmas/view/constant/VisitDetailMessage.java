package christmas.view.constant;

import christmas.model.domain.constant.Menu;
import java.text.DecimalFormat;

public enum VisitDetailMessage {

    START_BRACKET("\n<"),
    END_BRACKET(">\n"),
    ORDER_MENU_DETAIL_HEADER("주문 메뉴"),
    ORIGINAL_TOTAL_PRICE_HEADER("할인 전 총주문 금액"),
    GIVEAWAY_HEADER("증정 메뉴"),
    BENEFIT_DETAILS_HEADER("혜택 내역"),
    NONE("없음"),
    TOTAL_BENEFIT_HEADER("총혜택 금액"),
    DISCOUNT_TOTAL_PRICE_HEADER("할인 후 예상 결제 금액"),
    BADGE_HEADER("%d월 이벤트 배지"),
    MENU_MESSAGE("%s %d개"),
    DISCOUNT_MESSAGE("%s: %s");

    private final String message;

    VisitDetailMessage(String message) {
        this.message = message;
    }

    public String getHeader() {
        return START_BRACKET.message + message + END_BRACKET.message;
    }

    public String getHeader(int month) {
        return START_BRACKET.message + String.format(message, month) + END_BRACKET.message;
    }

    public String getMessage() {
        return message;
    }

    public static String getDiscountMessage(String discountName, int money) {
        return String.format(DISCOUNT_MESSAGE.message, discountName, formatNegativeMoney(money));
    }

    public static String getMenuMessage(Menu menu, int quantity) {
        return String.format(MENU_MESSAGE.message, menu.getName(), quantity);
    }

    public static String formatPositiveMoney(int money) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###원");
        return decimalFormat.format(money);
    }

    public static String formatNegativeMoney(int money) {
        DecimalFormat decimalFormat = new DecimalFormat("-###,###원");
        return decimalFormat.format(money);
    }
}
