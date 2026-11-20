package pd14.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd14.dto.LoanDto;
import pd14.entity.Loan;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoanMapper {
    public static LoanDto toDto(Loan loan) {
        Objects.requireNonNull(loan, "Loan cannot be null");
        return new LoanDto(
                loan.getId(),
                loan.getBook().getId(),
                loan.getBook().getTitle(),
                loan.getMember().getId(),
                loan.getMember().getName(),
                loan.getBorrowedAt(),
                loan.getDueDate(),
                loan.getReturnedAt()
        );
    }
}
