package pd10.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Order {
    private final String orderId;
    private final String customerId;
    private final ClientType tier;
    private final List<OrderItem> items;

    public Order (String orderId,
                  String customerId,
                  ClientType tier,
                  List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.tier = tier;
        this.items = List.copyOf(orderItems);
    }
}
