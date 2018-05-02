package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

/**
 * Abstract Base class for specific entity populators
 * @author wolfiewaffle
 *
 */
public abstract class Populator {

	protected int[][] tileArray;
	protected ArrayList<Entity> entities;

	public Populator(int[][] tileArray, ArrayList<Entity> entities) {
		this.tileArray = tileArray;
		this.entities = entities;
	}

	/**
	 * The abstract method that is used to generate features.
	 */
	public abstract void populate();

	/**
	 * Gets the ID of the tile at the specified location.
	 * 
	 * @param point
	 *            The x and y point of the tile to check.
	 * @return The ID of the tile.
	 */
	protected int getTile(Point point) {
		return tileArray[point.x][point.y];
	}

	/**
	 * Gets a location for a random tile that is a floor tile.
	 * 
	 * @return An int[][] containing x and y of the tile, or null if no free
	 *         space can be found.
	 */
	protected int[] getRandomFreeSpace() {
		int tries = 1000;

		for (int i = 0; i < tries; i++) {
			int x = (int) (Math.random() * tileArray.length);
			int y = (int) (Math.random() * tileArray[0].length);

			if (tileArray[x][y] == Settings.FLOOR_ID || tileArray[x][y] == Settings.COBBLES1_ID
					|| tileArray[x][y] == Settings.COBBLES2_ID || tileArray[x][y] == Settings.COBBLES3_ID) {
				if (noEntitiesHere(x, y)) {
					return new int[] { x, y };
				}
			}
		}

		return null;
	}

	/**
	 * Called to determine that no entities exist in this location.
	 * 
	 * @param x
	 * @param y
	 * @return If an no entities occupy this location.
	 */
	private boolean noEntitiesHere(int x, int y) {
		for (Entity e : entities) {
			if ((e.getLocation()[1] / Settings.TILESIZE) - 1 == x) {
				if ((e.getLocation()[0] / Settings.TILESIZE) - 1 == y) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Returns the count of neighbors of a specific tile. For example you can
	 * get how many of the 8 surrounding tiles are ID 1.
	 * 
	 * @param point
	 *            The point to count the surrounding tiles of.
	 * @param testForTile
	 *            The tile ID you want to count.
	 * @param outOfBoundsCounts
	 *            If out of bounds locations count towards the total.
	 * @return An integer of how many surrounding tiles of the specified ID.
	 */
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
	 * @param point The point you are checking.
	 * @param routeTile The number that corresponds to the path or non solid
	 *            tile.
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

		// Replace non solid tiles with route
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == Settings.FLOOR_ID || tiles[i] == Settings.DOOR_ID || tiles[i] == Settings.COBBLES1_ID
					|| tiles[i] == Settings.COBBLES2_ID || tiles[i] == Settings.COBBLES3_ID) {
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
