package pd11.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public record Report(
    BigDecimal totalRevenue,
    List<ProductRevenue> topProducts,
    List<CategorySummary> categories,
    LocalDate bestSalesDay
) {
    public Report {
        Objects.requireNonNull(totalRevenue, "totalRevenue cannot be null");
        topProducts = List.copyOf(Objects.requireNonNull(topProducts, "topProducts cannot be null"));
        categories = List.copyOf(Objects.requireNonNull(categories, "categories cannot be null"));
    }
}
