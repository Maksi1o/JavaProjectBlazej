package pd11.model;

import java.math.BigDecimal;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;

public record CategorySummary(
    String category,
    BigDecimal revenue,
    BigDecimal averagePrice
) {
    public CategorySummary {
        notBlank(category, "category cannot be null");
        Objects.requireNonNull(revenue, "revenue cannot be null");
        Objects.requireNonNull(averagePrice, "averagePrice cannot be null");
    }
}
