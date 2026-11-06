package pd11.result;

public record OperationResult<T>(
    boolean success,
    T value,
    String message
) {
    public static <T> OperationResult<T> success(T value) {
        return new OperationResult<>(true, value, null);
    }

    public static <T> OperationResult<T> failure(String message) {
        return new OperationResult<>(false, null, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
