package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Ogre;
import com.gammacrawler.util.Point;

public class PopulatorEnemies extends Populator {

	public PopulatorEnemies(int[][] tileArray, ArrayList<Enemy> enemies) {
		super(tileArray, enemies);
	}

	@Override
	public void populate() {
		Ogre ogre;
		for (int i = 0; i < 10; i++) {
			Point p = getRandomFreeSpace();
			ogre = new Ogre();
			ogre.setLocation(p.x * Settings.TILESIZE, p.y * Settings.TILESIZE);
			enemies.add(ogre);
		}
	}

}
