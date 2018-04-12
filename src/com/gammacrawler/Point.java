package com.gammacrawler;

public class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point direction(Point point, Direction direction) {
		switch (direction) {
		case NORTH:
			return new Point(point.x, point.y - 1);
		case SOUTH:
			return new Point(point.x, point.y + 1);
		case EAST:
			return new Point(point.x + 1, point.y);
		case WEST:
			return new Point(point.x - 1, point.y);
		}
		return point;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
