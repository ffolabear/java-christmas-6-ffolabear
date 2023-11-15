package christmas.model.domain.constant;

public enum OrderConstant {

    DECEMBER(12),
    MIN_ORDER_LIMIT(1),
    MAX_ORDER_LIMIT(20),
    EVENT_THRESHOLD(10_000);

    private final int value;

    OrderConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
