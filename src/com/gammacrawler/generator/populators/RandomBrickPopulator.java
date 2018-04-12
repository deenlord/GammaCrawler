package com.gammacrawler.generator.populators;

public class RandomBrickPopulator extends Populator {

	@Override
	public void populate(int[][] array) {
		for (int i = 0; i < 100; i++) {
			Point p = getRandomFreeSpace(array);
			System.out.println(p);
			if (getOctNeighborCount(array, p, 1) > 1) {
				array[p.x][p.y] = 1;
			}
		}
	}

}
