package com.sbaars.adventofcode.year23.days;

import com.sbaars.adventofcode.common.Builder;
import com.sbaars.adventofcode.common.grid.InfiniteGrid;
import com.sbaars.adventofcode.common.location.Loc;
import com.sbaars.adventofcode.year23.Day2023;

import java.util.*;

import static com.sbaars.adventofcode.common.Direction.four;

public class Day21 extends Day2023 {
  public Day21() {
    super(21);
  }

  public static void main(String[] args) {
    new Day21().printParts();
  }

  @Override
  public Object part1() {
    var grid = new InfiniteGrid(dayGrid());
    Builder<Set<Loc>> places = new Builder<>(HashSet::new);
    places.get().add(grid.findAll('S').findAny().get());
    for (int i = 0; i < 64; i++) {
      places.get().stream().flatMap(l -> four().map(d -> d.move(l))).filter(l -> grid.getChar(l) != '#').forEach(places.getNew()::add);
      places.refresh();
    }
    return places.get().size();
  }

  @Override
  public Object part2() {
    char[][] grid = dayGrid();
    var infGrid = new InfiniteGrid(grid);
    Loc start = infGrid.findAll('S').findAny().get();

    // Core algorithm by abnew123: https://github.com/abnew123/aoc2023/blob/main/src/solutions/Day21.java
    Map<Loc, Integer> distances = new HashMap<>();
    Builder<Set<Loc>> places = new Builder<>(HashSet::new);
    places.get().add(start);
    distances.put(start, 0);
    List<Long> totals = new ArrayList<>();
    List<Long> deltas = new ArrayList<>();
    List<Long> deltaDeltas = new ArrayList<>();
    long totalReached = 0;
    for (int i = 1; ; i++) {
      int finalIndex = i;
      places.get().stream().flatMap(l -> four().map(d -> d.move(l))).forEach(l -> {
        if (distances.get(l) == null) {
          if (grid[((l.intX() % grid.length) + grid.length) % grid.length][((l.intY() % grid.length) + grid.length) % grid.length] != '#') {
            places.getNew().add(l);
            distances.put(l, finalIndex);
          }
        }
      });
      if (i % 2 == 1) {
        totalReached += places.getNew().size();
        if (i % 262 == 65) {
          totals.add(totalReached);
          int currTotals = totals.size();
          if (currTotals > 1) {
            deltas.add(totals.get(currTotals - 1) - totals.get(currTotals - 2));
          }
          int currDeltas = deltas.size();
          if (currDeltas > 1) {
            deltaDeltas.add(deltas.get(currDeltas - 1) - deltas.get(currDeltas - 2));
          }
          if (deltaDeltas.size() > 1) {
            long neededLoopCount = 26501365 / 262 - 1;
            long currentLoopCount = i / 262 - 1;
            long deltaLoopCount = neededLoopCount - currentLoopCount;
            long deltaLoopCountTriangular = (neededLoopCount * (neededLoopCount + 1)) / 2 - (currentLoopCount * (currentLoopCount + 1)) / 2;
            long deltaDelta = deltaDeltas.get(deltaDeltas.size() - 1);
            long initialDelta = deltas.get(0);
            return deltaDelta * deltaLoopCountTriangular + initialDelta * deltaLoopCount + totalReached;
          }
        }

      }
      places.refresh();
    }
  }
}
