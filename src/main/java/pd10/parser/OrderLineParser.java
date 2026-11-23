package pd10.parser;

import pd10.model.ClientType;
import pd10.model.OrderLine;

import java.math.BigDecimal;
import java.util.Locale;

public class OrderLineParser {

    public static Result<OrderLine> parse(String line) {
        return ParserExecutor.execute(line, OrderLineParser::parseRaw);
    }

    private static OrderLine parseRaw(String line) throws Exception {
        if (line == null || line.isBlank()) {
            throw new IllegalArgumentException("Line cannot be empty.");
        }

        String[] parts = line.split(";", -1);

        if (parts.length != 6) {
            throw new IllegalArgumentException("Expected 6 columns got: " + parts.length);
        }

        String orderId = parts[0].trim();
        String customerId = parts[1].trim();
        String tierText = parts[2].trim();
        String product = parts[3].trim();

        if (orderId.isBlank()
            || customerId.isBlank()
            || tierText.isBlank()
            || product.isBlank()) {

            throw new IllegalArgumentException("Required field cannot be blank.");
        }

        ClientType tier = ClientType.valueOf(tierText.toUpperCase(Locale.ROOT));

        int quantity = Integer.parseInt(parts[4].trim());

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        String priceText = parts[5].trim().replace(',', '.');
        BigDecimal unitPrice = new BigDecimal(priceText);

        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        return new OrderLine(orderId,
            customerId,
            tier,
            product,
            quantity,
            unitPrice);
    }

}
