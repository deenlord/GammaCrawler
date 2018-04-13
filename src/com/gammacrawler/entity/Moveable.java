package com.gammacrawler.entity;

import com.gammacrawler.Direction;

/**
 * @author deenlord
 * 3/24/18
 */
public interface Moveable {
	/**
	 * @param d - Valid Directions: NORTH, SOUTH, EAST, WEST
	 */
	public void move(Direction d);
	
}
