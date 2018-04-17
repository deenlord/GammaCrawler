package com.gammacrawler.entity;

/**
 * @author jakev
 *
 */
public abstract class Item extends Entity{

	private String name;
	protected Sprite sprite;
	private int value;

	/**
	 * @param name - name of the item
	 * @param sprite - Item's sprite
	 */
	public Item(Sprite sprite, String name) {
		super(sprite);
		this.name = name;
	}
	
	/**
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
	 * @return item name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return item Sprite
	 */
	public Sprite getSprite() {
		return this.sprite;
	}
	
	/**
	 * @param richard - will add the item to the player's inventory
	 */
	public void addToUser(User richard) {
			richard.getInventory().add(this);
	}
	
	/**
	 * @param en - will add item to an enemy's inventory
	 */
	public void addToEnemy(Enemy en) {
		en.getInventory().add(this);
	}
	
	
}