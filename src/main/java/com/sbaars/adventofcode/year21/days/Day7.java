package com.sbaars.adventofcode.year21.days;

import com.sbaars.adventofcode.year21.Day2021;
import java.io.IOException;
import java.util.stream.LongStream;

public class Day7 extends Day2021 {

  public Day7() {
    super(7);
  }

  public static void main(String[] args) throws IOException {
    new Day7().printParts();
  }

  @Override
  public Object part1() {
    return input().map(this::sol).min().getAsLong();
  }

  private LongStream input() {
    return dayNumberStream(",");
  }

  private long sol(long guess){
    return getSteps(guess).sum();
  }

  private LongStream getSteps(long guess) {
    return input().map(n -> guess > n ? guess - n : n - guess);
  }

  private long sol2(long guess){
    return getSteps(guess).map(n -> n*(n+1)/2).sum();
  }

  @Override
  public Object part2() {
    return input().map(this::sol2).min().getAsLong();
  }
}
