package christmas.model.domain.event.constant;

public enum EventConstant {

    WEEKDAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    CHRISTMAS("크리스마스 디데이 할인"),
    GIVEAWAY("증정 이벤트");

    private final String name;

    EventConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}