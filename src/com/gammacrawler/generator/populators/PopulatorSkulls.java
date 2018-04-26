package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

public class PopulatorSkulls extends Populator {
	private int attempts;

	public PopulatorSkulls(int[][] tileArray, ArrayList<Entity> entities) {
		this(tileArray, entities, 1);
	}

	public PopulatorSkulls(int[][] tileArray, ArrayList<Entity> entities, double multiplier) {
		super(tileArray, entities);
		attempts = (tileArray.length * tileArray[0].length) / 20;
		attempts *= multiplier;
	}

	@Override
	public void populate() {
		for (int i = 0; i < attempts; i++) {
			int[] n = getRandomFreeSpace();
			Point p = new Point(n[0], n[1]);
			if (!doesBlockRoute(p, Settings.FLOOR_ID) && p != null) {
				tileArray[p.x][p.y] = Settings.SKULL_ID;
			}
		}
	}

}
