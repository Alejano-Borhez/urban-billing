package com.urban.utils;

import static com.urban.utils.Functions.applyMapper;
import static java.util.Optional.ofNullable;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Predicates {

    public static <T> Predicate<T> and(Predicate<T> lhp, Predicate<T> rhp) {
        return ofNullable(lhp)
                .map(lp -> ofNullable(rhp).map(lp::and).orElse(null))
                .orElse(alwaysFalse());
    }

    private static <T> Predicate<T> alwaysFalse() {
        return p -> false;
    }
    public static <T> Predicate<T> alwaysTrue() {
        return p -> true;
    }

    public static <T> Predicate<T> or(Predicate<T> lhp, Predicate<T> rhp) {
        return ofNullable(lhp)
                .map(lp -> ofNullable(rhp).map(lp::or).orElse(null))
                .orElse(alwaysFalse());
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return ofNullable(predicate)
                .map(Predicate::negate)
                .orElse(alwaysFalse());
    }

    public static <T, V> Predicate<T> compose(Function<T, V> mapper, Predicate<V> predicate) {
        return e -> ofNullable(predicate).map(p -> p.test(applyMapper(e, mapper))).orElse(false);
    }

    public static <T, V> Predicate<T> eq(Function<T, V> mapper, V value) {
        return e -> Objects.equals(applyMapper(e, mapper), value);
    }

    public static <T, V> Predicate<T> eq(V value) {
        return e -> Objects.equals(applyMapper(e, Function.identity()), value);
    }

    public static <T, V> Predicate<T> in(Function<T, V> mapper, Collection<V> values) {
        return e -> StreamUtils.safeStream(values).anyMatch(v -> Objects.equals(applyMapper(e, mapper), v));
    }

    public static <T, V> Predicate<T> isNull(Function<T, V> mapper) {
        return e -> Objects.isNull(applyMapper(e, mapper));
    }

    public static <T, V> Predicate<T> isNotNull(Function<T, V> mapper) {
        return e -> Objects.nonNull(applyMapper(e, mapper));
    }

}
