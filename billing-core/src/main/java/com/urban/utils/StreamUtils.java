package com.urban.utils;

import static com.urban.utils.Functions.combine;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {
    public static <T> Stream<T> safeStream(T... elements) {
        return ofNullable(elements).map(Stream::of).orElse(Stream.empty()).filter(Objects::nonNull);
    }

    public static <T> Stream<T> safeStream(Iterable<T> elements) {
        return ofNullable(elements)
                .map(combine(Iterable::spliterator, spliterator -> StreamSupport.stream(spliterator, false)))
                .orElse(Stream.empty())
                .filter(Objects::nonNull);
    }

    public static <T, K, V> Map<K, V> filterAndCollectToMap(Iterable<T> elements, Predicate<T> filter, Function<T, K> keyExtractor, Function<T, V> valueExtractor) {
        return safeStream(elements).filter(filter).collect(Collectors.toMap(keyExtractor, valueExtractor));
    }

    public static <K, V> Map<K, V> collectToMap(Iterable<V> elements, Function<V, K> keyExtractor) {
        return safeStream(elements).collect(Collectors.toMap(keyExtractor, Function.identity(), (l, r) -> l));
    }

    public static <K, V, E> Map<K, V> collectToMap(Iterable<E> elements, Function<E, K> keyExtractor, Function<E, V> valueExtractor) {
        return safeStream(elements).collect(Collectors.toMap(keyExtractor, valueExtractor, (l, r) -> l));
    }

    public static <K, V> Map<K, List<V>> groupingBy(Iterable<V> elements, Function<V, K> keyExtractor) {
        return safeStream(elements).collect(Collectors.groupingBy(keyExtractor));
    }

    public static <T, K, V> Map<K, List<V>> groupingBy(Iterable<T> elements, Function<T, K> keyExtractor, Function<T, V> valueExtractor) {
        return safeStream(elements).collect(Collectors.groupingBy(keyExtractor, mapping(valueExtractor, toList())));
    }

    public static <T> Set<T> collectToSet(Iterable<T> elements) {
        return safeStream(elements).collect(Collectors.toSet());
    }

    public static <T> Set<T> collectToSet(Stream<T> stream) {
        return ofNullable(stream).orElse(Stream.empty()).collect(Collectors.toSet());
    }

    public static <T> Optional<T> findFirst(Iterable<T> elements, Predicate<T> criteria) {
        return safeStream(elements).filter(criteria).findFirst();
    }

    public static <T> Optional<T> findFirst(T[] elements, Predicate<T> criteria) {
        return safeStream(elements).filter(criteria).findFirst();
    }

    public static <T> List<T> filter(Iterable<T> elements, Predicate<T> criteria) {
        return safeStream(elements).filter(criteria).collect(toList());
    }

    public static <T> void filterAndForEach(Iterable<T> elements, Predicate<T> criteria, Consumer<T> forEachConsumer) {
        safeStream(elements).filter(criteria).forEach(forEachConsumer);
    }

    public static <T> boolean anyMatch(Iterable<T> elements, Predicate<T> matcher) {
        return safeStream(elements).anyMatch(matcher);
    }
    public static <T> boolean allMatch(Iterable<T> elements, Predicate<T> matcher) {
        return safeStream(elements).allMatch(matcher);
    }
    public static <T> boolean noneMatch(Iterable<T> elements, Predicate<T> matcher) {
        return safeStream(elements).noneMatch(matcher);
    }

    public static <T> void forEach(Iterable<T> elements, Consumer<T> forEachConsumer) {
        safeStream(elements).forEach(forEachConsumer);
    }

    public static <T, V> List<V> map(Iterable<T> elements, Function<T, V> mapper) {
        return safeStream(elements).map(mapper).collect(toList());
    }

    public static <T, V> List<V> filterAndMap(Iterable<T> elements, Predicate<T> filter, Function<T, V> mapper) {
        return safeStream(elements).filter(filter).map(mapper).collect(toList());
    }

    public static <T, V> void mapAndForEach(Iterable<T> elements, Function<T, V> mapper, Consumer<V> consumer) {
        safeStream(elements).map(mapper).forEach(consumer);
    }

    public static <T, V> Set<V> mapToSet(Iterable<T> elements, Function<T, V> mapper) {
        return safeStream(elements).map(mapper).collect(Collectors.toSet());
    }

    public static <T> long size(Iterable<T> elements) {
        return safeStream(elements).count();
    }

    public static <T> List<T> safeAsList(T... elements) {
        return safeStream(elements).collect(toList());
    }

    public static <T, V> List<V> mapAndDistinct(Iterable<T> elements, Function<T, V> mapper) {
        return safeStream(elements).map(mapper).distinct().collect(toList());
    }
}
