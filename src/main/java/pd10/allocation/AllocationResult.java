package pd10.allocation;

import pd10.model.Order;

public record AllocationResult(
    WarehouseState warehouse,
    Order fulfilledOrder,
    AllocationStatus status,
    Order originalOrder
) {
}
