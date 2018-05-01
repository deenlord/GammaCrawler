package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.entity.Chest;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.item.FightPotion;
import com.gammacrawler.item.GoldPotion;
import com.gammacrawler.item.Chalice;
import com.gammacrawler.item.GhostPotion;
import com.gammacrawler.item.HealthPotion;
import com.gammacrawler.item.IncreaseMaxHPPotion;
import com.gammacrawler.item.XPPotion;

/**
 * <h3>PopulatorChests - A Chest Populator</h3>
 * <p> populates chests throughout the map
 * @author deenlord
 *
 */
public class PopulatorChests extends Populator {
	private int chests = 1;

	/**
	 * @param tileArray - array representing the board
	 * @param entities - ArrayList of entities
	 * @param chests - number of chests to place
	 */
	public PopulatorChests(int[][] tileArray, ArrayList<Entity> entities, int chests) {
		super(tileArray, entities);
		this.chests = chests;
	}

	/**
	 * @param tileArray - array representing the board
	 * @param entities - ArrayList of entities
	 */
	public PopulatorChests(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		// creates a new chest, finds a free space on the board, fills the chest with items, and places it on the free space.

		for (int i = 0; i < chests; i++) {
			Chest chest = new Chest();
			int[] p = this.getRandomFreeSpace();
			
			int rand = (int) (Math.random() * 100);
			System.out.println("Chest " + rand);
			if (rand <= 10) {
				chest.addTo(new Chalice());
				chest.addTo(new HealthPotion());
			} else if (rand <= 20) {
				chest.addTo(new IncreaseMaxHPPotion());	
			} else if (rand <= 30) {
				chest.addTo(new GhostPotion());
				chest.addTo(new XPPotion());
			}
			else if (rand <= 40) {
				chest.addTo(new GhostPotion());
				chest.addTo(new HealthPotion());
				chest.addTo(new XPPotion());
			}
			else if (rand <= 50) {
				chest.addTo(new IncreaseMaxHPPotion());
			}
			else if (rand <= 60) {
				chest.addTo(new GoldPotion());
				chest.addTo(new HealthPotion());
			}
			else if (rand <=70) {
				chest.addTo(new FightPotion());
				chest.addTo(new XPPotion());
				chest.addTo(new GoldPotion());
			}
			else
				chest.addTo(new HealthPotion());
			if (p != null) {
				chest.moveToTile(p[1] + 1, p[0] + 1);
			}
	
			entities.add(chest);
		}
	}

}
