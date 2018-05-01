package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.enemies.Ogre;
import com.gammacrawler.enemies.Slime;
import com.gammacrawler.enemies.Witch;
import com.gammacrawler.enemies.ZombieNinja;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.item.RandomItem;

public class PopulatorEnemies extends Populator {
	private int maxItemsInEnemyInventory;
	private double multiplier;
	private int attempts = 1;

	public PopulatorEnemies(int[][] tileArray, ArrayList<Entity> entities, double multiplier) {
		this(tileArray, entities);
		this.multiplier = multiplier;
	}

	public PopulatorEnemies(int[][] tileArray, ArrayList<Entity> entities, int maxInventoryItems) {
		this(tileArray, entities);
		maxItemsInEnemyInventory = maxInventoryItems;
	}

	public PopulatorEnemies(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
		maxItemsInEnemyInventory = 1;
	}

	@Override
	public void populate() {
		Ogre ogre;
		Slime slime;
		Witch witch;
		ZombieNinja zombieninja;

		int[] empt;
		for (int i = 0; i <= (int) (attempts * multiplier); i++) {
			int itemCount;

			empt = getRandomFreeSpace();
			if (empt != null) {
				ogre = new Ogre();

				// Add random items
				itemCount = (int) (Math.random() * maxItemsInEnemyInventory) + 1;
				for (int j = 0; j < itemCount; j++) {
					ogre.addToInventory(new RandomItem().getItem());
				}

				// Move Ogre to random location
				ogre.moveToTile(empt[1] + 1, empt[0] + 1); // TODO: Fix entity
															// coordinates
				entities.add(ogre);
			}

			empt = getRandomFreeSpace();
			if (empt != null) {
				slime = new Slime();

				// Add random items
				itemCount = (int) (Math.random() * maxItemsInEnemyInventory) + 1;
				for (int j = 0; j < itemCount; j++) {
					slime.addToInventory(new RandomItem().getItem());
				}

				// Move Slime to random location
				slime.moveToTile(empt[1] + 1, empt[0] + 1);
				entities.add(slime);
			}

			if (empt != null) {
				empt = getRandomFreeSpace();
				witch = new Witch();

				// Add random items
				itemCount = (int) (Math.random() * maxItemsInEnemyInventory) + 1;
				for (int j = 0; j < itemCount; j++) {
					witch.addToInventory(new RandomItem().getItem());
				}

				witch.moveToTile(empt[1] + 1, empt[0] + 1);
				entities.add(witch);

			}

			empt = getRandomFreeSpace();
			if (empt != null) {
				zombieninja = new ZombieNinja();

				itemCount = (int) (Math.random() * maxItemsInEnemyInventory) + 1;
				for (int j = 0; j < itemCount; j++) {
					zombieninja.addToInventory(new RandomItem().getItem());
				}

				// Move Slime to random location
				zombieninja.moveToTile(empt[1] + 1, empt[0] + 1);
				entities.add(zombieninja);
			}
		}
	}

}
