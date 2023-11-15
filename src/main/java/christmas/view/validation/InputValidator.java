package christmas.view.validation;

public interface InputValidator<T, R> {

    R validate(T input);
}