package pd11.calculator;

import pd11.model.CategorySummary;
import pd11.model.Product;
import pd11.model.ProductRevenue;
import pd11.model.Sale;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SaleCalculator {

    public BigDecimal calculateTotalRevenue(List<Sale> sales) {
        return sales.stream()
            .map(Sale::revenue)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<ProductRevenue> calculateTop3Products(List<Sale> sales) {
        Map<Product, List<Sale>> salesByProduct = sales.stream()
            .collect(Collectors.groupingBy(Sale::product));

        return salesByProduct.entrySet().stream()
            .map(entry -> new ProductRevenue(
                entry.getKey(),
                calculateTotalRevenue(entry.getValue())
            ))
            .sorted(Comparator.comparing(ProductRevenue::revenue).reversed()
                .thenComparing(result -> result.product().productId()))
            .limit(3)
            .toList();
    }

    public List<CategorySummary> calculateCategorySummaries(List<Sale> sales) {
        Map<String, List<Sale>> salesByCategory = sales.stream()
            .collect(Collectors.groupingBy(sale -> sale.product().category()));

        return salesByCategory.entrySet().stream()
            .map(entry -> {
                List<Sale> categorySales = entry.getValue();
                BigDecimal revenue = calculateTotalRevenue(categorySales);

                BigDecimal totalPrices = categorySales.stream()
                    .map(Sale::unitPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal averagePrice = totalPrices.divide(
                    BigDecimal.valueOf(categorySales.size()),
                    2,
                    RoundingMode.HALF_UP
                );

                return new CategorySummary(entry.getKey(), revenue, averagePrice);
            })
            .sorted(Comparator.comparing(CategorySummary::revenue).reversed()
                .thenComparing(CategorySummary::category))
            .toList();
    }

    public Optional<LocalDate> calculateBestSalesDay(List<Sale> sales) {
        Map<LocalDate, BigDecimal> revenueByDay = sales.stream()
            .collect(Collectors.groupingBy(
                Sale::date,
                Collectors.reducing(
                    BigDecimal.ZERO,
                    Sale::revenue,
                    BigDecimal::add
                )
            ));

        return revenueByDay.entrySet().stream()
            .sorted(Map.Entry.<LocalDate, BigDecimal>comparingByValue().reversed()
                .thenComparing(Map.Entry::getKey))
            .map(Map.Entry::getKey)
            .findFirst();
    }
}
