package com.sbaars.adventofcode.year20.days;

import com.sbaars.adventofcode.common.map.CountMap;
import com.sbaars.adventofcode.common.map.LongCountMap;
import com.sbaars.adventofcode.year20.Day2020;
import org.apache.commons.lang3.ArrayUtils;

import static java.util.Arrays.sort;

public class Day10 extends Day2020 {
  public Day10() {
    super(10);
  }

  public static void main(String[] args) {
    new Day10().printParts();
  }

  @Override
  public Object part1() {
    long[] input = getInput();
    CountMap<Long> diffs = new CountMap<>();
    for (int i = 1; i < input.length; i++) {
      diffs.increment(input[i] - input[i - 1]);
    }
    return diffs.get(1L) * diffs.get(3L);
  }

  @Override
  public Object part2() {
    long[] input = getInput();
    LongCountMap<Long> nRoutes = new LongCountMap<>();
    nRoutes.increment(input[input.length - 1]);
    for (int i = input.length - 2; i >= 0; i--) {
      for (int j = i + 1; j < input.length && j <= i + 3; j++) {
        if (input[j] - input[i] <= 3) {
          nRoutes.increment(input[i], nRoutes.get(input[j]));
        }
      }
    }
    return nRoutes.get(0L);
  }

  private long[] getInput() {
    long[] input = dayNumbers();
    sort(input);
    input = ArrayUtils.add(input, input[input.length - 1] + 3);
    input = ArrayUtils.addFirst(input, 0L);
    return input;
  }
}
