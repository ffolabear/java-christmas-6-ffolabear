package christmas.view;

public class InputResult<T> {

    private final T data;

    public InputResult(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
