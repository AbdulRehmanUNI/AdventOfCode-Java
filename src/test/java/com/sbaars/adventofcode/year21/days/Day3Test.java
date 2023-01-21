package com.sbaars.adventofcode.year21.days;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {
  Day3 day = new Day3();

  @Test
  void testPart1() {
    assertEquals("3901196", day.part1().toString());
  }

  @Test
  void testPart2() {
    assertEquals("4412188", day.part2().toString());
  }
}
