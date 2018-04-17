package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Ogre;

public class PopulatorEnemies extends Populator {

	public PopulatorEnemies(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		Ogre ogre;
		for (int i = 0; i < 10; i++) {
			int[] p = getRandomFreeSpace();
			ogre = new Ogre();
			ogre.moveToTile(p[1] + 1, p[0] + 1); // TODO: Fix entity coordinates
			entities.add(ogre);
		}
	}

}
