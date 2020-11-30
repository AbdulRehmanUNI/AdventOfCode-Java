package com.sbaars.adventofcode.year19.days;

import com.sbaars.adventofcode.common.Day;
import com.sbaars.adventofcode.year19.intcode.IntcodeComputer;
import java.io.IOException;

public class Day9 implements Day {

	public static void main(String[] args) throws IOException {
		new Day9().printParts();
	}

	@Override
	public Object part1() throws IOException {
		return new IntcodeComputer(9, 1).run();
	}

	@Override
	public Object part2() throws IOException {
		return new IntcodeComputer(9, 2).run();
	}
}
