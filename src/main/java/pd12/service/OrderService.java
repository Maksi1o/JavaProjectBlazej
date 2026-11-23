package pd12.service;

import pd12.model.Order;
import pd12.model.OrderResult;
import pd12.model.OrderStatus;

import java.util.Objects;

public class OrderService {
    private static final int INITIAL_QUANTITY = 10;
    private int availableQuantity = INITIAL_QUANTITY;

    public synchronized OrderResult processOrder(Order order) {
        Objects.requireNonNull(order, "Order cannot be null.");

        if (order.requestedQuantity() > availableQuantity) {
            return new OrderResult(OrderStatus.REJECTED, order);
        }
        availableQuantity -= order.requestedQuantity();

        return new OrderResult(OrderStatus.ACCEPTED, order);
    }

    public synchronized int getAvailableQuantity() {
        return availableQuantity;
    }

    public synchronized int getSoldCount() {
        return INITIAL_QUANTITY - getAvailableQuantity();
    }
}
