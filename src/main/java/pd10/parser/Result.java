package pd10.parser;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class Result<T> {
    private final T value;
    private final String error;

    public static <T> Result<T> success(T value) {
        return new Result<>(Objects.requireNonNull(value,
            "Value cannot be null"), null);
    }

    public static <T> Result<T> failure(String error) {
        if (error == null || error.isBlank()) {
            throw new IllegalArgumentException("Error message cannot be blank.");
        }
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return error == null;
    }
}
