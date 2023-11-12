package christmas.view.validation;

import christmas.view.constant.ErrorMessage;

public class DateValidator implements InputValidator<String> {

    private final int START_DATE = 1;
    private final int END_DATE = 31;

    @Override
    public void validate(String input) {

    }

    private int isDigit(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getError());
        }
    }

    private void isValidRange(int input) {
        if (input < START_DATE || END_DATE < input) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getError());
        }
    }

}
