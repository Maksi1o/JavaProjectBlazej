package pd10.promotion;

import pd10.model.Order;

import java.math.BigDecimal;

public record PriceContext(
    Order order,
    BigDecimal originalTotal,
    BigDecimal currentTotal
) {
    public PriceContext withTotal(BigDecimal newTotal) {
        return new PriceContext(order, originalTotal, newTotal);
    }
}
