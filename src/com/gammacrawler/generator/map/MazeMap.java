package com.gammacrawler.generator.map;

import java.util.ArrayList;

public class MazeMap {
	private MazeMapVertex[][] tileArray;
	private ArrayList<MazeMapVertex> tileList = new ArrayList<>();

	public MazeMap(int[][] array) {
		tileArray = new MazeMapVertex[(array.length - 1) / 2][(array[0].length - 1) / 2];
		intMapToCellMap(array);
	}

	/**
	 * Takes an array of 0 or 1 integers and puts the odd ints in a new array.
	 * 
	 * @param array
	 *            The array to copy over.
	 */
	private void intMapToCellMap(int[][] array) {

		// Add all the odd cells, so that we can remove the even ones between
		// them.
		for (int x = 1; x < array.length; x += 2) {
			for (int y = 1; y < array[0].length; y += 2) {

				MazeMapVertex c = new MazeMapVertex((x - 1) / 2, (y - 1) / 2);

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
	 *            The direction to check.
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @return If this direction is valid.
	 */
	private boolean thisDirectionValid(int direction, int x, int y) {
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
	 * Goes though each tile and starts a maze there if it is unvisited.
	 */
	public void makeMaze() {
		if (tileList.size() > 0) {
			for (MazeMapVertex tile : tileList) {
				if (!tile.isVisited()) {
					expandMaze(tile);
				}
			}
		}
	}

	/**
	 * A recursive method to create the Map, which is a randomized.
	 * spanning-tree. Google it.
	 * 
	 * @param tile
	 *            The tile to operate on.
	 */
	private void expandMaze(MazeMapVertex tile) {

		/** The direction we are checking, will be chosen randomly */
		int direction = 0;

		/** The directions we have checked on this tile */
		boolean[] dirs = new boolean[] { false, false, false, false };

		// We have visited this tile, so it is not a valid direction to carve
		// to.
		tile.setVisited(true);

		while (!dirs[0] || !dirs[1] || !dirs[2] || !dirs[3]) {
			do {
				direction = (int) (Math.random() * 4);
			} while (dirs[direction] == true);
			dirs[direction] = true;

			// If this direction is valid, open the pathways and expand the maze
			// there.
			if (thisDirectionValid(direction, tile.getCompX(), tile.getCompY())) {
				MazeMapVertex otherTile;
				switch (direction) {
				case 0:
					otherTile = tileArray[tile.getCompX() - 1][tile.getCompY()];
					tile.setHasLeft(true);
					otherTile.setHasRight(true);
					expandMaze(otherTile);
					break;
				case 1:
					otherTile = tileArray[tile.getCompX() + 1][tile.getCompY()];
					tile.setHasRight(true);
					otherTile.setHasLeft(true);
					expandMaze(otherTile);
					break;
				case 2:
					otherTile = tileArray[tile.getCompX()][tile.getCompY() - 1];
					tile.setHasUp(true);
					otherTile.setHasDown(true);
					expandMaze(otherTile);
					break;
				case 3:
					otherTile = tileArray[tile.getCompX()][tile.getCompY() + 1];
					tile.setHasDown(true);
					otherTile.setHasUp(true);
					expandMaze(otherTile);
					break;
				}
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
	public void carveArray(int[][] array) {
		for (int x = 0; x < tileArray.length; x++) {
			for (int y = 0; y < tileArray[0].length; y++) {
				int nX = (x * 2) + 1;
				int nY = (y * 2) + 1;

				array[nX][nY] = 0;

				if (tileArray[x][y].hasLeft()) {
					array[nX - 1][nY] = 0;
				}
				if (tileArray[x][y].hasRight()) {
					array[nX + 1][nY] = 0;
				}
				if (tileArray[x][y].hasUp()) {
					array[nX][nY - 1] = 0;
				}
				if (tileArray[x][y].hasDown()) {
					array[nX][nY + 1] = 0;
				}
			}
		}
	}

}
