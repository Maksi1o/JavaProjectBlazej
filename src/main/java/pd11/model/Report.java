package pd11.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record Report(
    BigDecimal totalRevenue,
    List<ProductRevenue> top3Products,
    List<CategorySummary> categories,
    LocalDate bestSalesDay
) {
}
