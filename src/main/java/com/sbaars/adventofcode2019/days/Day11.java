package com.sbaars.adventofcode2019.days;

import java.awt.Point;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.sbaars.adventofcode2019.common.Day;
import com.sbaars.adventofcode2019.intcode.IntcodeComputer;

public class Day11 implements Day {
	
	enum Direction { UP, RIGHT, DOWN, LEFT }

	public static void main(String[] args) throws IOException {
		new Day11().printParts();
	}

	@Override
	public Object part1() throws IOException {
		return robotWalk(false);
	}

	private Object robotWalk(boolean startWhite) throws IOException {
		IntcodeComputer c = new IntcodeComputer(11);
		Point currentLocation = new Point(0,0);
		Direction dir = Direction.UP;
		final Set<Point> paintedOnce = new HashSet<>();
		final Set<Point> whitePlaces = new HashSet<>();
		if(startWhite)
			whitePlaces.add(currentLocation);
		while(true) {
			c.setInput(whitePlaces.contains(currentLocation) ? 1 : 0);
			int paintColor = c.runInt();
			if(paintColor == -2)
				break;
			int turn = c.runInt();
			if(turn == -2)
				break;
			paintedOnce.add(currentLocation);
			if(paintColor == 1) {
				whitePlaces.add(currentLocation);
			} else if(paintColor == 0) {
				whitePlaces.remove(currentLocation);
			}
			
			dir = turn(dir, turn == 1);
			currentLocation = move(currentLocation, dir);
		}
		return startWhite ? constructImage(whitePlaces) : paintedOnce.size();
	}
	
	private int constructImage(Set<Point> whitePlaces) {
		int cornerX = whitePlaces.stream().mapToInt(e -> e.x).min().getAsInt();
		int cornerY = whitePlaces.stream().mapToInt(e -> e.y).min().getAsInt();
		whitePlaces.forEach(e -> e.move(e.x - cornerX, e.y - cornerY));
		int sizex = whitePlaces.stream().mapToInt(e -> e.x).max().getAsInt();
		int sizey = whitePlaces.stream().mapToInt(e -> e.y).max().getAsInt();
		int[][] places = new int[sizex][sizey];
		for(Point p : whitePlaces) {
			places[p.x][p.y] = 1;
		}
		return 0;
	}

	public Direction turn(Direction dir, boolean right) {
		int cur = dir.ordinal() + (right ? 1 : -1);
		if(cur == Direction.values().length)
			cur = 0;
		else if(cur == -1)
			cur = 3;
		return Direction.values()[cur];
	}
	
	private Point move(Point currentLocation, Direction dir2) {
		switch (dir2) {
			case UP: return new Point(currentLocation.x, currentLocation.y+1);
			case DOWN: return new Point(currentLocation.x, currentLocation.y-1);
			case RIGHT: return new Point(currentLocation.x+1, currentLocation.y);
			case LEFT: return new Point(currentLocation.x-1, currentLocation.y);
		}
		return null;
	}

	@Override
	public Object part2() throws IOException {
		return robotWalk(true);
	}
}
