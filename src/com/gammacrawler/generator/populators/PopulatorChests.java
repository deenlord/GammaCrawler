package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Chest;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.GoldCoin;
import com.gammacrawler.item.HealthPotion;
import com.gammacrawler.item.IncreaseMaxHPPotion;
import com.gammacrawler.item.XPPotion;

public class PopulatorChests extends Populator {

	public PopulatorChests(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		// TODO Add more chest types based on Math.random()
		Chest chest = new Chest();
		int[] p = this.getRandomFreeSpace();
	
		int rand = (int) Math.random() * 100;
		if (rand <= 10) {
			chest.addTo(new HealthPotion());
			chest.addTo(new IncreaseMaxHPPotion());	
		}
		else if (rand <= 20) {
			chest.addTo(new HealthPotion());
		}
		else if (rand <= 30) {
			chest.addTo(new HealthPotion());
			chest.addTo(new IncreaseMaxHPPotion());
			chest.addTo(new XPPotion());
		}
		else
			chest.addTo(new IncreaseMaxHPPotion());
		
		chest.moveToTile(p[1] + 1, p[0] + 0);
		entities.add(chest);
	}

}
