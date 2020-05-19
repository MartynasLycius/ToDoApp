/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.model;

/**
 *
 * @author nazmul
 */
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

public interface ForwardingStream<T> extends Stream<T> {
    Stream<T> getStream();
    default Stream<T> filter(Predicate<? super T> predicate) { return this.getStream().filter(predicate); }
    default <R> Stream<R> map(Function<? super T, ? extends R> mapper) { return this.getStream().map(mapper); }
    default IntStream mapToInt(ToIntFunction<? super T> mapper) { return this.getStream().mapToInt(mapper); }
    default LongStream mapToLong(ToLongFunction<? super T> mapper) { return this.getStream().mapToLong(mapper); }
    default DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) { return this.getStream().mapToDouble(mapper); }
    default <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) { return this.getStream().flatMap(mapper); }
    default IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) { return this.getStream().flatMapToInt(mapper); }
    default LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) { return this.getStream().flatMapToLong(mapper); }
    default DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) { return this.getStream().flatMapToDouble(mapper); }
    default Stream<T> distinct() { return this.getStream().distinct(); }
    default Stream<T> sorted() { return this.getStream().sorted(); }
    default Stream<T> sorted(Comparator<? super T> comparator) { return this.getStream().sorted(comparator); }
    default Stream<T> peek(Consumer<? super T> action) { return this.getStream().peek(action); }
    default Stream<T> limit(long maxSize) { return this.getStream().limit(maxSize); }
    default Stream<T> skip(long n) { return this.getStream().skip(n); }
    default void forEach(Consumer<? super T> action) { this.getStream().forEach(action); }
    default void forEachOrdered(Consumer<? super T> action) { this.getStream().forEachOrdered(action); }
    default Object[] toArray() { return this.getStream().toArray(); }
    default <A> A[] toArray(IntFunction<A[]> generator) { return this.getStream().toArray(generator); }
    default T reduce(T identity, BinaryOperator<T> accumulator) { return this.getStream().reduce(identity, accumulator); }
    default Optional<T> reduce(BinaryOperator<T> accumulator) { return this.getStream().reduce(accumulator); }
    default <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) { return this.getStream().reduce(identity, accumulator, combiner); }
    default <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) { return this.getStream().collect(supplier, accumulator, combiner); }
    default <R, A> R collect(Collector<? super T, A, R> collector) { return this.getStream().collect(collector); }
    default Optional<T> min(Comparator<? super T> comparator) { return this.getStream().min(comparator); }
    default Optional<T> max(Comparator<? super T> comparator) { return this.getStream().max(comparator); }
    default long count() { return this.getStream().count(); }
    default boolean anyMatch(Predicate<? super T> predicate) { return this.getStream().anyMatch(predicate); }
    default boolean allMatch(Predicate<? super T> predicate) { return this.getStream().allMatch(predicate); }
    default boolean noneMatch(Predicate<? super T> predicate) { return this.getStream().noneMatch(predicate); }
    default Optional<T> findFirst() { return this.getStream().findFirst(); }
    default Optional<T> findAny() { return this.getStream().findAny(); }
    default Iterator<T> iterator() { return this.getStream().iterator(); }
    default Spliterator<T> spliterator() { return this.getStream().spliterator(); }
    default boolean isParallel() { return this.getStream().isParallel(); }
    default Stream<T> sequential() { return this.getStream().sequential(); }
    default Stream<T> parallel() { return this.getStream().parallel(); }
    default Stream<T> unordered() { return this.getStream().unordered(); }
    default Stream<T> onClose(Runnable closeHandler) { return this.getStream().onClose(closeHandler); }
    default void close() { this.getStream().close(); }
}
