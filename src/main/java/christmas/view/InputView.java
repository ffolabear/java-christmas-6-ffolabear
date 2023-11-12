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

    public InputResult read() {
        System.out.println("입력하세요.");
        return repeat(inputUtil::read, inputValidator);
    }

    private InputResult repeat(Supplier<String> input, InputValidator<String> validator) {
        try {
            String inputData = input.get();
            validator.validate(inputData);
            return new InputResult(inputData);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(input, validator);
        }
    }
}
