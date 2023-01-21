package com.sbaars.adventofcode.year21.days;

import com.sbaars.adventofcode.common.Direction;
import com.sbaars.adventofcode.common.location.Loc;
import com.sbaars.adventofcode.year21.Day2021;

import java.awt.*;
import java.util.List;

import static com.sbaars.adventofcode.util.DataMapper.readString;

public class Day2 extends Day2021 {
  public Day2() {
    super(2);
  }

  public static void main(String[] args) {
    new Day2().printParts();
  }

  @Override
  public Object part1() {
    Loc p = dayStream()
        .map(e -> readString(e, "%s %n", Move.class))
        .reduce(
            new Loc(),
            (acc, m) -> new Loc(m.direction().move(acc.getPoint(), Math.toIntExact(m.n))),
            Loc::move
        );
    return p.x * p.y;
  }

  @Override
  public Object part2() {
    List<Move> input = dayStream().map(e -> readString(e, "%s %n", Move.class)).toList();
    Point p = new Point(0, 0);
    long aim = 0;
    for(Move m : input) {
      Direction d = m.direction();
      switch (d) {
        case NORTH -> aim -= m.n;
        case SOUTH -> aim += m.n;
        case EAST -> {
          p = Direction.EAST.move(p, Math.toIntExact(m.n));
          p = Direction.SOUTH.move(p, Math.toIntExact(aim * m.n));
        }
        default -> throw new IllegalStateException("There must be a direction.");
      }
    }
    return p.x * p.y;
  }

  public record Move (String dir, long n) {
    public Direction direction(){
      return switch (dir) {
        case "forward" -> Direction.EAST;
        case "up" -> Direction.NORTH;
        case "down" -> Direction.SOUTH;
        default -> throw new IllegalStateException("Unknown dir: " + dir);
      };
    }
  }
}
