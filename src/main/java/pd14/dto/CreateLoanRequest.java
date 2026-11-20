package pd14.dto;

public record CreateLoanRequest(
        Long memberId,
        Long bookId
) {
}
