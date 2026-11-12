package pd11.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public record Sale(
    LocalDate date,
    Product product,
    int quantity,
    BigDecimal unitPrice
) {
    public Sale {
        Objects.requireNonNull(date, "date cannot be null");
        Objects.requireNonNull(product, "product cannot be null");
        Objects.requireNonNull(unitPrice, "unitPrice cannot be null");

        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive, got: " + quantity);
        }
        if (unitPrice.signum() < 0) {
            throw new IllegalArgumentException("unitPrice cannot be negative, got: " + unitPrice);
        }
    }

    public BigDecimal revenue() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
