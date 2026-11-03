package pd10.parser;

import pd10.model.Order;
import pd10.model.Product;
import pd10.model.OrderLine;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderGrouper {

    public static List<Order> group(List<OrderLine> validLines) {

        Map<String, List<OrderLine>> grouped = validLines.stream()
            .collect(Collectors.groupingBy(OrderLine::getOrderId));

        return grouped.values().stream()
            .map(OrderGrouper::createOrder)
            .toList();
    }

    private static Order createOrder(List<OrderLine> lines) {

        OrderLine first = lines.get(0);

        boolean consistent = lines.stream()
            .allMatch(line ->
                Objects.equals(line.getCustomerId(), first.getCustomerId())
                    && line.getTier() == first.getTier()
            );

        if (!consistent) {
            throw new IllegalArgumentException(
                "Inconsistent customer or tier for order: "
                    + first.getOrderId()
            );
        }

        List<Product> items = lines.stream()
            .map(line -> new Product(
                line.getProduct(),
                line.getQuantity(),
                line.getUnitPrice()
            ))
            .toList();

        return new Order(
            first.getOrderId(),
            first.getCustomerId(),
            first.getTier(),
            items
        );
    }
}

