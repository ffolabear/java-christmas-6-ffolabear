package christmas.view.constant;

public enum EventReceipt {

    START_BRACKET("<"),
    END_BRACKET(">"),
    ORDER_MENU_DETAIL_HEADER("주문 메뉴"),
    ORIGINAL_TOTAL_PRICE_HEADER("할인 전 총주문 금액"),
    GIVE_AWAY_HEADER("증정 메뉴"),
    BENEFIT_DETAILS_HEADER("혜택 내역"),
    DISCOUNT_WEEKDAYS_("평일 할인: "),
    DISCOUNT_WEEKEND(" 할인: "),
    DISCOUNT_SPECIAL("주말 할인: "),
    DISCOUNT_CHRISTMAS("크리스마스 디데이 할인: "),
    GIVE_AWAY("증정 이벤트: "),
    TOTAL_BENEFIT_HEADER("총혜택 금액"),
    DISCOUNT__TOTAL_PRICE_HEADER("할인 후 예상 결제 금액"),
    BADGE_HEADER("%d월 이벤트 배지");

    private final String message;

    EventReceipt(String message) {
        this.message = message;
    }

    public String getHeader() {
        return START_BRACKET.message + message + END_BRACKET.message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int month) {
        return String.format(message, month);
    }
}
