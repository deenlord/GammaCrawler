package com.gammacrawler;

import java.util.ArrayList;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.User;
import com.gammacrawler.generator.Board;
import com.gammacrawler.generator.populators.Populator;
import com.gammacrawler.generator.populators.PopulatorChests;
import com.gammacrawler.generator.populators.PopulatorCobbles;
import com.gammacrawler.generator.populators.PopulatorEnemies;
import com.gammacrawler.generator.populators.PopulatorGoldCoin;
import com.gammacrawler.generator.populators.PopulatorGraveyard;
import com.gammacrawler.generator.populators.PopulatorSkulls;
import com.gammacrawler.generator.populators.PopulatorStair;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/** This class is used for procedural generation.
 * @author deenlord, crathke4, wolfiewaffle
 *
 */
public class Generator {
	public static User player;
	Board board;
	public static int[][] ar;
	ArrayList<Enemy> enemies;
	ArrayList<Entity> gameEntities;
	StatusBar status;
	InventoryBar invBar;
	Image wall;
	Image floor;
	Image stair;
	Image door;
	Image skull;
	Image cobbles1;
	Image cobbles2;
	Image cobbles3;
	Image stones1;
	Image stones2;
	Image stones3;

	/**
	 * Creates a generator
	 */
	public Generator() {
		this(new User("Richard"));
	}

	/**
	 * Creates a generator
	 * @param player - player who's scores and inventory will be passed
	 */
	public Generator(User player) {
		int xp = player.getXP();

		if (xp < 100)
			makeGenerator(player, 15, 15, 12);
		else if(xp > 100 && xp < 500)
			makeGenerator(player, 21, 25, 16);
		else
			makeGenerator(player, 21, 31, 20);
	}

	/**
	 * Creates a generator
	 * @param player - player who's scores and inventory will be passed
	 * @param width - width of the board
	 * @param height - height of the board
	 * @param roomMaxSize - Maximum size of individual rooms in the board
	 */
	private void makeGenerator(User player, int width, int height, int roomMaxSize) {
		Generator.player = player;
		this.board = new Board(width, height, roomMaxSize);
		int modifier = player.getXP() +1;
		Generator.ar = this.board.getArray();
		this.enemies = new ArrayList<>();
		this.gameEntities = new ArrayList<>();
		this.setPlayerInitialLocation();

		// Run all the populators, to populate the dungeon with stuff.
		populate(new PopulatorGraveyard(this.board.getArray(), gameEntities));
		populate(new PopulatorStair(this.board.getArray(), gameEntities));
		populate(new PopulatorSkulls(this.board.getArray(), gameEntities, 1.0));
		populate(new PopulatorGoldCoin(this.board.getArray(), gameEntities, 1));
		if (modifier > 10) {
			populate(new PopulatorEnemies(this.board.getArray(), gameEntities, modifier / 10));
			populate(new PopulatorChests(this.board.getArray(), gameEntities, modifier / 100));
		}
		else {
			populate(new PopulatorEnemies(this.board.getArray(), gameEntities, 10));
			populate(new PopulatorChests(this.board.getArray(), gameEntities, 1));
		}
		populate(new PopulatorCobbles(this.board.getArray(), gameEntities));
		
		
		// Show user their health, experience, gold, etc...
		this.status = new StatusBar(this, 20, 672);
		this.invBar = new InventoryBar();
	}
	
	/**
	 * Getter for this.player
	 * @return this.player
	 */
	public User getPlayer() {
		return Generator.player;
	}
	
	/**
	 * Getter for this.board
	 * @return this.board
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Getter for this.status
	 * @return this.status
	 */
	public StatusBar getStatus() {
		return this.status;
	}
	 /**
	  * Updates the inventory bar 
	  */
	public void updateInventoryBar() {
		this.invBar.update();
	}

	/**
	 * Creates a canvas representation of the board
	 * @return the board canvas
	 */
	public Canvas getDungeon() {
		setupImages();

		// to use as coordinates
		double x;
		double y;

		// create the Canvas (feel free to resize)
		Canvas cv = new Canvas(Settings.TILESIZE * (ar[0].length + 1), Settings.TILESIZE * (ar.length + 1));
		GraphicsContext gc = cv.getGraphicsContext2D();

		// iterate through the array and draw the appropriate sprite
		// based on 1 or 0 found at index
		for (int i = 0; i < (ar.length); i++) {
			for (int j = 0; j < ar[i].length; j++) {
				// for each j set x and y
				y = (i + 1) * Settings.TILESIZE; // plus one to avoid dividing by zero
				x = (j + 1) * Settings.TILESIZE;

				if (ar[i][j] == Settings.FLOOR_ID) {
					// draw floor tile where you find a 0 in the array
					gc.drawImage(floor, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.WALL_ID) {
					// draw wall tile where you find a 1 in the array
					gc.drawImage(wall, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.DOOR_ID) {
					gc.drawImage(door, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.COBBLES1_ID) {
					gc.drawImage(cobbles1, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.COBBLES2_ID) {
					gc.drawImage(cobbles2, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.COBBLES3_ID) {
					gc.drawImage(cobbles3, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.SKULL_ID) {
					gc.drawImage(skull, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.STONES1_ID) {
					gc.drawImage(stones1, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.STONES2_ID) {
					gc.drawImage(stones2, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.STONES3_ID) {
					gc.drawImage(stones3, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == Settings.STAIR_ID) {
					gc.drawImage(stair, x, y, Settings.TILESIZE, Settings.TILESIZE);
				}
			}
		}

		return cv;
	}

	
	/** 
	 * Sets up non entity sprites.
	 */
	private void setupImages() {
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

	/** Populates the board based on a populator
	 * @param p - populator who's function will be used
	 */
	public void populate(Populator p) {
		p.populate();
	}

	/**
	 * Sets the initial location of the player in the first open tile
	 */
	private void setPlayerInitialLocation() {
		int counter = 0;
		// 
		for (int z = 0; z < (ar.length); z++) {
			for (int j = 0; j < (ar[z].length); j++) {
				if (ar[z][j] == 0 & counter == 0) {
					int y = (z + 1) * Settings.TILESIZE; // avoid z/0
					int x = (j + 1) * Settings.TILESIZE;
					player.setLocation(x, y);
					player.getImageView().setLayoutX(x);
					player.getImageView().setLayoutY(y);
					gameEntities.add(player);
					counter++;
				}

				if (counter > 0) {
					break;
				}
			}

			if (counter > 0) {
				break;
			}
		}
	}

	/**
	 * Checks every entity against every other entity to determine if they are colliding
	 */
	public void handleCollisions() {

		// This checks each enemy against every other, once.
		for (int i = 0; i < gameEntities.size() - 1; i++) {
			for (int j = i + 1; j < gameEntities.size(); j++) {
				boolean areColliding = false;
				Entity entity1 = gameEntities.get(i);
				Entity entity2 = gameEntities.get(j);

				// Check if these entities are colliding.
				if (entity1.getLocation()[0] == entity2.getLocation()[0]) {
					if (entity1.getLocation()[1] == entity2.getLocation()[1]) {
						areColliding = true;
					}					
				}

				// If the entities occupy the same space we collide them.
				if (areColliding) {
					entity1.collide(entity2);
					entity2.collide(entity1);
				}
			}			
		}
	}

	/**
	 * Sets the player to a given value
	 * @param player - Value to which player will be set
	 */
	public void setPlayer(User player) {
		Generator.player = player;
	}

}
