package christmas.view;

import christmas.view.validation.InputValidator;
import java.util.function.Supplier;

public class InputView {

    private final InputUtil inputUtil;
    private final InputValidator<String> inputValidator;

    public InputView(InputUtil inputUtil, InputValidator<String> validator) {
        this.inputUtil = inputUtil;
        this.inputValidator = validator;
    }

    public String readDate() {
        System.out.println("입력하세요.");
        return repeat(inputUtil::read, inputValidator);
    }

    private <T> T repeat(Supplier<T> input, InputValidator<String> validator) {
        try {
            T date = input.get();
            validator.validate((String) date);
            return date;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(input, validator);
        }
    }
}
