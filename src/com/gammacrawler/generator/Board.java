package com.gammacrawler.generator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Board {

	MazeMapTile[][] tiles;
	ArrayList<MazeMapTile> tileList = new ArrayList<>();

	public Board(int[][] array) {
		tiles = new MazeMapTile[(array.length - 1) / 2][(array[0].length - 1) / 2];
		intMapToCellMap(array);
	}

	public void makeMaze() {
		Iterator<MazeMapTile> itr = tileList.iterator();

		// This must use an Iterator because it will fail otherwise. This is
		// because we cannot access the implicit Iterator in the for each loop,
		// and it will fail if modified in ways other than with the Iterator's
		// methods.
		while (itr.hasNext()) {
			randomize(itr.next());

			// We want to remove tiles from the list as we get to them to
			// improve performance.
			// Having a list makes sure there are no islands of tiles not
			// reached by the algorithm.
			itr.remove();
		}
	}

	/**
	 * A recursive method to create the Map, which is a randomized
	 * spanning-tree. Google it.
	 * 
	 * @param tile
	 *            The tile to operate on
	 */
	private void randomize(MazeMapTile tile) {
		int direction = 0;
		boolean[] dirs = new boolean[] { false, false, false, false };
		tile.setVisited(true);

		while (!dirs[0] || !dirs[1] || !dirs[2] || !dirs[3]) {
			do {
				direction = (int) (Math.random() * 4);
			} while (dirs[direction] == true);
			dirs[direction] = true;

			// System.out.println("Choosing direction: " + direction);
			if (thisDirectionValid(direction, tile.getX(), tile.getY())) {
				MazeMapTile otherTile;
				switch (direction) {
				case 0:
					otherTile = tiles[tile.getX() - 1][tile.getY()];
					tile.setHasLeft(true);
					otherTile.setHasRight(true);
					randomize(otherTile);
					break;
				case 1:
					otherTile = tiles[tile.getX() + 1][tile.getY()];
					tile.setHasRight(true);
					otherTile.setHasLeft(true);
					randomize(otherTile);
					break;
				case 2:
					otherTile = tiles[tile.getX()][tile.getY() - 1];
					tile.setHasUp(true);
					otherTile.setHasDown(true);
					randomize(otherTile);
					break;
				case 3:
					otherTile = tiles[tile.getX()][tile.getY() + 1];
					tile.setHasDown(true);
					otherTile.setHasUp(true);
					randomize(otherTile);
					break;
				}
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
			if (tiles[x - 1][y].isVisited())
				return false;
		}

		if (direction == 1) {
			if (x > tiles.length - 2)
				return false;
			if (tiles[x + 1][y].isVisited())
				return false;
		}

		if (direction == 2) {
			if (y < 1)
				return false;
			if (tiles[x][y - 1].isVisited())
				return false;
		}

		if (direction == 3) {
			if (y > tiles[0].length - 2)
				return false;
			if (tiles[x][y + 1].isVisited())
				return false;
		}

		return true;
	}

	public void paint(Graphics g, int tileSize) {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				int xPos = x * tileSize;
				int yPos = y * tileSize;
				int xPosH = xPos + (tileSize / 2);
				int yPosH = yPos + (tileSize / 2);
				MazeMapTile tile = tiles[x][y];

				g.setColor(Color.BLACK);
				g.fillOval(xPosH - 4, yPosH - 4, 8, 8);

				if (!tile.hasLeft())// {
					g.setColor(Color.white);
				g.drawLine(xPosH, yPosH, xPosH - tileSize / 2, yPosH);
				g.setColor(Color.black);
				// }

				if (!tile.hasRight())// {
					g.setColor(Color.white);
				g.drawLine(xPosH, yPosH, xPosH + tileSize / 2, yPosH);
				g.setColor(Color.black);
				// }

				if (!tile.hasUp())// {
					g.setColor(Color.white);
				g.drawLine(xPosH, yPosH, xPosH, yPosH - tileSize / 2);
				g.setColor(Color.black);
				// }

				if (!tile.hasDown())// {
					g.setColor(Color.white);
				g.drawLine(xPosH, yPosH, xPosH, yPosH + tileSize / 2);
				g.setColor(Color.black);
				// }
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
				tiles[(x - 1) / 2][(y - 1) / 2] = c;
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
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[0].length; y++) {
				int nX = (x * 2) + 1;
				int nY = (y * 2) + 1;

				array[nX][nY] = 0;

				if (tiles[x][y].hasLeft())
					array[nX - 1][nY] = 0;
				if (tiles[x][y].hasRight())
					array[nX + 1][nY] = 0;
				if (tiles[x][y].hasUp())
					array[nX][nY - 1] = 0;
				if (tiles[x][y].hasDown())
					array[nX][nY + 1] = 0;
			}
		}
	}

}
