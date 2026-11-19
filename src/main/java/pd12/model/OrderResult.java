package pd12.model;

public record OrderResult(
    OrderStatus orderStatus,
    Order order
) {
    @Override
    public String toString() {
        return String.format("%-10s | %2d szt. | %s",
            order().clientName(),
            order().requestedQuantity(),
            orderStatus());
    }
}
