package com.gammacrawler.generator.map.connector;

import java.util.HashSet;

/**
 * @author WolfieWaffle
 *
 */
public class DungeonConnector {

	private int x;
	private int y;
	private int regionIDOne;
	private int regionIDTwo;
	private int hashCode;
	private HashSet<Integer> hashSet = new HashSet<>();

	/**
	 * @param x - int
	 * @param y - int
	 * @param regionIDOne - int
	 * @param regionIDTwo - int
	 */
	public DungeonConnector(int x, int y, int regionIDOne, int regionIDTwo) {
		this.x = x;
		this.y = y;
		this.regionIDOne = regionIDOne;
		this.regionIDTwo = regionIDTwo;
		hashSet.add(regionIDOne);
		hashSet.add(regionIDTwo);
		hashCode = hashSet.hashCode();
	}

	/**
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return regionIDOne
	 */
	public int getRegionIDOne() {
		return regionIDOne;
	}

	/**
	 * @return regionIDTwo
	 */
	public int getRegionIDTwo() {
		return regionIDTwo;
	}

	/**
	 * @return hashcode
	 */
	public int getHashCode() {
		return hashCode;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d) %d-%d", x, y, regionIDOne, regionIDTwo);
	}

}
