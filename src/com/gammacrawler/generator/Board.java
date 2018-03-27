package com.gammacrawler.generator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import com.gammacrawler.generator.math.DunMath;

public class Board {
	private int[][] array;
	ArrayList<Point> connectors = new ArrayList<>();
	private int regionCounter = 1;
	private int[][] regionArray;
	MazeMap mazeMap;

	public Board(int width, int height) {
		this.array = new int[width][height];
		regionArray = new int[array.length][array[0].length];
		fillIntegerArray(array, 1);
		fillIntegerArray(regionArray, 0);
		attemptPlaceRooms(10, 3, 20, 3, 20);
		mazeMap = new MazeMap(array);
		addMaze();
		fillRegions();
	}

	private void fillIntegerArray(int[][] array, int fillInt) {
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				array[x][y] = fillInt;
			}
		}
	}

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
		System.out.println("HI");

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
	public void attemptPlaceRooms(int attempts, int minRoomWidth, int maxRoomWidth, int minRoomHeight,
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

	private int octNeighborCount(int num, int x, int y) {
		int count = 0;

		for (int cx = x - 1; cx < x + 2; cx++) {
			for (int cy = y - 1; cy < y + 2; cy++) {

				// If out of bounds count as a neighbor
				if (cx < 0 || cx >= array.length) {
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

	public void addMaze() {
		mazeMap.makeMaze();
		mazeMap.clearArray(array);
	}

//	/**
//	 * Returns all the tiles around the tile at (x, y)
//	 * 
//	 * @param x
//	 *            The x coord of the tile
//	 * @param y
//	 *            The y coord of the tile
//	 * @return ArrayList of MazeMapTile length 0-4
//	 */
//	private ArrayList<MazeMapTile> getAdjacentTiles(int x, int y) {
//		ArrayList<MazeMapTile> adjacent = new ArrayList<>();
//
//		if (x > 0) {
//			adjacent.add(tileArray[x - 1][y]);
//		}
//		if (x < tileArray.length - 1) {
//			adjacent.add(tileArray[x + 1][y]);
//		}
//		if (y > 0) {
//			adjacent.add(tileArray[x][y - 1]);
//		}
//		if (y < tileArray[0].length - 1) {
//			adjacent.add(tileArray[x][y + 1]);
//		}
//
//		return adjacent;
//	}

	public void paint(Graphics g) {
		paintArray(g, 800 / array.length, array);		
	}

	public void paintArray(Graphics g, int tileSize, int[][] array) {
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				int xPos = x * tileSize;
				int yPos = y * tileSize;
				g.setColor((array[x][y] == 0) ? Color.white : Color.black);
				g.fillRect(xPos, yPos, tileSize, tileSize);

				float hue = regionArray[x][y] * (1.0f / regionCounter);
				System.out.println("G " + regionArray[x][y]);
				Color c = new Color(Color.HSBtoRGB(hue, 0.8f, 0.8f));
				System.out.println("HUE " + hue);
				g.setColor(c);
				int b = 2;
				g.fillRect(xPos + b, yPos + b, tileSize - b * 2, tileSize - b * 2);
			}
		}
		
		//mazeMap.paint(g, tileSize);
	}

}
