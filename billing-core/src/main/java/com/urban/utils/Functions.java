package com.urban.utils;

import static java.util.Optional.ofNullable;

import java.util.function.Function;

import org.springframework.data.util.Pair;

public class Functions {
    public static <T, U, R> Function<T, R> combine(Function<T, U> lhf, Function<U, R> rhf) {
        return e -> ofNullable(lhf)
                .map(lf -> lf.apply(e))
                .map(v -> ofNullable(rhf).map(rf -> rf.apply(v)).orElse(null))
                .orElse(null);
    }

    public static <T, V> V applyMapper(T element, Function<T, V> mapper) {
        return ofNullable(element)
                .map(e -> ofNullable(mapper).map(f -> f.apply(e)).orElse(null))
                .orElse(null);
    }

    public static <T, K, V> Function<T, Pair<K, V>> toPairFunction(Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return e -> Pair.of(applyMapper(e, keyMapper), applyMapper(e, valueMapper));
    }
}
