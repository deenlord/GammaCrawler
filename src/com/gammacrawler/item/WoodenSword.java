package com.gammacrawler.item;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class WoodenSword extends Weapon {
	FadeTransition ft;
	private static final String name = "Wooden Sword";
	protected static Sprite sprite = new Sprite("file:src/com/gammacrawler/images/woodensword.png", Settings.TILESIZE/2);
	
	public WoodenSword() {
		super(name, sprite);
		super.setDamage(5);
		super.setMaxDamage(10);
		this.ft = new FadeTransition(Duration.millis(250), sprite.getImageView());
		}

	public WoodenSword(String name, Sprite sprite, int regDamage, int maxDamage) {
		super(name, sprite, regDamage, maxDamage);
	}
	
	@Override
	public void animate() {
		
	    this.ft.setFromValue(1.0);
	    this.ft.setToValue(0);
	    this.ft.setCycleCount(1);
	    this.ft.setAutoReverse(true);
	    this.ft.play();
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub
		System.out.println("WoodenSword IS COLLIDING WITH " + e.getClass().getSimpleName());
		setDoingDamage(false);
	}

}