package pd14.entity;

import lombok.Getter;
import pd14.exceptions.LoanLimitExceededException;
import pd14.validator.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Member {
    public static final int MAX_ACTIVE_LOANS = 3;

    private Long id;
    private final String name;
    private final String email;
    private final List<Loan> loans = new ArrayList<>();

    public Member(String name, String email) {
        this.name = Validation.requireNotBlank(name, "name");
        this.email = Validation.requireNotBlank(email, "email");
    }

    public void assignId(Long id) {
        if (this.id != null) {
            throw new IllegalStateException("Id already assigned.");
        }
        this.id = id;
    }

    public void addLoan(Loan loan) {
        if (!canBorrow()) {
            throw new LoanLimitExceededException(id);
        }
        loans.add(Objects.requireNonNull(loan, "Loan cannot be null"));
    }

    public List<Loan> getLoans() {
        return List.copyOf(loans);
    }

    public boolean canBorrow() {
        return loans.stream()
                .filter(Loan::isActive)
                .count() < MAX_ACTIVE_LOANS;
    }
}
