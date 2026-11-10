package pd10.model;

import java.util.List;


public record Order(String orderId,
                    String customerId,
                    ClientType tier,
                    List<Product> items) {


    public Order(String orderId,
                 String customerId,
                 ClientType tier,
                 List<Product> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.tier = tier;
        this.items = List.copyOf(items);
    }
}
