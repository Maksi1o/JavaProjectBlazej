package pd14.entity;

import lombok.Getter;
import pd14.exceptions.LoanAlreadyReturnedException;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Loan {
    public static final int LOAN_PERIOD_DAYS = 7;

    private Long id;
    private final Book book;
    private final Member member;
    private final LocalDate borrowedAt;
    private final LocalDate dueDate;
    private LocalDate returnedAt;

    public Loan(Book book, Member member, LocalDate borrowedAt) {
        this.book = Objects.requireNonNull(book, "Book cannot be null");
        this.member = Objects.requireNonNull(member, "Member cannot be null");
        this.borrowedAt = Objects.requireNonNull(borrowedAt, "BorrowedAt cannot be null");
        this.dueDate = borrowedAt.plusDays(LOAN_PERIOD_DAYS);
    }

    public boolean isActive() {
        return returnedAt == null;
    }

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now()) && isActive();
    }

    public void markReturned() {
        if (!isActive()) {
            throw new LoanAlreadyReturnedException(id);
        }
        returnedAt = LocalDate.now();
    }

    public void assignId(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("Id already assigned.");
        }
        this.id = id;
    }
}
