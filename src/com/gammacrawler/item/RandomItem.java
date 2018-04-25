package com.gammacrawler.item;

import com.gammacrawler.entity.Item;

public class RandomItem {
	private Item[] options;
	private Item item;

	public RandomItem() {

		// Add all the options we want to the array
		 options = new Item[]{
					new FightPotion(),
					new GoldPotion(),
					new HealthPotion(),
					new IncreaseMaxHPPotion(),
					new XPPotion(),
					new Chalice(),
					new GhostPotion()
			};

		// Choose a random item
		int index = (int) (Math.random() * options.length);
		item = options[index];
	}

	public Item getItem() {
		return item;
	}

}
