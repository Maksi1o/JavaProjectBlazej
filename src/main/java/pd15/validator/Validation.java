package pd15.validator;

import pd15.exception.InvalidUserIdException;

public final class Validation {

    private Validation() {
    }

    public static void requirePositiveId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new InvalidUserIdException(userId);
        }
    }
}
