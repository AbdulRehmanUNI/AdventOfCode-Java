package com.sbaars.adventofcode.year15.days;

import com.sbaars.adventofcode.year15.Day2015;

import static com.sbaars.adventofcode.util.AOCUtils.findReduce;
import static com.sbaars.adventofcode.util.AOCUtils.zip;
import static java.util.stream.IntStream.range;

public class Day1 extends Day2015 {
  public Day1() {
    super(1);
  }

  public static void main(String[] args) {
    new Day1().printParts();
  }

  @Override
  public Object part1() {
    String in = day().trim();
    return in.replace(")", "").length() - in.replace("(", "").length();
  }

  @Override
  public Object part2() {
    String in = day().trim();
    return findReduce(
            zip(range(1, in.length()).boxed(), in.chars().boxed()),
            0,
            (c, acc) -> acc + (c.b() == '(' ? 1 : -1),
            acc -> acc < 0
    ).a();
  }
}
