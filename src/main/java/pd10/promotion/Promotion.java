package pd10.promotion;

import java.util.function.Predicate;

@FunctionalInterface
public interface Promotion {

    PriceContext apply(PriceContext context);

    default Promotion andThen(Promotion next) {
        return context -> next.apply(this.apply(context));
    }

    default Promotion when(Predicate<PriceContext> condition) {
        return context -> condition.test(context)
            ? this.apply(context)
            : context;
    }
}
