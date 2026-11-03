package pd10.promotion;

import java.util.function.Predicate;

@FunctionalInterface
public interface Promotion {

    PromotionContext apply(PromotionContext context);

    default Promotion andThen(Promotion next) {
        return context -> next.apply(this.apply(context));
    }

    default Promotion when(Predicate<PromotionContext> condition) {
        return context -> condition.test(context)
            ? this.apply(context)
            : context;
    }
}
