package pd11.reader;

import pd11.model.Sale;

import java.util.List;
import java.util.Objects;

public record SaleReadResult(
    List<Sale> sales,
    List<String> errors
) {
    public SaleReadResult {
        sales = List.copyOf(Objects.requireNonNull(sales, "sales cannot be null"));
        errors = List.copyOf(Objects.requireNonNull(errors, "errors cannot be null"));
    }

    public int skippedCount() {
        return errors.size();
    }
}
