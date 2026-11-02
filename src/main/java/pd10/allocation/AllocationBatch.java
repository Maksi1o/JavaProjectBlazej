package pd10.allocation;

import java.util.List;

public record AllocationBatch(
    WarehouseState warehouse,
    List<AllocationResult> results
) {

    public AllocationBatch {
        results = List.copyOf(results);
    }
}
