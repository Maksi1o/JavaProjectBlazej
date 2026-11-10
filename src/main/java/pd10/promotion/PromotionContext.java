package pd10.promotion;

import pd10.model.Order;

import java.math.BigDecimal;

public record PromotionContext(
    Order order,
    BigDecimal originalTotal,
    BigDecimal currentTotal
) {
    public PromotionContext withTotal(BigDecimal newTotal) {
        return new PromotionContext(order, originalTotal, newTotal);
    }
}
