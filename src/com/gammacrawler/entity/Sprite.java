package com.gammacrawler.entity;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.StatusBar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * <h2>Container for Image and ImageView</h2>
 * @author deenlord
 *
 */
public class Sprite {
	Image img;
	ImageView imgView;
	
	/**
	 * <h3>Pass in a filepath to an Image, get a Sprite back with an ImageView </h3>
	 * @param pathtofile - String
	 */
	public Sprite(String pathtofile) {
		this.img = new Image(pathtofile, Settings.TILESIZE, Settings.TILESIZE, false, false);
		this.imgView = new ImageView(this.img);
	}
	
	/**
	 * <h3>Create a Sprite </h3>
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
	 * <h3>Create a Sprite</h3>
	 * @param pathtofile - path to file
	 * @param tileSize - for custom sized sprites
	 */
	public Sprite(String pathtofile, int tileSize) {
		this.img = new Image(pathtofile, tileSize, tileSize, false, false);
		this.imgView = new ImageView(this.img);
		
	}
	
	/**
	 * <h3>Get the ImageView of the file used to create this Sprite</h3>
	 * @return - an ImageView 
	 */
	public ImageView getImageView() {
		return this.imgView;
	}
	
	/** 
	 * <h3> Called to replace this.imgView </h3>
	 * @param iv - An ImageView to replace this.imgView
	 */
	public void setImageView(ImageView iv) {
		this.imgView = iv;
	}
	
	/**
	 * <h3>Called to rotate the Character's ImageView based on their direction </h3>
	 * of travel.
	 * @param dir - A Direction
	 */
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
	
	/**
	 * <h3> Rotate a user's weapon in the direction last travelled. </h3>
	 * @param dir - A Direction 
	 */
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

	public ImageView getNewImageView() {
		System.out.println(img);
		ImageView iv = new ImageView(img);
		return iv;
	}

}
