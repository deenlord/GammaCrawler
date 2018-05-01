package com.gammacrawler.util;

import com.gammacrawler.Direction;

public class Point {
	public int x;
	public int y;

	/**
	 * Creates a point with a given coordinate
	 * 
	 * @param x - x coordinate
	 * @param y - y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the point if moved in a given direction
	 * 
	 * @param direction - direction for which to move point
	 * @return new point based on the movement of the current point
	 */
	public Point direction(Direction direction) {
		switch (direction) {
		case NORTH:
			return new Point(x, y - 1);
		case SOUTH:
			return new Point(x, y + 1);
		case EAST:
			return new Point(x + 1, y);
		case WEST:
			return new Point(x - 1, y);
		}
		return this;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
