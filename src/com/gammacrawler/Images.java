package com.gammacrawler;

import javafx.scene.image.Image;

public class Images {
	public static Image wall;
	public static Image floor;
	public static Image stair;
	public static Image door;
	public static Image skull;
	public static Image cobbles1;
	public static Image cobbles2;
	public static Image cobbles3;
	public static Image stones1;
	public static Image stones2;
	public static Image stones3;

	/** 
	 * Sets up non entity sprites.
	 */
	public static void setupImages() {
		wall = new Image("file:src/com/gammacrawler/images/wall.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		floor = new Image("file:src/com/gammacrawler/images/floor.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		stair= new Image("file:src/com/gammacrawler/images/stair.png", Settings.TILESIZE, Settings.TILESIZE,false,false);
		door = new Image("file:src/com/gammacrawler/images/door.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		skull = new Image("file:src/com/gammacrawler/images/skull.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		cobbles1 = new Image("file:src/com/gammacrawler/images/cobbles1.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		cobbles2 = new Image("file:src/com/gammacrawler/images/cobbles2.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		cobbles3 = new Image("file:src/com/gammacrawler/images/cobbles3.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		stones1 = new Image("file:src/com/gammacrawler/images/stones1.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		stones2 = new Image("file:src/com/gammacrawler/images/stones2.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		stones3 = new Image("file:src/com/gammacrawler/images/stones3.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
	}

}
