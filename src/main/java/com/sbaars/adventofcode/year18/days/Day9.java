package com.sbaars.adventofcode.year18.days;

import com.sbaars.adventofcode.common.CircularList;
import com.sbaars.adventofcode.common.CircularList.Node;
import com.sbaars.adventofcode.common.map.LongCountMap;
import com.sbaars.adventofcode.year18.Day2018;

import java.util.Arrays;

import static com.sbaars.adventofcode.util.DataMapper.readString;

public class Day9 extends Day2018 {
  public Day9() {
    super(9);
  }

  public static void main(String[] args) {
    new Day9().printParts();
  }

  public record Input(long nPlayers, long lastMarble) {}

  @Override
  public Object part1() {
    return calcOutput(false);
  }

  private long calcOutput(boolean part2) {
    Input input = readString(day().trim(), "%n players; last marble is worth %n points", Input.class);
    CircularList cl = new CircularList(new long[]{0});
    LongCountMap<Long> lcm = new LongCountMap<>();
    long player = 0;
    for(int i = 1, j = 1; i<=input.lastMarble * (part2 ? 100 : 1); i++) {
      if(j == 23) {
        lcm.increment(player, i);
        cl.setCurrent(cl.move(cl.currentNode(), -6));
        lcm.increment(player, cl.remove(cl.currentNode().prev).value);
        j = 0;
      } else {
        Node newNode = new Node(i);
        cl.insertAfter(newNode, cl.currentNode().next);
        cl.setCurrent(newNode);
      }
      j++;
      player++;
      if(player >= input.nPlayers) player = 0;
    }
    return lcm.max();
  }

  @Override
  public Object part2() {
    return calcOutput(true);
  }
}
