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

public class PopulatorChests extends Populator {
	private int chests = 1;

	public PopulatorChests(int[][] tileArray, ArrayList<Entity> entities, int chests) {
		super(tileArray, entities);
		this.chests = chests;
	}

	public PopulatorChests(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		// TODO Add more chest types based on Math.random()

		for (int i = 0; i < chests; i++) {
			Chest chest = new Chest();
			int[] p = this.getRandomFreeSpace();
			
			int rand = (int) Math.random() * 100;
			if (rand <= 3) {
				chest.addTo(new Chalice());
				chest.addTo(new GhostPotion());
			} else if (rand <= 9) {
				chest.addTo(new GhostPotion());
				chest.addTo(new IncreaseMaxHPPotion());	
			} else if (rand <= 10) {
				chest.addTo(new GhostPotion());
				chest.addTo(new HealthPotion());
				chest.addTo(new IncreaseMaxHPPotion());	
			}
			else if (rand <= 20) {
				chest.addTo(new GhostPotion());
				chest.addTo(new HealthPotion());
			}
			else if (rand <= 30) {
				chest.addTo(new GhostPotion());
				chest.addTo(new HealthPotion());
				chest.addTo(new IncreaseMaxHPPotion());
				chest.addTo(new XPPotion());
			}
			else if (rand <= 40) {
				chest.addTo(new GoldPotion());
				chest.addTo(new HealthPotion());
			}
			else if (rand <=50) {
				chest.addTo(new FightPotion());
				chest.addTo(new XPPotion());
				chest.addTo(new GoldPotion());
			}
			else
				chest.addTo(new GhostPotion());
				chest.addTo(new IncreaseMaxHPPotion());
	
			if (p != null) {
				chest.moveToTile(p[1] + 1, p[0] + 1);
			}
	
			entities.add(chest);
		}
	}

}
