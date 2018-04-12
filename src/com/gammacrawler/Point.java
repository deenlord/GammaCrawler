package com.gammacrawler;

public class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

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
