package com.gammacrawler.item;

import com.gammacrawler.entity.Item;

/**
 * <h3>RandomItem - A Random Item Generator</h3>
 *  <p> Will produce one of the following:
 *  <br> - Fight Potion
 *  <br> - Gold Potion
 *  <br> - Health Potion
 *  <br> - BoostHP Potion
 *  <br> - XP Potion
 *  <br> - Gold Chalice
 *  <br> - Ghost Potion
 *  
 *  @author wolfiewaffle
 */
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
