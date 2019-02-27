package com.urban.utils;

import static com.urban.utils.StreamUtils.safeStream;
import static java.util.Optional.ofNullable;

import java.util.function.Consumer;
import java.util.function.Function;

public class Consumers {
    @SafeVarargs
    public static <T> Consumer<T> applyToAll(Consumer<T>... consumers) {
        return value -> safeStream(consumers).forEach(consumer -> consumer.accept(value));
    }

    public static <T, V> Consumer<T> consume(Function<T, V> mapper, Consumer<V> consumer) {
        return e -> ofNullable(mapper).map(m -> m.apply(e)).ifPresent(consumer);
    }
}
