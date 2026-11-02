package pd10.parser;

@FunctionalInterface
public interface ThrowingFunction<T, R> {

    R apply(T input) throws Exception;
}
