package pd11.calculator;

import pd11.model.CategorySummary;
import pd11.model.Product;
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

public class ReportCalculator {

    private static final int TOP_PRODUCTS_LIMIT = 3;
    private static final int PRICE_SCALE = 2;
    private static final RoundingMode PRICE_ROUNDING = RoundingMode.HALF_UP;

    public Report generateReport(List<Sale> sales) {
        Objects.requireNonNull(sales, "sales cannot be null");

        return new Report(
            calculateTotalRevenue(sales),
            calculateTopProducts(sales),
            calculateCategorySummaries(sales),
            calculateBestSalesDay(sales).orElse(null)
        );
    }

    private BigDecimal calculateTotalRevenue(List<Sale> sales) {
        return sales.stream()
            .map(Sale::revenue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ProductRevenue> calculateTopProducts(List<Sale> sales) {
        Map<Product, List<Sale>> salesByProduct = sales.stream()
            .collect(Collectors.groupingBy(Sale::product));

        return salesByProduct.entrySet().stream()
            .map(entry -> new ProductRevenue(
                entry.getKey(),
                calculateTotalRevenue(entry.getValue())
            ))
            .sorted(Comparator.comparing(ProductRevenue::revenue).reversed()
                .thenComparing(result -> result.product().productId()))
            .limit(TOP_PRODUCTS_LIMIT)
            .toList();
    }

    private List<CategorySummary> calculateCategorySummaries(List<Sale> sales) {
        Map<String, List<Sale>> salesByCategory = sales.stream()
            .collect(Collectors.groupingBy(sale -> sale.product().category()));

        return salesByCategory.entrySet().stream()
            .map(entry -> createCategorySummary(entry.getKey(), entry.getValue()))
            .sorted(Comparator.comparing(CategorySummary::revenue).reversed()
                .thenComparing(CategorySummary::category))
            .toList();
    }

    private CategorySummary createCategorySummary(String category, List<Sale> categorySales) {
        BigDecimal revenue = calculateTotalRevenue(categorySales);

        BigDecimal totalPrices = categorySales.stream()
            .map(Sale::unitPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal averagePrice = totalPrices.divide(
            BigDecimal.valueOf(categorySales.size()),
            PRICE_SCALE,
            PRICE_ROUNDING
        );

        return new CategorySummary(category, revenue, averagePrice);
    }

    private Optional<LocalDate> calculateBestSalesDay(List<Sale> sales) {
        Map<LocalDate, BigDecimal> revenueByDay = sales.stream()
            .collect(Collectors.groupingBy(
                Sale::date,
                Collectors.reducing(BigDecimal.ZERO, Sale::revenue, BigDecimal::add)
            ));

        return revenueByDay.entrySet().stream()
            .sorted(Map.Entry.<LocalDate, BigDecimal>comparingByValue().reversed()
                .thenComparing(Map.Entry::getKey))
            .map(Map.Entry::getKey)
            .findFirst();
    }
}