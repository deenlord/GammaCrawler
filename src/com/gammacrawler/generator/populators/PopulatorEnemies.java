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
		//for (int i = 0; i < 1; i++) {
			System.out.println("CALLING");
			int[] p = getRandomFreeSpace();
			System.out.println("POINT " + p[0] + " " + p[1]);
			ogre = new Ogre();
			ogre.moveToTile(2, 3);
			System.out.println("OGRE IS AT " + ogre.getImageView().getX() + " " + ogre.getImageView().getY());
			entities.add(ogre);
		//}
	}

}
