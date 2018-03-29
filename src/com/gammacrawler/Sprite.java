package com.gammacrawler;

public class Sprite {
	String name;
	
	public Sprite(int name) {
		if (name == 0) {
			this.name = "Sprite0";
		}
		
		else if (name == 1) {
			this.name = "Sprite1";
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(int nm) {
		if (nm == 0) {
			this.name = "Sprite0";
		}
		
		else if (nm == 1) {
			this.name = "Sprite1";
		}
	}
}
