package pd14.exceptions;

public class MemberAlreadyExistsException extends LibraryException {
    public MemberAlreadyExistsException(String email) {
        super("Member with email: " + email + " already exists");
    }
}
