package pd11.result;

import java.util.Objects;

public record OperationResult<T>(
    boolean successful,
    T value,
    String message
) {
    public OperationResult {
        if (successful) {
            Objects.requireNonNull(value, "successful result must have a value");
        } else {
            Objects.requireNonNull(message, "failed result must have a message");
        }
    }

    public static <T> OperationResult<T> success(T value) {
        return new OperationResult<>(true, value, null);
    }

    public static <T> OperationResult<T> failure(String message) {
        return new OperationResult<>(false, null, message);
    }
}