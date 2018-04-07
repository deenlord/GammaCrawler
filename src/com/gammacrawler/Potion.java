package com.gammacrawler;

public abstract class Potion extends Item {
	private int effect;
	
	public Potion(String name, Sprite sprite) {
		super(name, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public Potion(String name, Sprite sprite, int effect) {
		super(name, sprite);
		this.effect = effect;
	}
	
	public int getEffect() {
		return this.effect;
	}
	
	public void setEffect(int eff) {
		this.effect = eff;
	}
	
	
}
