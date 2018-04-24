package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.enemies.Ogre;
import com.gammacrawler.enemies.Slime;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.item.RandomItem;

public class PopulatorEnemies extends Populator {
	private static int MaxItemsInEnemyInventory = 2;

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

			// Add random items
			int itemCount = (int) ((Math.random() * MaxItemsInEnemyInventory) + 1);
			for (int j = 0; j < itemCount; j++) {
				ogre.addToInventory(new RandomItem().getItem());
			}

			ogre.moveToTile(empt[1] + 1, empt[0] + 1); // TODO: Fix entity coordinates
			empt = getRandomFreeSpace();
			slime = new Slime();

			// Add random items
			itemCount = (int) ((Math.random() * MaxItemsInEnemyInventory) + 1);
			for (int j = 0; j < itemCount; j++) {
				slime.addToInventory(new RandomItem().getItem());
			}

			slime.moveToTile(empt[1] + 1, empt[0] + 1);
			entities.add(ogre);
			entities.add(slime);
			
		}
	}

}
