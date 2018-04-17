package com.gammacrawler.entity;

/**
 * @author deenlord
 *
 */
public class Slime extends Enemy {
	
	private static final String name = "Slime";
	protected static final Sprite sprite = new Sprite("file:src/com/gammacrawler/images/slime.png");
	private static final int damage = 5;
	
	/** Creates an Enemy Slime
	 * @param name = "Slime"
	 * @param damage = 5
	 * @param hp = 10
	 * @param xp = 5
	 */
	public Slime() {
		super(name, sprite);
		this.setHP(10);
		this.setXP(5);
	}
	
	/**
	 * @return the damage the slime deals
	 */
	public static int getDamage() {
		return damage;
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		System.out.println("Slime IS COLLIDING WITH " + e.getClass().getSimpleName());
	}

}
