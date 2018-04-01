package com.gammacrawler.generator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.gammacrawler.generator.map.MazeMap;
import com.gammacrawler.generator.map.MazeMapTile;
import com.gammacrawler.generator.math.DunMath;

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
		this.array = new int[width][height];
		regionArray = new int[array.length][array[0].length];
		fillIntegerArray(array, 1);
		fillIntegerArray(regionArray, 0);
	}

	public int[][] getArray() {
		return array;
	}

	public void addMaze() {
		attemptPlaceRooms(500, 3, 20, 3, 20);

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

		// Make any even numbers odd.
		minRoomWidth = (minRoomWidth % 2 == 0 ? minRoomWidth + 1 : minRoomWidth);
		maxRoomWidth = (maxRoomWidth % 2 == 0 ? maxRoomWidth + 1 : maxRoomWidth);
		minRoomHeight = (minRoomHeight % 2 == 0 ? minRoomHeight + 1 : minRoomHeight);
		maxRoomHeight = (maxRoomHeight % 2 == 0 ? maxRoomHeight + 1 : maxRoomHeight);

		for (int i = 0; i < attempts; i++) {
			thisRoomWidth = DunMath.randomOdd(minRoomWidth, maxRoomWidth);
			thisRoomHeight = DunMath.randomOdd(minRoomHeight, maxRoomHeight);

			// Account for room width
			xPointMax = array.length - thisRoomWidth - 1;
			yPointMax = array[0].length - thisRoomHeight - 1;

			xPoint = DunMath.randomOdd(xPointMin, xPointMax);
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
				if (array[x][y] == 0 || octNeighborCount(0, x, y) > 0)
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

	private int octNeighborCount(int num, int x, int y) {
		int count = 0;

		for (int cx = x - 1; cx < x + 3; cx++) {
			for (int cy = y - 1; cy < y + 3; cy++) {

				// If out of bounds count as a neighbor
				if (cx < 0 || cx >= array.length || cy < 0 || cy >= array[0].length) {
					count++;
				} else if (array[cx][cy] == num) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Returns if the tiles in a group do NOT all have the same region ID.
	 * Ignores solid blocks.
	 * 
	 * @param tiles
	 *            An ArrayList of tiles to check for different region IDs.
	 * @return If the tiles do NOT have the same region ID, or false if < 2
	 *         tiles.
	 */
	private boolean areAllTilesSameRegion(ArrayList<MazeMapTile> tiles) {
		if (tiles.size() < 2) {
			return false;
		}

		int firstRegionID = tiles.get(0).getRegionID();

		for (int x = 1; x < tiles.size(); x++) {
			if (tiles.get(x).getRegionID() != 0 && tiles.get(x).getRegionID() != firstRegionID) {
				return false;
			}
		}

		return true;
	}

	private Color blendRegion(int x, int y) {
		int c1 = 0;
		int c2 = 0;

		if (c1 == 0 && regionArray[x - 1][y] != 0)
			c1 = regionArray[x - 1][y];
		if (c1 == 0 && regionArray[x - 1][y] != 0)
			c1 = regionArray[x + 1][y];
		if (c1 == 0 && regionArray[x][y + 1] != 0)
			c1 = regionArray[x][y + 1];
		if (c1 == 0 && regionArray[x][y - 1] != 0)
			c1 = regionArray[x][y - 1];

		if (c2 == 0 && regionArray[x][y - 1] != 0 && regionArray[x][y - 1] != c1)
			c2 = regionArray[x][y - 1];
		if (c2 == 0 && regionArray[x][y + 1] != 0 && regionArray[x][y - 1] != c1)
			c2 = regionArray[x][y + 1];
		if (c2 == 0 && regionArray[x - 1][y] != 0 && regionArray[x][y - 1] != c1)
			c2 = regionArray[x + 1][y];
		if (c2 == 0 && regionArray[x - 1][y] != 0 && regionArray[x][y - 1] != c1)
			c2 = regionArray[x - 1][y];

		Color color1 = new Color(Color.HSBtoRGB(c1 * (1.0f / regionCounter), 0.8f, 0.8f));
		Color color2 = new Color(Color.HSBtoRGB(c2 * (1.0f / regionCounter), 0.8f, 0.8f));

		return (c1 < c2 ? color1 : color2);
	}

	public void paint(Graphics g) {
		paintArray(g, 800 / Math.max(array.length, array[0].length));
	}

	public void paintArray(Graphics g, int tileSize) {
		float doorHue = ((int) (Math.random() * regionCounter) + 1) * (1.0f / regionCounter);

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				int xPos = x * tileSize;
				int yPos = y * tileSize;

				float hue = x * (1.0f / array.length) + (System.nanoTime() % 10) / 10;
				Color c = new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
				g.setColor(c);

				g.fillRect(xPos, yPos, tileSize, tileSize);

				int b1 = 0;
				if (array[x][y] == 0) {
					hue = regionArray[x][y] * (1.0f / regionCounter);
					c = new Color(Color.HSBtoRGB(hue, 0.8f, 0.8f));
					g.setColor(c);
					// g.fillRect(xPos + b1, yPos + b1, tileSize - b1 * 2,
					// tileSize - b1 * 2);
				} else if (array[x][y] == 2) {
					b1 = tileSize / 4;
					int b2 = tileSize / 8;
					// doorHue = ((int) (Math.random() * regionCounter) + 1) *
					// (1.0f / regionCounter);
					c = new Color(Color.HSBtoRGB(doorHue, 0.8f, 0.8f));
					g.setColor(blendRegion(x, y));
					// g.fillRect(xPos, yPos, tileSize, tileSize);
					// g.setColor(Color.black);
					// g.fillRect(xPos + b2, yPos + b2, tileSize - b2 * 2,
					// tileSize - b2 * 2);
					// g.setColor(blendRegion(x, y));
					// g.fillRect(xPos + b1, yPos + b1, tileSize - b1 * 2,
					// tileSize - b1 * 2);

				}
				// g.fillRect(xPos + b1, yPos + b1, tileSize - b1 * 2, tileSize
				// - b1 * 2);

				g.setColor(Color.black);
				if (array[x][y] == 2) {
					g.fillRect(xPos, yPos, tileSize, tileSize);
					g.setColor(Color.white);
					if (array[x - 1][y] == 1 || array[x + 1][y] == 1) {
						g.fillRect(xPos, yPos + (tileSize / 2) - 2, tileSize, 4);
						// g.fillRect(xPos, yPos, tileSize, (tileSize / 2) - 2);
						// g.fillRect(xPos, yPos + (tileSize / 2) + 2, tileSize,
						// (tileSize / 2) - 2);
					} else {
						g.fillRect(xPos + (tileSize / 2) - 2, yPos, 4, tileSize);
						// g.fillRect(xPos, yPos, (tileSize / 2) - 2, tileSize);
						// g.fillRect(xPos + (tileSize / 2) + 2, yPos, (tileSize
						// / 2) - 2, tileSize);
					}
				} else if (array[x][y] == 0) {
					g.fillRect(xPos, yPos, tileSize, tileSize);
				}
			}
		}

		// mazeMap.paint(g, tileSize);
	}

}
