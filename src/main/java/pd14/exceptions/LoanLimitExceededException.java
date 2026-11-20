package pd14.exceptions;

public class LoanLimitExceededException extends LibraryException {
    public LoanLimitExceededException(Long memberId) {
        super("Member with id " + memberId + " has reached the loan limit");
    }
}
