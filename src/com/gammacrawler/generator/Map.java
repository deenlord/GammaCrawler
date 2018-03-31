package com.gammacrawler.generator;


public class Map {

	private static GamePanel panel;
	
	public Map() {
		this.panel = new GamePanel(51, 51);
	}
	
	public GamePanel getMap() {
		return this.panel;
	}
	
}
