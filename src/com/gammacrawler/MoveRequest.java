package com.gammacrawler;

public class MoveRequest {
	public int x;
	public int y;
	public Enemy e;

	public MoveRequest(int x, int y, Enemy e) {
		this.x = x;
		this.y = y;
		this.e = e;
	}

}
