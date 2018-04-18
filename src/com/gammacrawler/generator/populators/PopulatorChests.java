package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.entity.Chest;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.item.HealthPotion;

public class PopulatorChests extends Populator {

	public PopulatorChests(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		// TODO Auto-generated method stub
		Chest chest = new Chest();
		HealthPotion hp = new HealthPotion();
		chest.addTo(hp);
		int[] p = this.getRandomFreeSpace();
		chest.getImageView().setLayoutX(p[0] + 1);
		chest.getImageView().setLayoutY(p[1] + 1);
		
		entities.add(chest);
		
	}

}
