package pd11.parser;

import pd11.result.OperationResult;
import pd11.model.Product;
import pd11.model.Sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SaleParser {

    public OperationResult<Sale> parse(String line) {
        String[] fields = line.split(",", -1);

        if (fields.length != 6) {
            return OperationResult.failure("Expected 6 columns, got: " + fields.length);
        }

        try {
            LocalDate date = LocalDate.parse(fields[0].strip());
            String productId = fields[1].strip();
            String productName = fields[2].strip();
            String category = fields[3].strip();
            int quantity = Integer.parseInt(fields[4].strip());
            BigDecimal unitPrice = new BigDecimal(fields[5].strip());

            if (productId.isBlank() || productName.isBlank() || category.isBlank()) {
                return OperationResult.failure("Product fields cannot be empty");
            }

            if (quantity <= 0 || unitPrice.signum() < 0) {
                return OperationResult.failure("Invalid quantity or unit price");
            }

            Product product = new Product(productId, productName, category);
            Sale sale = new Sale(date, product, quantity, unitPrice);

            return OperationResult.success(sale);
        } catch (NumberFormatException | DateTimeParseException e) {
            return OperationResult.failure(e.getMessage());
        }
    }
}
