package pd14.exceptions;

public class LoanNotFoundException extends LibraryException {
    public LoanNotFoundException(Long id) {
        super("Loan with id " + id + " was not found");
    }
}
