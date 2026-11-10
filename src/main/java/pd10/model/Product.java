package pd10.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {
    private final String Product;
    private final int quantity;
    private final BigDecimal unitPrice;
}
