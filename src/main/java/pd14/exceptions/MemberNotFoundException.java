package pd14.exceptions;

public class MemberNotFoundException extends LibraryException {
    public MemberNotFoundException(Long id) {
        super("Member with id " + id + " was not found");
    }
}
