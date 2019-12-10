package com.sbaars.adventofcode2019.days;

import java.io.IOException;

import com.sbaars.adventofcode2019.common.Day;
import com.sbaars.adventofcode2019.intcode.IntcodeComputer;
import com.sbaars.adventofcode2019.util.DoesFileOperations;

public class Day2 implements Day, DoesFileOperations {

	public static void main(String[] args) throws IOException {
		new Day2().printParts();
	}
	
	@Override
	public int part1() throws IOException {
		return execute(12,2);
	}
	
	@Override 
	public int part2() throws IOException {
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

	private int execute(int x, int y) throws IOException {
		IntcodeComputer computer = new IntcodeComputer(2, x, y);
		computer.run();
		return Math.toIntExact(computer.firstElement());
	}
}
