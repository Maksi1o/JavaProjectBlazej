package pd14.service;

import entity.Loan;

import java.util.List;

public interface LoanService {
    Loan borrow(long memberId, long bookId);

    void returnBook(long loanId);

    List<Loan> findOverdue();
}
