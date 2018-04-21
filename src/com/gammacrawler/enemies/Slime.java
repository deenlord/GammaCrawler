package com.gammacrawler.enemies;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Sprite;

/**
 * @author deenlord
 *
 */
public class Slime extends Enemy {
	
	private static final String name = "Slime";

	
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
		super(name, new Sprite("file:src/com/gammacrawler/images/slime.png"), 5);
		this.setMaxHP(15);
		this.setHP(15);
		this.setXP(5);
	}
//	
//	@Override 
//	public void move(Direction dir) {
//		// TODO : add enemy movement ai here
//	}


}
