package com.gammacrawler.entity;

import com.gammacrawler.Generator;

/**
 * @author jakev
 *
 */
public abstract class Item extends Entity{

	private String name;
	protected Sprite sprite;
	private int value;

	/**
	 * Creates an Item
	 * @param name - name of the item
	 * @param sprite - Item's sprite
	 */
	public Item(Sprite sprite, String name) {
		super(sprite);
		this.name = name;
	}
	
	/**
	 * Creates an Item
	 * @param name - name of item
	 * @param sprite - Item's sprite
	 * @param val - value of item, a modifier. HealthPotion val = 25, gives user 25 health.
	 */
	public Item(String name, Sprite sprite, int val) {
		super(sprite);
		this.name = name;
		this.value = val;
	}
	
	/**
	 * Called to get this.name
	 * @return item name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Called to get this.sprite
	 * @return item Sprite
	 */
	public Sprite getSprite() {
		return this.sprite;
	}
	
	/**
	 * Called to add this to User's inventory
	 * @param richard - will add the item to the player's inventory
	 */
	public void addToUser() {
			Generator.player.inventory.add(this);
	}
	
	/**
	 * @param en - will add item to an enemy's inventory
	 */
	public void addToEnemy(Enemy en) {
		en.getInventory().add(this);
	}
	
	
}