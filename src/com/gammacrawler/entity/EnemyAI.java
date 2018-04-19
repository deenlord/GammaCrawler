package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.generator.Board;

public class EnemyAI {

	Enemy enemy;
	User player;
	Board board;
	
	public void check(Enemy e)
	{
		this.enemy=e;
		switch(enemy.status)
		{
		case DOCILE:
			walk();
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
	 */
	public void walk()
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
		System.out.println(dir);
		System.out.println(">"+enemy.getLocation()[0]/32+", v"+enemy.getLocation()[1]/32);
	}
	
	/**
	 * if player leaves the room, enemy returns to walk
	 */
	public void returnToSpawn()
	{
		
	}
}
