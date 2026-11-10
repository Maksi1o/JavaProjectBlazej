package pd10.allocation;

import java.util.Map;

public record WarehouseState(Map<String, Integer> stock) {

    public WarehouseState {
        stock = Map.copyOf(stock);
    }
}
