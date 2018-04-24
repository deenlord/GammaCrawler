package com.gammacrawler.generator;

import java.util.ArrayList;
import java.util.HashMap;

import com.gammacrawler.Settings;
import com.gammacrawler.generator.map.MazeMap;
import com.gammacrawler.generator.map.connector.ConnectorBucket;
import com.gammacrawler.generator.map.connector.DungeonConnector;
import com.gammacrawler.generator.map.connector.DungeonConnectorMaker;
import com.gammacrawler.util.Point;

public class Board {
	ArrayList<ConnectorBucket> connectors;
	private int[][] tileArray;
	private int regionCounter = 1;
	private int[][] regionArray;
	MazeMap mazeMap;
	HashMap<Integer, ArrayList<DungeonConnector>> connectorMap = new HashMap<>();

	private int extraDoorChance = 100;
	private int sparseTries = 100000;

	public Board(int width, int height) {
		width = (width % 2 == 0 ? width + 1 : width);
		height = (height % 2 == 0 ? height + 1 : height);
		this.tileArray = new int[width][height];
		regionArray = new int[tileArray.length][tileArray[0].length];
		fillIntegerArray(tileArray, Settings.WALL_ID);
		fillIntegerArray(regionArray, Settings.FLOOR_ID);
		this.addMaze();
	}

	public int[][] getArray() {
		return tileArray;
	}

	/**
	 * Adds the Maze structure to the Board, including rooms and hallways.
	 */
	public void addMaze() {
		attemptPlaceRooms(500, 2, 20, 2, 24);

		mazeMap = new MazeMap(tileArray);
		mazeMap.makeMaze();
		mazeMap.carveArray(tileArray);

		fillRegions();
		connectors = ConnectorBucket.getSortedList(DungeonConnectorMaker.getConnectors(regionArray));
		carveConnectors();
		makeSparse();
	}

	/**
	 * This removes essentially dead ends to the maze, so that there are blocks
	 * of solid stone instead of every inch being opened up.
	 */
	private void makeSparse() {
		for (int i = 0; i < sparseTries; i++) {
			int x = (int) (Math.random() * tileArray.length);
			int y = (int) (Math.random() * tileArray[0].length);
			if (adjacentOpenCount(x, y) < 2) {
				tileArray[x][y] = Settings.WALL_ID;
				regionArray[x][y] = Settings.FLOOR_ID;
			}
		}
	}

	/**
	 * This takes all the connectors between regions and replaces them with
	 * doors.
	 */
	private void carveConnectors() {
		for (ConnectorBucket bucket : connectors) {

			// Open some of the connectors
			for (DungeonConnector c : bucket.getConnectors()) {
				int random = (int) (Math.random() * extraDoorChance) + 1;
				if (random == 1) {
					tileArray[c.getX()][c.getY()] = Settings.DOOR_ID;
					regionArray[c.getX()][c.getY()] = 1;
				}
			}

			// Make sure there is at least one connector
			if (bucket.getConnectors().size() > 0) {
				int random = (int) (Math.random() * bucket.getConnectors().size());
				DungeonConnector c = bucket.getConnectors().get(random);
				tileArray[c.getX()][c.getY()] = Settings.DOOR_ID;
				regionArray[c.getX()][c.getY()] = 1;
			}
		}
	}

