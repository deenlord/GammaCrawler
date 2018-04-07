package com.gammacrawler;

import java.util.ArrayList;

public abstract class Enemy extends Character implements Moveable {
	// TODO: These javadocs
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
	 * @author crathke4
	 * 4/7
	 */
	User player;
	
	/**
	 * @param main - main from which player is derived
	 */
	Enemy(Main main)
	{
		this.player=main.player;
	}
	
	/**
	 * if attack is successful, player loses HP
	 */
	public void attack()
	{
		boolean successful=true;
		if(successful) {
			player.setHP(getHP()-(int)(Math.random()*player.getMaxHP())/10);  //TODO: the math here probably isn't entirely right, 
																			  //should take a reasonable but random amount of health away from user
		}
	}
	

	@Override
	/**
	 * @return - True if dead, in which players xp is increased
	 */
	public boolean isDead()
	{
		boolean dead;
		if( getHP() > 0 ) {
			dead = false;
		}
		else {
			dead = true;
			player.setExp(player.getExp()+5);	//TODO: again this is an arbitrary number and should be changed later
		}

		return dead;
	}

}
