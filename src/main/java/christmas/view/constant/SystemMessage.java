package christmas.view.constant;

public enum SystemMessage {

    WELCOME("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다."),
    INPUT_DATE("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_HEADER("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

    private final String message;

    SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(int month) {
        return String.format(message, month);
    }

    public String getMessage(int month, int date) {
        return String.format(message, month, date);
    }
}
