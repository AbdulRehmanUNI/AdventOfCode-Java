package com.sbaars.adventofcode.util;

import com.sbaars.adventofcode.common.Pair;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.IntStream.range;

public class AOCUtils {
    public static void verify(boolean b) {
        verify(b, "Something went wrong");
    }

    public static void verify(boolean b, String message) {
        if(!b) {
            throw new IllegalStateException(message);
        }
    }

    public static<A> Stream<Pair<A, A>> connectedPairs(List<A> l) {
        return range(1, l.size()).mapToObj(i -> Pair.of(l.get(i-1), l.get(i)));
    }

    public static<A> Stream<Pair<A, A>> pairs(List<A> l) {
        return range(1, l.size()/2).map(i -> i + ((i-1)*2)).mapToObj(i -> Pair.of(l.get(i-1), l.get(i)));
    }

    public static<A> Stream<Pair<A, A>> allPairs(List<A> l) {
        return range(0, l.size()).boxed().flatMap(i -> range(i+1, l.size()).mapToObj(j -> new Pair<>(l.get(i), l.get(j))));
    }

    public static<A, B> Stream<Pair<A, B>> allPairs(List<A> l1, List<B> l2) {
        return range(0, l1.size()).boxed().flatMap(i -> l2.stream().map(b -> new Pair<>(l1.get(i), b)));
    }

    public static<E> E last(List<E> list) {
        return list.get(list.size() - 1);
    }

    public static<A, B, C> Stream<C> zip(Stream<? extends A> a,
                                         Stream<? extends B> b,
                                         BiFunction<? super A, ? super B, ? extends C> zipper) {
        Objects.requireNonNull(zipper);
        Spliterator<? extends A> aSpliterator = Objects.requireNonNull(a).spliterator();
        Spliterator<? extends B> bSpliterator = Objects.requireNonNull(b).spliterator();

        // Zipping loses DISTINCT and SORTED characteristics
        int characteristics = aSpliterator.characteristics() & bSpliterator.characteristics() &
                ~(Spliterator.DISTINCT | Spliterator.SORTED);

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                : -1;

        Iterator<A> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<B> bIterator = Spliterators.iterator(bSpliterator);
        Iterator<C> cIterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(aIterator.next(), bIterator.next());
            }
        };

        Spliterator<C> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
        return (a.isParallel() || b.isParallel())
                ? StreamSupport.stream(split, true)
                : StreamSupport.stream(split, false);
    }

    public static long binarySearch(Function<Long, Long> testFunction, long target, long low, long high) {
        while(true) {
            if(low == high) return low;
            long size = (high - low) / 3;
            long l1 = low + size;
            long res1 = testFunction.apply(l1);
            long diff1 = Math.abs(res1 - target);
            if(diff1 == 0) return l1;
            long l2 = l1 + size;
            long res2 = testFunction.apply(l2);
            long diff2 = Math.abs(res2 - target);
            if(diff2 == 0) return l2;
            if(diff1 <= diff2) high = l2-1;
            if (diff1 >= diff2) low = l1+1;
        }
    }

    public static long binarySearch(Function<Long, Long> testFunction, long low, long high) {
        return binarySearch(testFunction, 0, low, high);
    }

    public static long binarySearch(Function<Long, Long> testFunction) {
        return binarySearch(testFunction, 0, 0, Long.MAX_VALUE);
    }
}
