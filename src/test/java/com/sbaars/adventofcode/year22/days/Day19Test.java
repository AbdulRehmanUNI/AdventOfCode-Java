package com.sbaars.adventofcode.year22.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {
    Day19 day = new Day19();

    @Test
    void testPart1() {
        assertEquals("1306", day.part1().toString());
    }

    @Test
    void testPart2() {
        assertEquals("37604", day.part2().toString());
    }
}
