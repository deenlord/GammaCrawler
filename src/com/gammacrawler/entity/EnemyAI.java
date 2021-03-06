package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.generator.Board;

/**
 * <h3> EnemyAI </h3>
 * handles how the enemies move about the screen. 
 * @author crathke4
 */
public class EnemyAI {

	Enemy enemy;
	Board board;
	int counter = 0;
	/**
	 * Checks the status of the enemy, and calls a moving method based on the enemyStatus
	 * @param e - Enemy which to move
	 */
	public void check(Enemy e)
	{
		this.enemy=e;
		switch(enemy.status)
		{
		case DOCILE:
			walk(e);
			break;
		case HOSTILE:
			chase();
			break;
		case RETURNING:
			returnToSpawn();
			break;
		}
	}
	
	/**
	 * makes enemy chase player until player leaves the room in which enemy is
	 */
	public void chase()
	{
		//Method stub
	}
	
	/**
	 * moves enemy randomly until player is in same room as enemy
	 * @param e - Enemy which to move
	 */
	public void walk(Enemy e)
	{
		int directionInt=(int) (Math.random()*4)+1;
		Direction dir;
		switch(directionInt)
		{
			case 1:
				dir=Direction.NORTH;
				break;
			case 2:
				dir=Direction.SOUTH;
				break;
			case 3:
				dir=Direction.EAST;
				
				break;
			default: 
				dir=Direction.WEST;
				break;
		}
		enemy.move(dir);
		
	}
	
	/**
	 * if player leaves the room, enemy returns to walk
	 */
	public void returnToSpawn()
	{
		//method stub
	}
}
