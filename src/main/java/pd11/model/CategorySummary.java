package pd11.model;

import java.math.BigDecimal;

public record CategorySummary(
    String category,
    BigDecimal revenue,
    BigDecimal averagePrice
) {
}
