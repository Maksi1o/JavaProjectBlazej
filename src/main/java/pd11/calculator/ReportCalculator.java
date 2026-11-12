package pd11.calculator;

import pd11.model.CategorySummary;
import pd11.model.ProductRevenue;
import pd11.model.Report;
import pd11.model.Sale;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ReportCalculator {

    private static final int TOP_PRODUCTS_LIMIT = 3;
    private static final int PRICE_SCALE = 2;
    private static final RoundingMode PRICE_ROUNDING = RoundingMode.HALF_UP;

    private ReportCalculator() {
    }

    public static Report generateReport(List<Sale> sales) {
        Objects.requireNonNull(sales, "sales cannot be null");
        return new Report(
            calculateTotalRevenue(sales),
            calculateTopProducts(sales),
            calculateCategorySummaries(sales),
            calculateBestSalesDay(sales).orElse(null)
        );
    }

    private static BigDecimal calculateTotalRevenue(List<Sale> sales) {
        return sales.stream()
            .map(Sale::revenue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static List<ProductRevenue> calculateTopProducts(List<Sale> sales) {
        return sales.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Sale::product),
                salesByProduct -> salesByProduct.entrySet().stream()
                    .map(entry -> new ProductRevenue(entry.getKey(), calculateTotalRevenue(entry.getValue())))
                    .sorted(Comparator.comparing(ProductRevenue::revenue).reversed()
                        .thenComparing(result -> result.product().productId()))
                    .limit(TOP_PRODUCTS_LIMIT)
                    .toList()
            ));
    }

    private static List<CategorySummary> calculateCategorySummaries(List<Sale> sales) {
        return sales.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(sale -> sale.product().category()),
                salesByCategory -> salesByCategory.entrySet().stream()
                    .map(entry -> createCategorySummary(entry.getKey(), entry.getValue()))
                    .sorted(Comparator.comparing(CategorySummary::revenue).reversed()
                        .thenComparing(CategorySummary::category))
                    .toList()
            ));
    }

    private static CategorySummary createCategorySummary(String category, List<Sale> categorySales) {
        return new CategorySummary(
            category,
            calculateTotalRevenue(categorySales),
            calculateAveragePrice(categorySales)
        );
    }

    private static BigDecimal calculateAveragePrice(List<Sale> sales) {
        return sales.stream()
            .map(Sale::unitPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(sales.size()), PRICE_SCALE, PRICE_ROUNDING);
    }

    private static Optional<LocalDate> calculateBestSalesDay(List<Sale> sales) {
        return sales.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(
                    Sale::date,
                    Collectors.reducing(BigDecimal.ZERO, Sale::revenue, BigDecimal::add)
                ),
                revenueByDay -> revenueByDay.entrySet().stream()
                    .sorted(Map.Entry.<LocalDate, BigDecimal>comparingByValue().reversed()
                        .thenComparing(Map.Entry::getKey))
                    .map(Map.Entry::getKey)
                    .findFirst()
            ));
    }
}
