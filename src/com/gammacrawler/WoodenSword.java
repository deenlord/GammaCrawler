package com.gammacrawler;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class WoodenSword extends Weapon {
	FadeTransition ft;
	public WoodenSword(String name, Sprite sprite) {
		super(name, sprite);
		super.setDamage(5);
		super.setMaxDamage(10);
		this.ft = new FadeTransition(Duration.millis(250), this.sprite.getSprite());
	}

	public WoodenSword(String name, Sprite sprite, int regDamage, int maxDamage) {
		super(name, sprite, regDamage, maxDamage);
	}
	
	public void swing() {
		
	    this.ft.setFromValue(1.0);
	    this.ft.setToValue(0);
	    this.ft.setCycleCount(1);
	    this.ft.setAutoReverse(true);
	    this.ft.play();
	}

}
