package pd12.model;

public record Order(
    String clientName,
    int requestedQuantity
) {

    public Order {
        if (clientName == null || clientName.isBlank()) {
            throw new IllegalArgumentException("Client name cannot be blank.");
        }
        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
    }
}
