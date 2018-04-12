package com.gammacrawler.generator.map.connector;

import java.util.HashSet;

public class DungeonConnector {

	private int x;
	private int y;
	private int regionIDOne;
	private int regionIDTwo;
	private int hashCode;
	private HashSet<Integer> hashSet = new HashSet<>();

	public DungeonConnector(int x, int y, int regionIDOne, int regionIDTwo) {
		this.x = x;
		this.y = y;
		this.regionIDOne = regionIDOne;
		this.regionIDTwo = regionIDTwo;
		hashSet.add(regionIDOne);
		hashSet.add(regionIDTwo);
		hashCode = hashSet.hashCode();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRegionIDOne() {
		return regionIDOne;
	}

	public int getRegionIDTwo() {
		return regionIDTwo;
	}

	public int getHashCode() {
		return hashCode;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d) %d-%d", x, y, regionIDOne, regionIDTwo);
	}

}
