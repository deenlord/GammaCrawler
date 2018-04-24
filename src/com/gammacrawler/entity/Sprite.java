package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.StatusBar;

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
	}
	
	/**
	 * @param pathtofile - path to image file
	 * @param half - if true sets image tile size to Settings.HALFTILESIZE
	 */
	public Sprite(String pathtofile, boolean half) {
		if (half == true)  {
			this.img = new Image(pathtofile, Settings.HALF_TILESIZE, Settings.HALF_TILESIZE, false, false);
		}
		else {
			this.img = new Image(pathtofile, Settings.TILESIZE, Settings.TILESIZE, false, false);
		}
		
		this.imgView = new ImageView(this.img);
	}
	
	
	/**
	 * @param pathtofile - path to file
	 * @param tileSize - for custom sized sprites
	 */
	public Sprite(String pathtofile, int tileSize) {
		this.img = new Image(pathtofile, tileSize, tileSize, false, false);
		this.imgView = new ImageView(this.img);
		
	}
	
	/**
	 * @return - an ImageView of the file used to create this Sprite
	 */
	public ImageView getImageView() {
		return this.imgView;
	}
	
	public void setImageView(ImageView iv) {
		this.imgView = iv;
	}
	
	public void rotateCharacter(Direction dir) {
		this.imgView.setRotationAxis(Rotate.Y_AXIS);
		switch (dir) {
		case WEST:
			this.imgView.setRotate(180);
			break;
		case EAST:
			this.imgView.setRotate(0);
			break;
		case NORTH:
			this.imgView.setRotate(0);
			break;
		case SOUTH:
			this.imgView.setRotate(180);
			break;
		}
	}
	
	public void rotateWeapon(Direction dir) {
		StatusBar.addStatus("Rotate Weapon called.");
		this.imgView.setRotationAxis(Rotate.Z_AXIS);
		//this.imgView.setTranslateZ(1);
		switch (dir) {
		case WEST:
//			 StatusBar.addStatus("Rotating Weapon West");
			this.imgView.setRotate(180);
			break;
		case EAST:
//			 StatusBar.addStatus("Rotating Weapon East");
			this.imgView.setRotate(0);
			break;
		case NORTH:
//			StatusBar.addStatus("Rotating Weapon North");
			this.imgView.setRotate(270);
			break;
		case SOUTH:
//			StatusBar.addStatus("Rotating Weapon South");
			this.imgView.setRotate(90);
			break;
		}
	}

}
