package com.gammacrawler.generator.map;

public class MazeMapTile {

	private int compressedX;
	private int compressedY;
	private int fullX;
	private int fullY;
	private int regionID; // Used for determining if a tile connects two separate regions.
	private boolean visited = false;
	private boolean hasLeft = false;
	private boolean hasRight = false;
	private boolean hasUp = false;
	private boolean hasDown = false;

	public MazeMapTile(int compressedX, int compressedY) {
		this.compressedX = compressedX;
		this.compressedY = compressedY;
		this.fullX = ((compressedX + 1) * 2) - 1;
		this.fullY = ((compressedY + 1) * 2) - 1;
	}

	public MazeMapTile(int compressedX, int compressedY, boolean visited) {
		this(compressedX, compressedY);
		this.visited = visited;
	}

	public int getCompX() {
		return compressedX;
	}

	public int getCompY() {
		return compressedY;
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

	public int getRegionID() {
		return regionID;
	}

	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}

	public int getFullX() {
		return fullX;
	}

	public int getFullY() {
		return fullY;
	}

}
