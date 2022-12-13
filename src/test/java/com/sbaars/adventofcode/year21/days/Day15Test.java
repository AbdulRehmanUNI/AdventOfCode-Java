  package com.sbaars.adventofcode.year21.days;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  import org.junit.jupiter.api.Test;
  import com.sbaars.adventofcode.year21.days.Day15;

  class Day15Test {
      Day15 day = new Day15();

      @Test
      void testPart1() {
          assertEquals("741", day.part1().toString());
      }

      @Test
      void testPart2() {
          assertEquals("2976", day.part2().toString());
      }
  }
