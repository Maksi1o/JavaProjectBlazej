package pd11.parser;

import pd11.result.OperationResult;
import pd11.model.Product;
import pd11.model.Sale;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SaleParser {

    private static final String SEPARATOR = ",";
    private static final int EXPECTED_COLUMNS = 6;


    public OperationResult<Sale> parse(String line) {
        String[] fields = line.split(SEPARATOR, -1);

        if (fields.length != EXPECTED_COLUMNS) {
            return failure(line, "expected " + EXPECTED_COLUMNS + " columns, got " + fields.length);
        }

        try {
            LocalDate date = LocalDate.parse(fields[0].strip());
            String productId = fields[1].strip();
            String productName = fields[2].strip();
            String category = fields[3].strip();
            int quantity = Integer.parseInt(fields[4].strip());
            BigDecimal unitPrice = new BigDecimal(fields[5].strip());

            Product product = new Product(productId, productName, category);
            Sale sale = new Sale(date, product, quantity, unitPrice);

            return OperationResult.success(sale);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return failure(line, e.getMessage());
        }
    }

    private OperationResult<Sale> failure(String line, String reason) {
        return OperationResult.failure("Skipping line [" + line + "]: " + reason);
    }
}