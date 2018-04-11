package com.gammacrawler;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * @author deenlord
 *
 */
public class Sprite {
	Image img;
	ImageView imgView;
	
	/**
	 * @param pathtofile - path to image file
	 */
	public Sprite(String pathtofile) {
		this.img = new Image(pathtofile, Settings.TILESIZE, Settings.TILESIZE, false, false);
		this.imgView = new ImageView(this.img);
		this.imgView.setRotationAxis(Rotate.Y_AXIS);
	}
	
	/**
	 * @param pathtofile - path to image file
	 * @param half - if true sets image tile size to Settings.HALFTILESIZE
	 */
	public Sprite(String pathtofile, boolean half) {
		if (half == true)  {
			this.img = new Image(pathtofile, Settings.HALFTILESIZE, Settings.HALFTILESIZE, false, false);
		}
		else {
			this.img = new Image(pathtofile, Settings.TILESIZE, Settings.TILESIZE, false, false);
		}
		
		this.imgView = new ImageView(this.img);
		this.imgView.setRotationAxis(Rotate.Y_AXIS);
	}
	
	
	/**
	 * @param pathtofile - path to file
	 * @param tileSize - for custom sized sprites
	 */
	public Sprite(String pathtofile, int tileSize) {
		this.img = new Image(pathtofile, tileSize, tileSize, false, false);
		this.imgView = new ImageView(this.img);
		this.imgView.setRotationAxis(Rotate.Y_AXIS);
	}
	
	/**
	 * @return - an ImageView of the file used to create this Sprite
	 */
	public ImageView getSprite() {
		return this.imgView;
	}
	
	public void rotate(Direction dir) {
		switch (dir) {
		case WEST:
			this.imgView.setRotate(180);
			break;
		case EAST:
			this.imgView.setRotate(0);
			break;
		default:
			break;
		}
	}
}
