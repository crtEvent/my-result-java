import java.util.function.Consumer;

public class MyResult<T> {
    private final T value;
    private final Exception exception;

    private MyResult(T value, Exception exception) {
        this.value = value;
        this.exception = exception;
    }

    public static <T> MyResult<T> success(T value) {
        return new MyResult<>(value, null);
    }

    public static <T> MyResult<T> failure(Exception exception) {
        return new MyResult<>(null, exception);
    }

    public boolean isSuccess() {
        return exception == null;
    }

    public boolean isFailure() {
        return exception != null;
    }

    public T getOrNull() {
        return value;
    }

    public Exception exceptionOrNull() {
        return exception;
    }

    public MyResult<T> onSuccess(Consumer<? super T> action) {
        if (isSuccess()) {
            action.accept(value);
        }
        return this;
    }

    public MyResult<T> onFailure(Consumer<? super Exception> action) {
        if (isFailure()) {
            action.accept(exception);
        }
        return this;
    }
}
