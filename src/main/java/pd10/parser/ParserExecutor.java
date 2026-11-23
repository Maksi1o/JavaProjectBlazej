package pd10.parser;

public class ParserExecutor {

    public static <T, R> Result<R> execute(T input, ThrowingFunction<T, R> function) {
        try {
            R value = function.apply(input);
            return Result.success(value);

        } catch (Exception e) {
            String message = e.getMessage();

            if (message == null || message.isBlank()) {
                message = "Unknown parsing error";
            }
            return Result.failure(message);
        }
    }
}
