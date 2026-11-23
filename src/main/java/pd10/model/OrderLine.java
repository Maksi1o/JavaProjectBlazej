package pd10.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderLine {
    private final String orderId;
    private final String customerId;
    private final ClientType tier;
    private final String product;
    private final int quantity;
    private final BigDecimal unitPrice;
}
