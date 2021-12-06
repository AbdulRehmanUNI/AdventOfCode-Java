package com.sbaars.adventofcode.year21.days;

import com.sbaars.adventofcode.year19.util.LongCountMap;
import com.sbaars.adventofcode.year21.Day2021;
import java.io.IOException;

public class Day6 extends Day2021 {

  public Day6() throws IOException {
    super(6);
  }

  public static void main(String[] args) throws IOException {
    new Day6().printParts();
//    System.in.read();
//    new Day6().submitPart1();
//    new Day6().submitPart2();
  }

  @Override
  public Object part1() {
    return countFishes(80);
  }

  @Override
  public Object part2() {
    return countFishes(256);
  }

  private long countFishes(int iterations) {
    var cm = LongCountMap.ofFrequencies(dayNumberStream(","));
    var nc = new LongCountMap<Long>();
    for(int j = 0; j<iterations; j++) {
      for(var e : cm.entrySet()) {
        if(e.getKey() == 0){
          nc.increment(8L, e.getValue());
          nc.increment(6L, e.getValue());
        } else {
          nc.increment(e.getKey()-1, e.getValue());
        }
      }
      cm = nc;
      nc = new LongCountMap<>();
    }
    return cm.sumValues();
  }
}
