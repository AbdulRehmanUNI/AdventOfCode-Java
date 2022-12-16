  package com.sbaars.adventofcode.year22.days;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  import org.junit.jupiter.api.Test;
  import com.sbaars.adventofcode.year22.days.Day16;

  class Day16Test {
      Day16 day = new Day16();

      @Test
      void testPart1() {
          assertEquals("1906", day.part1().toString());
      }

      @Test
      void testPart2() {
          assertEquals("2548", day.part2().toString());
      }
  }
