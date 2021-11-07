package com.sbaars.adventofcode.year20.days;

import static com.sbaars.adventofcode.common.Direction.EAST;
import static com.sbaars.adventofcode.common.Direction.turnDegrees;
import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;

import com.sbaars.adventofcode.common.Direction;
import com.sbaars.adventofcode.year20.Day2020;
import java.awt.*;
import java.util.List;

public class Day12 extends Day2020 {
  public Day12() {
    super(12);
  }

  public static void main(String[] args) {
    new Day12().printParts();
  }

  @Override
  public Object part1() {
    List<Flight> input = convertInput();
    Direction face = EAST;
    Point location = new Point(0, 0);
    for (Flight f : input) {
      switch (f.dir) {
        case 'L':
        case 'R':
          face = face.turnDegrees(f.distance, f.dir == 'R');
          break;
        case 'F':
          location = face.move(location, f.distance);
          break;
        default:
          location = Direction.getByDir(f.dir).move(location, f.distance);
          break;
      }
    }
    return abs(location.x) + abs(location.y);
  }

  private List<Flight> convertInput() {
    return dayStream().map(e -> new Flight(e.charAt(0), Integer.parseInt(e.substring(1)))).collect(toList());
  }

  @Override
  public Object part2() {
    List<Flight> input = convertInput();
    Point waypoint = new Point(10, -1);
    Point location = new Point(0, 0);
    for (Flight f : input) {
      switch (f.dir) {
        case 'L':
        case 'R':
          waypoint = turnDegrees(waypoint, f.distance, f.dir == 'R');
          break;
        case 'F':
          location = new Point(location.x + (waypoint.x * f.distance), location.y + (waypoint.y * f.distance));
          break;
        default:
          waypoint = Direction.getByDir(f.dir).move(waypoint, f.distance);
          break;
      }
    }
    return abs(location.x) + abs(location.y);
  }

  record Flight(char dir, int distance) {
  }


}
