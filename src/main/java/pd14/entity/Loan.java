package pd14.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
@ToString
public class Loan {
    private final long id;
    private final Book book;
    @ToString.Exclude
    private final Member member;
    private final LocalDate borrowedAt;
    private final LocalDate dueDate;
    private LocalDate returnedAt;

    public boolean isActive() {
        return returnedAt == null;
    }

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now()) && isActive();
    }

    public void markReturned() {
        returnedAt = LocalDate.now();
    }

}
