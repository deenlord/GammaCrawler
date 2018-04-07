package com.gammacrawler;

public class Enemy extends Character implements Moveable{

	/**
	 * @author crathke4
	 * 4/7
	 */
	User player;
	
	/**
	 * 
	 */
	public void attack()
	{
		boolean succesful=true;
		if(succesful) {
			player.setHP(getHP()-(int)(Math.random()*player.getMaxHP())/10);  //TODO: the math here probably isn't entirely right, 
																			  //should take a reasonable but random amount of health away from user
		}
	}
	
	@Override
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
