package pd10.allocation;

import pd10.model.ClientType;
import pd10.model.Order;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class OrderAllocationProcessor {

    public static List<Order> sortOrders(List<Order> orders) {
        return orders.stream()
            .sorted(
                Comparator.comparing((Order order) -> order.tier() == ClientType.GOLD)
                    .reversed()
                    .thenComparing(
                        OrderAllocationProcessor::calculateOrderValue,
                        Comparator.reverseOrder()
                    )
            )
            .toList();
    }

    private static BigDecimal calculateOrderValue(Order order) {
        return order.items().stream()
            .map(item -> item.getUnitPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static AllocationBatch accumulate(
        AllocationBatch batch,
        Order order
    ) {
        AllocationResult result = WarehouseAllocator.allocate(
            order,
            batch.warehouse()
        );

        List<AllocationResult> updatedResults = Stream.concat(
            batch.results().stream(),
            Stream.of(result)
        ).toList();

        return new AllocationBatch(
            result.warehouse(),
            updatedResults
        );
    }

    public static AllocationBatch process(
        List<Order> orders,
        WarehouseState initialWarehouse
    ) {
        AllocationBatch initialBatch = new AllocationBatch(
            initialWarehouse,
            Collections.emptyList()
        );

        return sortOrders(orders).stream()
            .map(order -> (UnaryOperator<AllocationBatch>)
                batch -> accumulate(batch, order))
            .reduce(
                UnaryOperator.identity(),
                (first, second) -> batch ->
                    second.apply(first.apply(batch))
            )
            .apply(initialBatch);
    }
}
