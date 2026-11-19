package pd14.service.impl;

import entity.Book;
import entity.Loan;
import entity.Member;
import exceptions.LoanLimitExceededException;
import exceptions.LoanNotFoundException;
import lombok.RequiredArgsConstructor;
import service.BookService;
import service.LoanService;
import service.MemberService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final BookService bookService;
    private final MemberService memberService;
    private final List<Loan> loans = new ArrayList<>();
    private long nextId;

    @Override
    public Loan borrow(long memberId, long bookId) {
        Member member = memberService.findById(memberId);
        if (!member.canBorrow()) {
            throw new LoanLimitExceededException("Member: " + memberId + " cannot loan more.");
        }
        Book book = bookService.findById(bookId);
        book.borrow();
        nextId++;
        Loan loan = new Loan(nextId, book, member, LocalDate.now(), LocalDate.now().plusDays(14));
        loans.add(loan);
        member.getLoans().add(loan);
        return loan;
    }

    @Override
    public void returnBook(long loanId) {
        Loan loanToReturn = loans.stream()
                .filter(loan -> loan.getId() == loanId)
                .findFirst()
                .orElseThrow(() -> new LoanNotFoundException("Loan with Id: " + loanId + " was not found."));
        loanToReturn.markReturned();
        loanToReturn.getBook().returnBack();
    }

    @Override
    public List<Loan> findOverdue() {
        return loans.stream()
                .filter(Loan::isOverdue)
                .toList();
    }
}
