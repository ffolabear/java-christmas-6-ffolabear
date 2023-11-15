package christmas.view.validation;

import christmas.view.Mapper;
import christmas.view.constant.ErrorMessage;
import java.time.LocalDate;

public class DateValidator implements InputValidator<String, LocalDate> {

    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    @Override
    public LocalDate validate(String input) {
        int inputAsInt = isDigit(input);
        isValidRange(inputAsInt);
        return Mapper.toLocalDate(inputAsInt);
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