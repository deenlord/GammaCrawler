package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Chest;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.item.HealthPotion;
import com.gammacrawler.item.IncreaseMaxHPPotion;

public class PopulatorChests extends Populator {

	public PopulatorChests(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		// TODO Auto-generated method stub
		Chest chest = new Chest();
		HealthPotion hp = new HealthPotion();
		IncreaseMaxHPPotion incMax = new IncreaseMaxHPPotion();
		chest.addTo(hp);
		chest.addTo(incMax);
		int[] p = this.getRandomFreeSpace();
		chest.moveToTile(p[1] + 1, p[0] + 0);
		entities.add(chest);
		
	}

}
