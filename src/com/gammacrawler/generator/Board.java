package com.gammacrawler.generator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {

	MazeMapTile[][] tileArray;
	ArrayList<MazeMapTile> tileList = new ArrayList<>();
	ArrayList<Point> connectors = new ArrayList<>();
	private int regionCounter = 1;
	private int[][] regionArray;

	public Board(int[][] array) {
		tileArray = new MazeMapTile[(array.length - 1) / 2][(array[0].length - 1) / 2];
		regionArray = new int[array.length][array[0].length];
		fillIntegerArray(regionArray, 0);
		intMapToCellMap(array);
	}

	private void fillIntegerArray(int[][] array, int fillInt) {
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				array[x][y] = 0;
			}
		}
	}

	/**
	 * Takes an int array and clears the tiles on it corresponding to the maze
	 * map.
	 * 
	 * @param array
	 *            The array to operate on.
	 */
	public void clearArray(int[][] array) {
		for (int x = 0; x < tileArray.length; x++) {
			for (int y = 0; y < tileArray[0].length; y++) {
				int nX = (x * 2) + 1;
				int nY = (y * 2) + 1;

				array[nX][nY] = 0;

				if (tileArray[x][y].hasLeft()) {
					array[nX - 1][nY] = 0;
					regionArray[nX - 1][nY] = tileArray[x][y].getRegionID();
				}
				if (tileArray[x][y].hasRight()) {
					array[nX + 1][nY] = 0;
					regionArray[nX + 1][nY] = tileArray[x][y].getRegionID();
				}
				if (tileArray[x][y].hasUp()) {
					array[nX][nY - 1] = 0;
					regionArray[nX][nY - 1] = tileArray[x][y].getRegionID();
				}
				if (tileArray[x][y].hasDown()) {
					array[nX][nY + 1] = 0;
					regionArray[nX][nY + 1] = tileArray[x][y].getRegionID();
				}
			}
		}
	}

	/**
	 * Takes an array of 0 or 1 integers and puts the odd ints in a new array.
	 * 
	 * @param array
	 */
	private void intMapToCellMap(int[][] array) {

		// Add all the odd cells, so that we can remove the even ones between
		// them.
		for (int x = 1; x < array.length; x += 2) {
			for (int y = 1; y < array[0].length; y += 2) {

				MazeMapTile c = new MazeMapTile((x - 1) / 2, (y - 1) / 2);

				// Process the tiles such that we don't place mazes on already
				// clear tiles.
				if (array[x][y] == 1) {
					tileList.add(c);
				} else {
					c.setVisited(true);
				}
				tileArray[(x - 1) / 2][(y - 1) / 2] = c;
			}
		}
	}

	/**
	 * 0: Left 1: Right 2: Up 3: Down
	 * 
	 * @param direction
	 *            The direction to check
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 * @return If this direction is valid
	 */
	private boolean thisDirectionValid(int direction, int x, int y) {
		boolean flag = true;

		if (direction == 0) {
			if (x < 1)
				return false;
			if (tileArray[x - 1][y].isVisited())
				return false;
		}

		if (direction == 1) {
			if (x > tileArray.length - 2)
				return false;
			if (tileArray[x + 1][y].isVisited())
				return false;
		}

		if (direction == 2) {
			if (y < 1)
				return false;
			if (tileArray[x][y - 1].isVisited())
				return false;
		}

		if (direction == 3) {
			if (y > tileArray[0].length - 2)
				return false;
			if (tileArray[x][y + 1].isVisited())
				return false;
		}

		return true;
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

	private ArrayList<MazeMapTile> getAdjacentTiles(int x, int y) {
		ArrayList<MazeMapTile> adjacent = new ArrayList<>();

		if (x > 0) {
			adjacent.add(tileArray[x - 1][y]);
		}
		if (x < tileArray.length - 1) {
			adjacent.add(tileArray[x + 1][y]);
		}
		if (y > 0) {
			adjacent.add(tileArray[x][y - 1]);
		}
		if (y < tileArray[0].length - 1) {
			adjacent.add(tileArray[x][y + 1]);
		}

		return adjacent;
	}

	public void makeMaze() {
		Iterator<MazeMapTile> itr = tileList.iterator();
	
		// This must use an Iterator because it will fail otherwise. This is
		// because we cannot access the implicit Iterator in the for each loop,
		// and it will fail if modified in ways other than with the Iterator's
		// methods.
		int iterations = 0;
		while (itr.hasNext()) {
			expandMaze(itr.next(), regionCounter);
			iterations++;
	
			// We want to remove tiles from the list as we get to them to
			// improve performance.
			// Having a list makes sure there are no islands of tiles not
			// reached by the algorithm.
			itr.remove();

			// We increment the region counter here so that each room and each maze
			// section is a separate region.
			regionCounter++;
		}
		System.out.println("ITERATIONS " + iterations);
	}

	/**
	 * A recursive method to create the Map, which is a randomized
	 * spanning-tree. Google it.
	 * 
	 * @param tile
	 *            The tile to operate on
	 * @param region
	 *            The ID of the region that this cell is part of
	 * @param itr 
	 */
	private void expandMaze(MazeMapTile tile, int region) {

		/** The direction we are checking, will be chosen randomly */
		int direction = 0;

		/** The directions we have checked on this tile */
		boolean[] dirs = new boolean[] { false, false, false, false };

		// We have visited this tile, so it is not a valid direction to carve
		// to.
		tile.setVisited(true);

		// Also set the region this tile is part of.
		regionArray[tile.getFullX()][tile.getFullY()] = regionCounter;
		System.out.println("Region " + tile.getFullX() + " " + tile.getFullY() + " " + regionCounter);

		while (!dirs[0] || !dirs[1] || !dirs[2] || !dirs[3]) {
			do {
				direction = (int) (Math.random() * 4);
			} while (dirs[direction] == true);
			dirs[direction] = true;

			// If this direction is valid, open the pathways and expand the maze
			// there.
			if (thisDirectionValid(direction, tile.getCompX(), tile.getCompY())) {
				MazeMapTile otherTile;
				switch (direction) {
				case 0:
					otherTile = tileArray[tile.getCompX() - 1][tile.getCompY()];
					tile.setHasLeft(true);
					otherTile.setHasRight(true);
					expandMaze(otherTile, region);
					break;
				case 1:
					otherTile = tileArray[tile.getCompX() + 1][tile.getCompY()];
					tile.setHasRight(true);
					otherTile.setHasLeft(true);
					expandMaze(otherTile, region);
					break;
				case 2:
					otherTile = tileArray[tile.getCompX()][tile.getCompY() - 1];
					tile.setHasUp(true);
					otherTile.setHasDown(true);
					expandMaze(otherTile, region);
					break;
				case 3:
					otherTile = tileArray[tile.getCompX()][tile.getCompY() + 1];
					tile.setHasDown(true);
					otherTile.setHasUp(true);
					expandMaze(otherTile, region);
					break;
				}
			}
		}
	}

	public void paintArray(Graphics g, int tileSize, int[][] array) {
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				int xPos = x * tileSize;
				int yPos = y * tileSize;
				g.setColor((array[x][y] == 0) ? Color.white : Color.red);
				g.fillRect(xPos, yPos, tileSize, tileSize);
				g.setColor(new Color(Color.HSBtoRGB(regionArray[x][y] * 10, 1, 0.9f)));
				g.fillRect(xPos + 2, yPos + 2, tileSize - 4, tileSize - 4);
			}
		}
	}

	public void paint(Graphics g, int tileSize) {
		for (int x = 0; x < tileArray.length; x++) {
			for (int y = 0; y < tileArray[0].length; y++) {
				int xPos = x * tileSize;
				int yPos = y * tileSize;
				int xPosH = xPos + (tileSize / 2);
				int yPosH = yPos + (tileSize / 2);
				MazeMapTile tile = tileArray[x][y];

				g.setColor(Color.BLACK);
				g.fillOval(xPosH - 4, yPosH - 4, 8, 8);

				if (!tile.hasLeft())
					g.setColor(Color.green);
				g.drawLine(xPosH, yPosH, xPosH - tileSize / 2, yPosH);
				g.setColor(Color.black);

				if (!tile.hasRight())
					g.setColor(Color.green);
				g.drawLine(xPosH, yPosH, xPosH + tileSize / 2, yPosH);
				g.setColor(Color.black);

				if (!tile.hasUp())
					g.setColor(Color.green);
				g.drawLine(xPosH, yPosH, xPosH, yPosH - tileSize / 2);
				g.setColor(Color.black);

				if (!tile.hasDown())
					g.setColor(Color.green);
				g.drawLine(xPosH, yPosH, xPosH, yPosH + tileSize / 2);
				g.setColor(Color.black);

				Color c = new Color(Color.HSBtoRGB(tile.getRegionID() * 10, 1, (float) 0.6));
				g.setColor(c);
				g.drawRect(xPos, yPos, tileSize, tileSize);
			}
		}
	}

}
