package com.gammacrawler.entity;

public class EnemySlime extends Enemy {
	
	private static final String name = "Slime";
	protected static final Sprite sprite = new Sprite("file:src/com/gammacrawler/images/slime.png");
	private static final int damage = 5;
	
	public EnemySlime() {
		super(name, sprite);
		this.setHP(10);
		this.setXP(5);
	}
	
	public static int getDamage() {
		return damage;
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		
	}

}
