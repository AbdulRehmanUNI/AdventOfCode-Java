package com.sbaars.adventofcode.year20.days;

import com.sbaars.adventofcode.common.Day;
import com.sbaars.adventofcode.year20.Day2020;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;

public class Day1 extends Day2020 {
    public static void main(String[] args) throws IOException {
        new Day1().printParts();
    }

    public Day1(){super(1);}

    @Override
    public Object part1() throws IOException {
        return dayNumberStream().flatMap(a ->
            dayNumberStream().filter(b -> a + b == 2020L).map(b -> a * b)
        ).findAny().getAsLong();
    }

    @Override
    public Object part2() throws IOException {
        long[] s = dayNumbers();
        for (long a : s) {
            for (long b : s) {
                for (long c : s) {
                    if (a != b && b != c && a + b + c == 2020L) {
                        return a * b * c;
                    }
                }
            }
        }
        return 0;
    }
}
