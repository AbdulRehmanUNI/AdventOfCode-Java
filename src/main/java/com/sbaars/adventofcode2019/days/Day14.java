package com.sbaars.adventofcode2019.days;

import java.io.IOException;
import java.util.Arrays;

import com.sbaars.adventofcode2019.common.Day;
import com.sbaars.adventofcode2019.util.CountMap;

public class Day14 implements Day {
	public static void main(String[] args) throws IOException {
		new Day14().printParts();
	}

	@Override
	public Object part1() throws IOException {
		Trade[] trades = Arrays.stream(readDay(14).split(System.lineSeparator())).map(Trade::new).toArray(Trade[]::new);
		int cost = findCost(trades, new Item(1, "FUEL"), new CountMap<>());
		//System.out.println(created);
		return cost;
		//return Arrays.stream(trades).map(e -> e.output.item).distinct().count();
		/*CountMap<String> items = new CountMap<>();
		for(int i = 13311; i<15000; i++) {
			System.out.println("We now have "+i+" ORE");
			items.put("ORE", i);
			if(i == 14000) {
				System.out.println("Hi");
			}
			if(canMakeFuel(trades, items)) {
				return i;
			}
		}
		return 0;*/
	}

	private Trade getTrade(Trade[] trades, String key) {
		return Arrays.stream(trades).filter(e -> e.output.item.equals(key)).findAny().get();
	}
	
	CountMap<String> created = new CountMap<>();
	private int findCost(Trade[] trades, Item buyingItem, CountMap<String> leftOver) {
		if(buyingItem.item.equals("ORE"))
			return buyingItem.amount;
		else if(buyingItem.amount <= leftOver.get(buyingItem.item)) {
			leftOver.increment(buyingItem.item, -buyingItem.amount);
			return 0;
		}
		buyingItem.amount-=leftOver.get(buyingItem.item);
		leftOver.put(buyingItem.item, 0);
		
		Trade fuelTrade = getTrade(trades, buyingItem.item);
		int timesApplied = (int)Math.ceil((double)buyingItem.amount/(double)fuelTrade.output.amount);
		
		//System.out.println("Buy "+timesApplied+"x"+fuelTrade.output.amount+" "+buyingItem.item);
		
		/*if(leftOver.get(fuelTrade.output.item) >= fuelTrade.output.amount) {
			System.out.println("Leftover "+fuelTrade.output.item+" = "+leftOver.get(fuelTrade.output.item)+" for trade "+fuelTrade);
			leftOver.increment(fuelTrade.output.item, -fuelTrade.output.amount);
			System.out.println("Leftover "+fuelTrade.output.item+" = "+leftOver.get(fuelTrade.output.item)+" for trade "+fuelTrade);
			//skip = fuelTrade.output.item;
			//System.out.println("Enough "+fuelTrade.output.item+" !");
			timesApplied--;
			//return 0; //You have this material, so you get it for free :)
		}*/
		//leftOver.increment(fuelTrade.output.item, buyingItem.amount % fuelTrade.output.amount);
		
		int totalCost = 0;
		for(Item cost : fuelTrade.input) {
			totalCost+=findCost(trades, new Item(cost.amount*timesApplied, cost.item), leftOver);
		}
		//for(int i = 0; i<timesApplied; i++) {	
			/*if(leftOver.get(fuelTrade.output.item) >= fuelTrade.output.amount) {
				System.out.println("Leftover "+fuelTrade.output.item+" = "+leftOver.get(fuelTrade.output.item)+" for trade "+fuelTrade);
				leftOver.increment(fuelTrade.output.item, -fuelTrade.output.amount);
				System.out.println("Leftover "+fuelTrade.output.item+" = "+leftOver.get(fuelTrade.output.item)+" for trade "+fuelTrade);
				//skip = fuelTrade.output.item;
				System.out.println("Enough "+fuelTrade.output.item+" LEFTOVER!");
				continue;
			}*/
			//totalCost = applyTrade(trades, fuelTrade, totalCost, leftOver);
			//created.increment(buyingItem.item, fuelTrade.output.amount);
		//}
		
		leftOver.increment(buyingItem.item, fuelTrade.output.amount * timesApplied - buyingItem.amount);
		//System.out.println(fuelTrade.output.item+" nLeftOver "+leftOver.get(fuelTrade.output.item));
		//System.out.println("Bought "+(timesApplied*fuelTrade.output.amount)+" "+buyingItem.item+" for "+totalCost);
		//System.out.println(fuelTrade.output.item+" costs "+totalCost+" times "+timesApplied);
		return totalCost;
	}

	private int applyTrade(Trade[] trades, Trade fuelTrade, int totalCost, CountMap<String> leftOver) {
		for(Item cost : fuelTrade.input) {
			/*if(leftOver.get(cost.item) >= cost.amount) {
				//System.out.println("Leftover "+fuelTrade.output.item+" = "+leftOver.get(fuelTrade.output.item)+" for trade "+fuelTrade);
				leftOver.increment(cost.item, -cost.amount);
				//System.out.println("Leftover "+fuelTrade.output.item+" = "+leftOver.get(fuelTrade.output.item)+" for trade "+fuelTrade);
				//skip = fuelTrade.output.item;
				//System.out.println("Enough "+fuelTrade.output.item+" !");
				continue; //You have this material, so you get it for free :)
			}*/
			//if(cost.item.equals("ORE")) {
			//	totalCost+=cost.amount;
			//	System.out.println("Spend "+cost.amount+" ORE to get "+fuelTrade.output.amount+" "+fuelTrade.output.item+" in trade "+fuelTrade);
			//} else {
				totalCost+=findCost(trades, new Item(cost.amount, cost.item), leftOver);
				//leftOver.increment(fuelTrade.output.item, cost.amount % fuelTrade.output.amount);
			//}
		}
		return totalCost;
	}
	
	@Override
	public Object part2() throws IOException {
		return 0;
	}
	
	class Trade {
		final Item[] input;
		final Item output;
		
		public Trade(String trade) {
			String[] inputOutput = trade.split(" => ");
			input = Arrays.stream(inputOutput[0].split(", ")).map(Item::new).toArray(Item[]::new);
			output = new Item(inputOutput[1]);
		}

		@Override
		public String toString() {
			return "Trade [input=" + Arrays.toString(input) + ", output=" + output + "]";
		}
	}
	
	class Item {
		int amount;
		String item;
		
		public Item(String item) {
			String[] i = item.split(" ");
			amount = Integer.parseInt(i[0]);
			this.item = i[1];
		}

		public Item(int i, String string) {
			amount = i;
			item = string;
		}

		@Override
		public String toString() {
			return "Item [amount=" + amount + ", item=" + item + "]";
		}
	}
}
