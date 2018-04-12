package com.gammacrawler.generator.populators;

import com.gammacrawler.Point;

public abstract class Populator {

	public abstract void populate(int[][] array);

	protected Point getRandomFreeSpace(int[][] array) {
		int tries = 1000;

		for (int i = 0; i < tries; i++) {
			int x = (int) (Math.random() * array.length);
			int y = (int) (Math.random() * array[0].length);

			if (array[x][y] == 0) {
				return new Point(x, y);
			}
		}

		return null;
	}

	// TODO: Test for nulls
	protected int getOctNeighborCount(int[][] array, Point point, int testForTile) {
		int count = 0;

		for (int x = point.x - 1; x < point.x + 1; x++) {
			for (int y = point.y - 1; x < point.y + 1; y++) {

				if (array[x][y] == testForTile) {
					count++;
				}
			}
		}

		return count;
	}

}
