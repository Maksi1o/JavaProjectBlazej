package pd10.allocation;

import pd10.model.ClientType;
import pd10.model.Order;
import pd10.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WarehouseAllocator {

    public static AllocationResult allocate(Order order, WarehouseState warehouse) {
        if (order.tier() != ClientType.GOLD
            && !canFulfill(order, warehouse)) {
            return new AllocationResult(
                warehouse,
                null,
                AllocationStatus.REJECTED,
                order
            );
        }

        Map<String, Integer> required = order.items()
            .stream()
            .collect(Collectors.groupingBy(
                Product::getProduct,
                Collectors.summingInt(Product::getQuantity)
            ));

        Map<String, Integer> allocated = calculateAllocated(required, warehouse);

        Map<String, Integer> updatedStock = warehouse.stock().
            entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue()
                    - allocated.getOrDefault(entry.getKey(), 0)
            ));

        WarehouseState newWarehouse = new WarehouseState(updatedStock);

        List<Product> fulfilledItems = allocated.entrySet()
            .stream()
            .map(entry -> new Product(
                entry.getKey(),
                entry.getValue(),
                order.items().stream()
                    .filter(item -> item.getProduct().equals(entry.getKey()))
                    .findFirst()
                    .orElseThrow()
                    .getUnitPrice()
            ))
            .toList();

        AllocationStatus status = fulfilledItems.isEmpty()
            ? AllocationStatus.REJECTED
            : canFulfill(order, warehouse)
              ? AllocationStatus.FULL
              : AllocationStatus.PARTIAL;

        Order fulfilledOrder = Optional.of(fulfilledItems)
            .filter(items -> !items.isEmpty())
            .map(items -> new Order(
                order.orderId(),
                order.customerId(),
                order.tier(),
                items
            ))
            .orElse(null);

        return new AllocationResult(
            status == AllocationStatus.REJECTED ? warehouse : newWarehouse,
            fulfilledOrder,
            status,
            order
        );
    }

    public static boolean canFulfill(Order order, WarehouseState warehouse) {
        Map<String, Integer> required = calculateRequired(order);

        return required.entrySet()
            .stream()
            .allMatch(entry ->
                warehouse.stock()
                    .getOrDefault(entry.getKey(), 0) >= entry.getValue()
            );
    }

    private static Map<String, Integer> calculateRequired(Order order) {
        return order.items()
            .stream()
            .collect(Collectors.groupingBy(
                Product::getProduct,
                Collectors.summingInt(Product::getQuantity)
            ));
    }

    private static Map<String, Integer> calculateAllocated(
        Map<String, Integer> required,
        WarehouseState warehouse) {
        Map<String, Integer> updated = required.entrySet()
            .stream()
            .filter(entry -> entry.getValue() > 0)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    int requiredQuantity = entry.getValue();
                    int availableQuantity = warehouse.stock().getOrDefault(entry.getKey(), 0);
                    int result = Math.min(requiredQuantity, availableQuantity);

                    return result;
                }
            ));
        return updated;
    }
}
