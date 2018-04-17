package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

public class PopulatorCobbles extends Populator {

	int attempts;

	public PopulatorCobbles(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
		attempts = (int) ((tileArray.length * tileArray[0].length) / 6);
	}

	@Override
	public void populate() {
		for (int i = 0; i < attempts; i++) {
			int[] n = getRandomFreeSpace();
			if (n != null) {
				Point p = new Point(n[0], n[1]);
				tileArray[p.x][p.y] = Settings.COBBLES1_ID;
			}
		}
		for (int i = 0; i < attempts; i++) {
			int[] n = getRandomFreeSpace();
			if (n != null) {
				Point p = new Point(n[0], n[1]);
				tileArray[p.x][p.y] = Settings.COBBLES2_ID;
			}
		}
		for (int i = 0; i < attempts; i++) {
			int[] n = getRandomFreeSpace();
			if (n != null) {
				Point p = new Point(n[0], n[1]);
				tileArray[p.x][p.y] = Settings.COBBLES3_ID;
			}
		}
	}

}
