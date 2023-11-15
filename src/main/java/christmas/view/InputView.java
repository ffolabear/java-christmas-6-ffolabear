package christmas.view;

import christmas.view.validation.InputValidator;
import java.util.function.Supplier;

public class InputView<T, R> {

    private final InputUtil<T> inputUtil;
    private final InputValidator<T, R> inputValidator;

    public InputView(InputUtil<T> inputUtil, InputValidator<T, R> validator) {
        this.inputUtil = inputUtil;
        this.inputValidator = validator;
    }

    public InputResult<R> read() {
        return repeat(inputUtil::read, inputValidator);
    }

    private InputResult<R> repeat(Supplier<T> input, InputValidator<T, R> validator) {
        try {
            T inputData = input.get();
            R validationResult = validator.validate(inputData);
            return new InputResult<>(validationResult);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(input, validator);
        }
    }
}
