package com.gammacrawler;

/**
 * @author jakev
 *
 */
public abstract class Potion extends Item {
	int value;
	public Potion(String name, Sprite sprite, int val) {
		super(name, sprite);
		this.value = val;
	}	
	
	/**
	 * @param c - a Character, can be User or Enemy
	 * 
	 * Intended use: apply the Potion's value to the Character's applicable attribute, 
	 * and remove the item from their inventory.
	 */
	public abstract void drink(Character c);

}
