package pd7.model;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class User {
    private final String name;
    private final TransportType preferredTransport;
    private final BigDecimal budget;

    public User(String name, TransportType preferredTransport, BigDecimal budget) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name cannot be null or blank");
        }

        if (budget != null && budget.signum() < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.name = name.trim();
        this.preferredTransport = preferredTransport;
        this.budget = budget;
    }
}
