  package com.sbaars.adventofcode.year22.days;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  import org.junit.jupiter.api.Test;
  import com.sbaars.adventofcode.year22.days.Day5;

  class Day5Test {
      Day5 day = new Day5();

      @Test
      void testPart1() {
          assertEquals("FRDSQRRCD", day.part1().toString());
      }

      @Test
      void testPart2() {
          assertEquals("HRFTQVWNN", day.part2().toString());
      }
  }
