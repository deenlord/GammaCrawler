package com.gammacrawler.generator.populators;

import java.util.ArrayList;

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
		//for (int i = 0; i < 1; i++) {
			System.out.println("CALLING");
			int[] p = getRandomFreeSpace();
			System.out.println("POINT " + p[0] + " " + p[1]);
			ogre = new Ogre();
			ogre.moveToTile(p[0], p[1]);
			enemies.add(ogre);
		//}
	}

}
