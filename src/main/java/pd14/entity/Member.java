package pd14.entity;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public class Member {
    private final long id;
    private final String name;
    private final String email;
    private final List<Loan> loans = new ArrayList<>();

    public Member(long id, String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public boolean canBorrow() {
        return loans.stream()
                .filter(Loan::isActive)
                .count() < 3;
    }
}
