package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.entity.Enemy;
import com.gammacrawler.util.Point;

public abstract class Populator {

	protected int[][] tileArray;
	protected ArrayList<Enemy> enemies;

	public Populator(int[][] tileArray, ArrayList<Enemy> enemies) {
		this.tileArray = tileArray;
		this.enemies = enemies;
	}

	public abstract void populate();

	protected int getTile(Point point) {
		return tileArray[point.x][point.y];
	}

	// TODO: Make this work
	protected Enemy getEnemy(Point point) {
//		for (Enemy e : enemies) {
//			if (e.getLocation()) {
//				
//			}
//		}
		return null;
	}

	protected int[] getRandomFreeSpace() {
		int tries = 1000;

		for (int i = 0; i < tries; i++) {
			int x = (int) (Math.random() * tileArray.length);
			int y = (int) (Math.random() * tileArray[0].length);

			System.out.println("Attempting " + x + " " + y);
			System.out.println("BLOCK: " + tileArray[x][y]);
			if (tileArray[x][y] == 0) {
				System.out.println("MAKING POINT " + x + " " + y);
				return new int[]{x, y};
			}
		}

		return null;
	}

	protected int getOctNeighborCount(Point point, int testForTile, boolean outOfBoundsCounts) {
		int count = 0;

		for (int x = point.x - 1; x < point.x + 2; x++) {
			for (int y = point.y - 1; y < point.y + 2; y++) {

				// Check the tile
				if (!(x == point.x && y == point.y)) {
					if (x < 0 || x >= tileArray.length || y < 0 || y >= tileArray[0].length) {
						count += (outOfBoundsCounts ? 1 : 0);
					} else if (tileArray[x][y] == testForTile) {
						count++;
					}
				}
			}
		}

		return count;
	}

	/**
	 * This checks if placing a solid thing in this tile will block off any
	 * routes. Always check for this if you are making a solid tile populator.
	 * 
	 * @param array The array of integers/tiles.
	 * @param point The point you are checking.
	 * @param routeTile The number that corresponds to the path or non solid tile.
	 * @return If placing a tile here would block off routes.
	 */
	protected boolean doesBlockRoute(Point point, int routeTile) {
		int borders = 0;

		// First we "unwrap" the 8 surrounding tiles into a linear sequence
		int[] tiles = new int[] { getTile(point.direction(Direction.NORTH).direction(Direction.EAST)),
				getTile(point.direction(Direction.NORTH)),
				getTile(point.direction(Direction.NORTH).direction(Direction.WEST)),
				getTile(point.direction(Direction.WEST)),
				getTile(point.direction(Direction.SOUTH).direction(Direction.WEST)),
				getTile(point.direction(Direction.SOUTH)),
				getTile(point.direction(Direction.SOUTH).direction(Direction.EAST)),
				getTile(point.direction(Direction.EAST)) };

		// Replace doors with route
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == 2) {
				tiles[i] = routeTile;
			}
		}

		// Then we check for the changes from a path to not a path tile
		for (int i = 1; i < tiles.length; i++) {
			if (tiles[i] == routeTile) {
				if (tiles[i - 1] != routeTile) {
					borders++;
				}
			}
			if (tiles[i] != routeTile) {
				if (tiles[i - 1] == routeTile) {
					borders++;
				}
			}
			if (tiles[0] == routeTile && tiles[tiles.length - 1] != routeTile) {
				borders++;
			}
			if (tiles[0] != routeTile && tiles[tiles.length - 1] == routeTile) {
				borders++;
			}
		}

		// If there are more than 2 changes, there is more than one region of
		// path, which will be cut off by placing a solid thing here.
		return (borders > 2);
	}

}
