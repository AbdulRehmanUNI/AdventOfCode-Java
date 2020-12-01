package com.sbaars.adventofcode.year19.days;

import java.io.IOException;

import com.sbaars.adventofcode.common.Day;
import com.sbaars.adventofcode.year19.Day2019;
import com.sbaars.adventofcode.year19.intcode.IntcodeComputer;

public class Day2 extends Day2019 {
	public Day2(){
		super(2);
	}

	public static void main(String[] args) throws IOException {
		new Day2().printParts();
	}

	@Override
	public Object part1() throws IOException {
		return execute(12,2);
	}

	@Override 
	public Object part2() throws IOException {
		return bruteForceFindingNumber(19690720, 99);
	}

	private int bruteForceFindingNumber(int number, int bound) throws IOException {
		for(int i = 0; i<bound;i++) {
			for(int j = 0; j<bound; j++) {
				if(execute(i, j) == number) {
					return 100 * i + j;
				}
			}
		}
		return -1;
	}

	private long execute(int x, int y) throws IOException {
		IntcodeComputer computer = new IntcodeComputer(2, x, y);
		computer.run();
		return computer.firstElement();
	}
}
