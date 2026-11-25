package pd15.exception;

public class InvalidUserIdException extends RuntimeException {

    public InvalidUserIdException(Long userId) {
        super("User id must be positive, got: " + userId);
    }
}
