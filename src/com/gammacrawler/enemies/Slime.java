package com.gammacrawler.enemies;

import com.gammacrawler.Direction;
import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public class Slime extends Enemy {
	
	private static final String name = "Slime";
	protected static final Sprite sprite = new Sprite("file:src/com/gammacrawler/images/slime.png");

	
	/** 
	 * <h3>Creates an Enemy Slime</h3>
	 * <p>
	 *  name = "Slime" <br>
	 *  damage = 5 <br>
	 *  hp = 10 <br>
	 *  xp = 5 <br>
	 */
	public Slime() {
		// damage = 5
		super(name, sprite, 5);
		this.setHP(10);
		this.setXP(5);
	}
	
	@Override 
	public void move(Direction dir) {
		// TODO : add enemy movement ai here
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		System.out.println("Slime IS COLLIDING WITH " + e.getClass().getSimpleName());
	}

}
