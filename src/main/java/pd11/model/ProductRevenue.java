package pd11.model;

import java.math.BigDecimal;
import java.util.Objects;

public record ProductRevenue(
    Product product,
    BigDecimal revenue
) {
    public ProductRevenue {
        Objects.requireNonNull(product, "product cannot be null");
        Objects.requireNonNull(revenue, "revenue cannot be null");
    }
}
