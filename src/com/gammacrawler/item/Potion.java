package com.gammacrawler.item;

import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * <h3>Potion - An Item</h3>
 *  <p> Abstract base class for all Potions
 *  @author deenlord
 */
public abstract class Potion extends Item {
	int value;
	public Potion(String name, Sprite sprite, int val) {
		super(sprite, name);
		this.value = val;
	}	
	
}
