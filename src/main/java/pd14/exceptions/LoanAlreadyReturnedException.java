package pd14.exceptions;

public class LoanAlreadyReturnedException extends LibraryException {
    public LoanAlreadyReturnedException(Long loanId) {
        super("Loan with id " + loanId + " was already returned");
    }
}
