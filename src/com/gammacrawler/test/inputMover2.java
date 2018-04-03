package com.gammacrawler.test;

import java.util.Scanner;

import com.gammacrawler.Direction;
import com.gammacrawler.User;
/**
 * 
 * @author crathke4
 *
 */
public class inputMover2 
{
	User user=new User("Richard");//(Goes by Dick)
	
	/**
	 * 
	 * @return Direction based on WSAD input from console.
	 */
	public Direction DirectionIn()
	{
		Scanner scn=new Scanner(System.in);
		System.out.println("Standard WSAD controls: ");
		String input=scn.next().toLowerCase();
		Direction move=null;
		switch(input)
		{
		case "w": 
			move=Direction.NORTH;
			break;
		case "s": 
			move=Direction.SOUTH;
			break;
		case "a": 
			move=Direction.WEST;
			break;
		case "d": 
			move=Direction.EAST;
			break;
		default:
			move=DirectionIn();
			break;
		}
		return move;
	}
}
