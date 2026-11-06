package pd11.model;

import java.math.BigDecimal;

public record ProductRevenue(
    Product product,
    BigDecimal revenue
) {
}