	/**
	 * Fills an int[][] with a single value.
	 * 
	 * @param array
	 *            The array to fill.
	 * @param fillInt
	 *            The integer to fill with.
	 */
	private void fillIntegerArray(int[][] array, int fillInt) {
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				array[x][y] = fillInt;
			}
		}
	}

	/**
	 * Sets every section of connected tiles to a new region ID.
	 */
	private void fillRegions() {
		for (int x = 0; x < tileArray.length; x++) {
			for (int y = 0; y < tileArray[0].length; y++) {
				if (tileArray[x][y] == 0 && regionArray[x][y] == 0) {
					floodFillRegion(x, y, regionCounter);
					regionCounter++;
				}
			}
		}
	}

	/**
	 * Fills out floors with a region - this lets us assign each tile to a
	 * region so that we can connect all the regions.
	 * 
	 * @param x
	 * @param y
	 * @param regionID
	 */
	private void floodFillRegion(int x, int y, int regionID) {
		if (tileArray[x][y] == Settings.WALL_ID || regionArray[x][y] == regionID) {
			return;
		}

		regionArray[x][y] = regionCounter;

		if (x > 0) {
			floodFillRegion(x - 1, y, regionID);
		}
		if (x < tileArray.length - 1) {
			floodFillRegion(x + 1, y, regionID);
		}
		if (y > 0) {
			floodFillRegion(x, y - 1, regionID);
		}
		if (y < tileArray[0].length - 1) {
			floodFillRegion(x, y + 1, regionID);
		}
	}

	/**
	 * Attempt to place randomized white rooms. Size parameters will be made odd
	 * by adding 1 if they are even.
	 * 
	 * @param attempts The attempts that will be taken to create a room
	 * @param minRoomWidth The minimum width of a room
	 * @param maxRoomWidth The maximum width of a room
	 * @param minRoomHeight The minimum height of a room
	 * @param maxRoomHeight The minimum height of a room
	 */
	private void attemptPlaceRooms(int attempts, int minRoomWidth, int maxRoomWidth, int minRoomHeight,
			int maxRoomHeight) {
		int snipWidth = tileArray.length - 2;
		int snipHeight = tileArray[0].length - 2;
		int xPointMin = 1;
		int xPointMax;
		int yPointMin = 1;
		int yPointMax;
		int xPoint;
		int yPoint;
		int thisRoomWidth;
		int thisRoomHeight;

		// Make sure we don't have rooms bigger than the floor
		minRoomWidth = Math.min(snipWidth, minRoomWidth);
		minRoomHeight = Math.min(snipHeight, minRoomHeight);
		maxRoomWidth = Math.min(snipWidth, maxRoomWidth);
		maxRoomHeight = Math.min(snipHeight, maxRoomHeight);

		// Make any even numbers odd.
		minRoomWidth = (minRoomWidth % 2 == 0 ? minRoomWidth + 1 : minRoomWidth);
		maxRoomWidth = (maxRoomWidth % 2 == 0 ? maxRoomWidth - 1 : maxRoomWidth);
		minRoomHeight = (minRoomHeight % 2 == 0 ? minRoomHeight + 1 : minRoomHeight);
		maxRoomHeight = (maxRoomHeight % 2 == 0 ? maxRoomHeight - 1 : maxRoomHeight);

		for (int i = 0; i < attempts; i++) {
			thisRoomWidth = randomOdd(minRoomWidth, maxRoomWidth);
			thisRoomHeight = randomOdd(minRoomHeight, maxRoomHeight);

			// Account for room width
			xPointMax = Math.max(1, snipWidth - thisRoomWidth + 2);
			xPointMax = (xPointMax % 2 == 0 ? xPointMax - 1 : xPointMax);
			xPoint = randomOdd(xPointMin, xPointMax);

			yPointMax = Math.max(1, snipHeight - thisRoomHeight + 2);
			yPointMax = (yPointMax % 2 == 0 ? yPointMax - 1 : yPointMax);
			yPoint = randomOdd(yPointMin, yPointMax);

			makeRoom(xPoint, yPoint, thisRoomWidth, thisRoomHeight);
		}
	}

	/**
	 * Fills in a rectangle with empty space.
	 * 
	 * @param pointX
	 *            Rectangle x point.
	 * @param pointY
	 *            Rectangle y point.
	 * @param width
	 *            Rectangle width.
	 * @param height
	 *            Rectangle height.
	 */
	private void makeRoom(int pointX, int pointY, int width, int height) {

		// Check to make sure no intersection with existing open spaces
		if (isSpace(pointX, pointY, width, height)) {
			for (int x = pointX; x < pointX + width; x++) {
				for (int y = pointY; y < pointY + height; y++) {
					tileArray[x][y] = Settings.FLOOR_ID;
				}
			}
		}
	}

	private boolean isSpace(int pointX, int pointY, int width, int height) {
		for (int x = pointX; x < pointX + width; x++) {
			for (int y = pointY; y < pointY + height; y++) {
				if (tileArray[x][y] == 0)
					return false;
			}
		}
		return true;
	}

	private int adjacentOpenCount(int x, int y) {
		int count = 0;

		if (x > 0) {
			if (tileArray[x - 1][y] == 0 || tileArray[x - 1][y] == 2) {
				count++;
			}
		}
		if (x < tileArray.length - 1) {
			if (tileArray[x + 1][y] == 0 || tileArray[x + 1][y] == 2) {
				count++;
			}
		}
		if (y > 0) {
			if (tileArray[x][y - 1] == 0 || tileArray[x][y - 1] == 2) {
				count++;
			}
		}
		if (y < tileArray[0].length - 1) {
			if (tileArray[x][y + 1] == 0 || tileArray[x][y + 1] == 2) {
				count++;
			}
		}

		return count;
	}

	public int[] getFreePosition() {
		ArrayList<Point> points = new ArrayList<>();

		for (int x = 0; x < tileArray.length; x++) {
			for (int y = 0; y < tileArray[0].length; y++) {
				System.out.print(tileArray[x][y]);
				if (tileArray[x][y] == 0) {
					points.add(new Point(x, y));
					System.out.print("X ");
				} else {
					System.out.print("O ");
				}
			}
			System.out.println();
		}

		int index = (int) (Math.random() * points.size());

		System.out.println(points);
		return new int[] { points.get(index).x, points.get(index).y };
		// return new int[]{(points.get(index).x + 1) * Settings.TILESIZE,
		// (points.get(index).x + 2) * Settings.TILESIZE};
	}

	/**
	 * Returns a random odd number with a range
	 * @param min - lowest possible number
	 * @param max - highest possible number
	 * @return A random odd number with a range
	 */
	public static int randomOdd(int min, int max) {
		if (max % 2 == 0)
			max--;
		if (min % 2 == 0)
			min++;
		return min + 2 * (int) (Math.random() * ((max - min) / 2 + 1));
	}

}
