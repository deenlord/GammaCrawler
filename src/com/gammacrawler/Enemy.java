package com.gammacrawler;

import java.util.ArrayList;

public abstract class Enemy extends Character {
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

}
