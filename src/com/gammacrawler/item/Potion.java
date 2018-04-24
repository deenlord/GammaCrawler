package com.gammacrawler.item;

import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * @author jakev
 *
 */
public abstract class Potion extends Item {
	int value;
	public Potion(String name, Sprite sprite, int val) {
		super(sprite, name);
		this.value = val;
	}	
	
//	/**
//	 * @param c - a Character, can be User or Enemy
//	 * 
//	 * Intended use: apply the Potion's value to the Character's applicable attribute, 
//	 * and remove the item from their inventory.
//	 */
//	public abstract void drink(Character c);

}
