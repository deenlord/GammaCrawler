package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.generator.Board;

public class EnemyAI {
/**
 * @author crathke4
 */
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
	 * if the player is in the same room as the enemy, the enemy reduces the distance to player until it either hits 0 or goes above the size of the room
	 */
	public void chase()
	{
		
	}
	
	/**
	 * moves enemy randomly until player is in same room as enemy
	 * @param e - Enemy which to move
	 */
	public void walk(Enemy e)
	{
		
		if (counter == 3) {
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
			System.out.println(dir);
			System.out.println(">"+enemy.getLocation()[0]+", v"+enemy.getLocation()[1]);
			counter =  0;
		}
		counter ++;
	}
	
	/**
	 * if player leaves the room, enemy returns to walk
	 */
	public void returnToSpawn()
	{
		
	}
}
