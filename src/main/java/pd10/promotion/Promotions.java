package pd10.promotion;

import pd10.model.ClientType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Promotions {

    private Promotions() {
    }

    public static final Promotion MILK_THREE_FOR_TWO =
        context -> {
            BigDecimal discount = context.order()
                .getItems()
                .stream()
                .filter(item -> item.getProduct().equalsIgnoreCase("MILK"))
                .map(item -> item.getUnitPrice().multiply(
                    BigDecimal.valueOf(item.getQuantity() / 3)
                ))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            return context.withTotal(
                context.currentTotal().subtract(discount)
            );
        };

    public static final Promotion GOLD_DISCOUNT =
        ((Promotion) context -> context.withTotal(
            context.currentTotal().multiply(
                new BigDecimal("0.90")
            )
        )).when(context ->
            context.order().getTier() == ClientType.GOLD
        );

    public static final Promotion ORDER_DISCOUNT =
        ((Promotion) context -> context.withTotal(
            context.currentTotal().subtract(
                new BigDecimal("5.00")
            )
        )).when(context ->
            context.originalTotal()
                .compareTo(new BigDecimal("50.00")) > 0
        );

    public static final Promotion DISCOUNT_CAP =
        context -> {
            BigDecimal minimumTotal = context.originalTotal()
                .multiply(new BigDecimal("0.70"));

            BigDecimal finalTotal = context.currentTotal()
                .max(minimumTotal)
                .max(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);

            return context.withTotal(finalTotal);
        };
}