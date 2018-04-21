package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.enemies.Ogre;
import com.gammacrawler.enemies.Slime;
import com.gammacrawler.entity.Entity;

public class PopulatorEnemies extends Populator {

	public PopulatorEnemies(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		Ogre ogre;
		Slime slime;
		int[] empt;
		for (int i = 0; i <= 4; i++) {
			empt = getRandomFreeSpace();
			ogre = new Ogre();
			ogre.moveToTile(empt[1] + 1, empt[0] + 1); // TODO: Fix entity coordinates
			empt = getRandomFreeSpace();
			slime = new Slime();
			slime.moveToTile(empt[1] + 1, empt[0] + 1);
			entities.add(ogre);
			entities.add(slime);
			
		}
	}

}
