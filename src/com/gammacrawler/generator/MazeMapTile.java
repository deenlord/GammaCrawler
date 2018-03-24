package com.gammacrawler.generator;

public class MazeMapTile {

	private int x;
	private int y;
	private boolean visited = false;
	private boolean hasLeft = false;
	private boolean hasRight = false;
	private boolean hasUp = false;
	private boolean hasDown = false;

	public MazeMapTile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public MazeMapTile(int x, int y, boolean visited) {
		this(x, y);
		this.visited = visited;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean hasLeft() {
		return hasLeft;
	}

	public void setHasLeft(boolean hasLeft) {
		this.hasLeft = hasLeft;
	}

	public boolean hasRight() {
		return hasRight;
	}

	public void setHasRight(boolean hasRight) {
		this.hasRight = hasRight;
	}

	public boolean hasUp() {
		return hasUp;
	}

	public void setHasUp(boolean hasUp) {
		this.hasUp = hasUp;
	}

	public boolean hasDown() {
		return hasDown;
	}

	public void setHasDown(boolean hasDown) {
		this.hasDown = hasDown;
	}

}
