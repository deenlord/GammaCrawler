package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

public class PopulatorGraveyard extends Populator {

	public PopulatorGraveyard(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		for (int i = 0; i < 30; i++) {
			int[] n = getRandomFreeSpace();
			Point p = new Point(n[0], n[1]);
			if (!doesBlockRoute(p, Settings.FLOOR_ID) && p != null) {
				tileArray[p.x][p.y] = Settings.STONES1_ID;
			}
		}
	}

}
