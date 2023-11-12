package christmas.view;

import christmas.view.validation.InputValidator;
import java.util.function.Supplier;

public class InputView<T> {

    private final InputUtil<T> inputUtil;
    private final InputValidator<String> inputValidator;

    public InputView(InputUtil<T> inputUtil, InputValidator<String> validator) {
        this.inputUtil = inputUtil;
        this.inputValidator = validator;
    }

    public InputResult<T> read() {
        System.out.println("입력하세요.");
        return repeat(inputUtil::read, inputValidator);
    }

    private InputResult<T> repeat(Supplier<T> input, InputValidator<String> validator) {
        try {
            T inputData = input.get();
            validator.validate((String) inputData);
            return new InputResult<>(inputData);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(input, validator);
        }
    }
}
