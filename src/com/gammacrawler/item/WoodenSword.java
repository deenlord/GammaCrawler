package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.entity.Sprite;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

/**
 * <h3>WoodenSword - A Weapon</h3>
 * <p> name = Wooden Sword
 * <br> damage = 5
 * <br> maxDamage = 10
 * @author deenlord
 */
public class WoodenSword extends Weapon {
	FadeTransition ft;
	private static final String name = "Wooden Sword";

	public WoodenSword() {
		super(name, new Sprite("file:src/com/gammacrawler/images/woodensword.png", Settings.HALF_TILESIZE));
		super.setDamage(5);
		super.setMaxDamage(10);
		this.ft = new FadeTransition(Duration.millis(250), sprite.getImageView());
	}

	public WoodenSword(String name, Sprite sprite, int regDamage, int maxDamage) {
		super(name, sprite, regDamage, maxDamage);
	}

	@Override
	public void animate() {
		// sets opacity from zero to 100 and back, once per click.
		this.ft.setFromValue(1.0);
		this.ft.setToValue(0);
		this.ft.setCycleCount(1);
		this.ft.setAutoReverse(true);
		this.ft.play();
	}

	// public Sprite getSprite() {
	// return this.sprite;
	// }

	@Override
	public void move(Direction d) {
		// TODO really don't need this.

	}

}
