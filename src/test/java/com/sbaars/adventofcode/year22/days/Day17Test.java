  package com.sbaars.adventofcode.year22.days;

  import static org.junit.jupiter.api.Assertions.assertEquals;

  import org.junit.jupiter.api.Test;
  import com.sbaars.adventofcode.year22.days.Day17;

  class Day17Test {
      Day17 day = new Day17();

      @Test
      void testPart1() {
          assertEquals("3141", day.part1().toString());
      }

      @Test
      void testPart2() {
          assertEquals("1561739130391", day.part2().toString());
      }
  }
