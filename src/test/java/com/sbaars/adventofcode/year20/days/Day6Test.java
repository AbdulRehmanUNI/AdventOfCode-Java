package com.sbaars.adventofcode.year20.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {
    Day6 day = new Day6();

    @Test
    void testPart1() {
        assertEquals("7283", day.part1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("3520", day.part2().toString());
    }
}
