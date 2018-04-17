package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Ogre;

public class PopulatorEnemiesDebug extends Populator {

	public PopulatorEnemiesDebug(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		Ogre ogre;
		ogre = new Ogre();
		ogre.moveToTile(3, 3);
		entities.add(ogre);
	}

}
