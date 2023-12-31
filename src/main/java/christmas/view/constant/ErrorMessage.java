package christmas.view.constant;

public enum ErrorMessage {

    ERROR_HEAD("[ERROR] "),
    RE_ENTER(" 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_MENU("유효하지 않은 주문입니다."),
    MENU_NOT_EXIST("유효하지 않은 주문입니다."),
    DUPLICATE_MENU("유효하지 않은 주문입니다."),
    ONLY_BEVERAGE("음료만 주문할 수 없습니다."),
    ORDER_EXCEEDED("메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다."),
    MENU_NOT_FOUND("존재하지 않는 매뉴입니다."),
    GIVEAWAY_NOT_FOUND("증정 매뉴 혜택 내역을 찾을 수 없습니다.");

    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return ERROR_HEAD.error + error + RE_ENTER.error;
    }

    public String getError(int limit) {
        return String.format(ERROR_HEAD.error + error + RE_ENTER.error, limit);
    }
}
