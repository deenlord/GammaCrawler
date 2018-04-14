package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.util.MoveRequest;
/**
 * @author crathke4
 * 4/7
 */
public abstract class Enemy extends Character implements Moveable {
	// TODO: These javadocs
	
	public Enemy(String name, Sprite sprite) {
		super(name, sprite);
	}
	
	
	/**
	 * This gets this entity, and an x and y coordinate that it wants to move
	 * to. Used by Main to resolve move conflicts.
	 * @param possibilities
	 * @return
	 */
	public abstract MoveRequest getMoveRequest(ArrayList<MoveRequest> possibilities);

	public abstract ArrayList<MoveRequest> getMovePossibilities();

	/**
	 * This is what happens when a move request conflicts with another. Main
	 * will decide which enemy to prioritize and call this method on the other
	 * enemy.
	 * 
	 * @return
	 */
	public abstract MoveRequest handleMoveRequestFailed();


	
	/**
	 * if attack is successful, player loses HP
	 */
	public void attack(User p)
	{
		boolean successful=true;
		if(successful) {
			p.setHP(getHP()-(int)((Math.random()*10)/p.getMaxHP()));  //TODO: the math here probably isn't entirely right, 
																			  //should take a reasonable but random amount of health away from user
		}
	}

}
