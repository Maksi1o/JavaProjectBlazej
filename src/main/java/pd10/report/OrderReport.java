package pd10.report;

import pd10.model.ClientType;
import pd10.model.Order;

import java.math.BigDecimal;
import java.util.List;

public record OrderReport(
    ClientType tier,
    BigDecimal revenue,
    long rejectedCount,
    List<Order> topOrders
) {
    public OrderReport {
        topOrders = List.copyOf(topOrders);
    }
}
