package pd14.dto;

import java.time.LocalDate;

public record LoanDto(
        Long id,
        Long bookId,
        String bookTitle,
        Long memberId,
        String memberName,
        LocalDate borrowedAt,
        LocalDate dueDate,
        LocalDate returnedAt
) {
    @Override
    public String toString() {
        return String.format("Loan #%d: \"%s\" -> %s, borrowed %s, due %s, returned: %s",
                id, bookTitle, memberName, borrowedAt, dueDate,
                returnedAt == null ? "not returned" : returnedAt);
    }
}
