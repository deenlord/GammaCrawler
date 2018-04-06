package com.gammacrawler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	Image img;
	ImageView imgView;
	
	public Sprite(String filename, int tileSize) {
		this.img = new Image(filename, tileSize, tileSize, false, false);
		this.imgView = new ImageView(this.img);
	}
	
	public ImageView getSprite() {
		return this.imgView;
	}
}
