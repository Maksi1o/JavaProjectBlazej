package pd11.model;

import java.util.Objects;

public record Product(
    String productId,
    String productName,
    String category
) {
    public Product {
        requireNotBlank(productId, "productId");
        requireNotBlank(productName, "productName");
        requireNotBlank(category, "category");
    }

    private static void requireNotBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
    }
}
