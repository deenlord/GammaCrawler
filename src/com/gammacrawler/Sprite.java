package com.gammacrawler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author deenlord
 *
 */
public class Sprite {
	Image img;
	ImageView imgView;
	
	/**
	 * @param filename - path to file
	 */
	public Sprite(String filename) {
		this.img = new Image(filename, Settings.TILESIZE, Settings.TILESIZE, false, false);
		this.imgView = new ImageView(this.img);
	}
	
	
	/**
	 * @param filename - path to file
	 * @param tileSize - for custom sized sprites
	 */
	public Sprite(String filename, int tileSize) {
		this.img = new Image(filename, tileSize, tileSize, false, false);
		this.imgView = new ImageView(this.img);
	}
	
	/**
	 * @return - an ImageView of the file used to create this Sprite
	 */
	public ImageView getSprite() {
		return this.imgView;
	}
}
