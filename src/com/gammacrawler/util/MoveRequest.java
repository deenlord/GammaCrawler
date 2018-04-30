package com.gammacrawler.util;

import com.gammacrawler.entity.Enemy;

public class MoveRequest {
	public int x;
	public int y;
	public Enemy e;

	/**
	 * Sets the location and enemy for the move request
	 * @param x - x location requested
	 * @param y - y location requested
	 * @param e - enemy requesting to move
	 */
	public MoveRequest(int x, int y, Enemy e) {
		this.x = x;
		this.y = y;
		this.e = e;
	}

}
