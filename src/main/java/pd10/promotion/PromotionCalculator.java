package pd10.promotion;

import pd10.model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PromotionCalculator {

    private PromotionCalculator() {
    }

    private static final Promotion PIPELINE =
        Promotions.MILK_THREE_FOR_TWO
            .andThen(Promotions.GOLD_DISCOUNT)
            .andThen(Promotions.ORDER_DISCOUNT)
            .andThen(Promotions.DISCOUNT_CAP);

    public static BigDecimal calculate(Order order) {

        BigDecimal originalTotal = order.items()
            .stream()
            .map(item -> item.getUnitPrice().multiply(
                BigDecimal.valueOf(item.getQuantity())
            ))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        PromotionContext context = new PromotionContext(
            order,
            originalTotal,
            originalTotal
        );

        return PIPELINE.apply(context)
            .currentTotal()
            .setScale(2, RoundingMode.HALF_UP);
    }
}