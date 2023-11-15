package christmas.view.constant;

public enum InputConstant {

    ORDER_DELIMITER(","),
    MENU_DELIMITER("-");

    private final String value;

    InputConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
