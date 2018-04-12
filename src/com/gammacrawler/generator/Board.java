package com.gammacrawler.generator;

import java.util.ArrayList;
import java.util.HashMap;

import com.gammacrawler.Point;
import com.gammacrawler.Settings;
import com.gammacrawler.generator.map.MazeMap;
import com.gammacrawler.generator.map.connector.ConnectorBucket;
import com.gammacrawler.generator.map.connector.DungeonConnector;
import com.gammacrawler.generator.map.connector.DungeonConnectorMaker;
import com.gammacrawler.generator.math.DunMath;
import com.gammacrawler.generator.populators.Populator;
import com.gammacrawler.generator.populators.PopulatorSkulls;

public class Board {
	ArrayList<ConnectorBucket> connectors;
	private int[][] array;
	private int regionCounter = 1;
	private int[][] regionArray;
	MazeMap mazeMap;
	HashMap<Integer, ArrayList<DungeonConnector>> connectorMap = new HashMap<>();

	private int extraDoorChance = 100;
	private int sparseTries = 100000;

	public Board(int width, int height) {
		width = (width % 2 == 0 ? width + 1 : width);
		height = (height % 2 == 0 ? height + 1 : height);
		this.array = new int[width][height];
		regionArray = new int[array.length][array[0].length];
		fillIntegerArray(array, 1);
		fillIntegerArray(regionArray, 0);
		this.addMaze();
	}

	public int[][] getArray() {
		return array;
	}

	public void addMaze() {
		attemptPlaceRooms(500, 2, 20, 2, 20);

		mazeMap = new MazeMap(array);
		mazeMap.makeMaze();
		mazeMap.carveArray(array);

		fillRegions();
		connectors = ConnectorBucket.getSortedList(DungeonConnectorMaker.getConnectors(regionArray));
		carveConnectors();
		makeSparse();
	}

	private void makeSparse() {
		for (int i = 0; i < sparseTries; i++) {
			int x = (int) (Math.random() * array.length);
			int y = (int) (Math.random() * array[0].length);
			if (adjacentOpenCount(x, y) < 2) {
				array[x][y] = 1;
				regionArray[x][y] = 0;
			}
		}
	}

	private void carveConnectors() {
		for (ConnectorBucket bucket : connectors) {

			// Open some of the connectors
			for (DungeonConnector c : bucket.getConnectors()) {
				int random = (int) (Math.random() * extraDoorChance) + 1;
				if (random == 1) {
					array[c.getX()][c.getY()] = 2;
					regionArray[c.getX()][c.getY()] = 1;
				}
			}

			// Make sure there is at least one connector
			if (bucket.getConnectors().size() > 0) {
				int random = (int) (Math.random() * bucket.getConnectors().size());
				DungeonConnector c = bucket.getConnectors().get(random);
				array[c.getX()][c.getY()] = 2;
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
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				if (array[x][y] == 0 && regionArray[x][y] == 0) {
					floodFillRegion(x, y, regionCounter);
					regionCounter++;
				}
			}
		}
	}

	private void floodFillRegion(int x, int y, int regionID) {
		if (array[x][y] == 1 || regionArray[x][y] == regionID) {
			return;
		}

		regionArray[x][y] = regionCounter;

		if (x > 0) {
			floodFillRegion(x - 1, y, regionID);
		}
		if (x < array.length - 1) {
			floodFillRegion(x + 1, y, regionID);
		}
		if (y > 0) {
			floodFillRegion(x, y - 1, regionID);
		}
		if (y < array[0].length - 1) {
			floodFillRegion(x, y + 1, regionID);
		}
	}

	// TODO: This javadoc
	/**
	 * Attempt to place randomized white rooms. Size parameters will be made odd
	 * by adding 1 if they are even.
	 * 
	 * @param attempts
	 * @param minRoomWidth
	 * @param maxRoomWidth
	 * @param minRoomHeight
	 * @param maxRoomHeight
	 */
	private void attemptPlaceRooms(int attempts, int minRoomWidth, int maxRoomWidth, int minRoomHeight,
			int maxRoomHeight) {
		int snipWidth = array.length - 2;
		int snipHeight = array[0].length - 2;
		int xPointMin;
		int xPointMax;
		int yPointMin;
		int yPointMax;
		int xPoint;
		int yPoint;
		int thisRoomWidth;
		int thisRoomHeight;

		xPointMin = 1;
		yPointMin = 1;

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
			thisRoomWidth = DunMath.randomOdd(minRoomWidth, maxRoomWidth);
			thisRoomHeight = DunMath.randomOdd(minRoomHeight, maxRoomHeight);

			// Account for room width
			xPointMax = Math.max(1, snipWidth - thisRoomWidth + 2);
			xPointMax = (xPointMax % 2 == 0 ? xPointMax - 1 : xPointMax);
			xPoint = DunMath.randomOdd(xPointMin, xPointMax);

			yPointMax = Math.max(1, snipHeight - thisRoomHeight + 2);
			yPointMax = (yPointMax % 2 == 0 ? yPointMax - 1 : yPointMax);
			yPoint = DunMath.randomOdd(yPointMin, yPointMax);

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
					array[x][y] = 0;
				}
			}
		}
	}

	private boolean isSpace(int pointX, int pointY, int width, int height) {
		for (int x = pointX; x < pointX + width; x++) {
			for (int y = pointY; y < pointY + height; y++) {
				if (array[x][y] == 0)
					return false;
			}
		}
		return true;
	}

	private int adjacentOpenCount(int x, int y) {
		int count = 0;

		if (x > 0) {
			if (array[x - 1][y] == 0 || array[x - 1][y] == 2) {
				count++;
			}
		}
		if (x < array.length - 1) {
			if (array[x + 1][y] == 0 || array[x + 1][y] == 2) {
				count++;
			}
		}
		if (y > 0) {
			if (array[x][y - 1] == 0 || array[x][y - 1] == 2) {
				count++;
			}
		}
		if (y < array[0].length - 1) {
			if (array[x][y + 1] == 0 || array[x][y + 1] == 2) {
				count++;
			}
		}

		return count;
	}

	public int[] getFreePosition() {
		ArrayList<Point> points = new ArrayList<>();

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				System.out.print(array[x][y]);
				if (array[x][y] == 0) {
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
		return new int[]{points.get(index).x, points.get(index).y};
		//return new int[]{(points.get(index).x + 1) * Settings.TILESIZE, (points.get(index).x + 2) * Settings.TILESIZE};
	}

}
