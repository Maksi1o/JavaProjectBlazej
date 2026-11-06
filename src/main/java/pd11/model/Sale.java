package pd11.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Sale(
    LocalDate date,
    Product product,
    int quantity,
    BigDecimal unitPrice
) {
    public BigDecimal revenue() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
