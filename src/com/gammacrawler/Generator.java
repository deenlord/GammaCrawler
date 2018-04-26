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

	public Generator() {
		this(new User("Richard"));
	}

	public Generator(User player) {
		int xp = player.getXP();

		if (xp < 100)
			makeGenerator(player, 21, 21, 12);
		else if(xp > 100 && xp < 500)
			makeGenerator(player, 21, 25, 16);
		else
			makeGenerator(player, 25, 31, 20);
	}

	private void makeGenerator(User player, int width, int height, int roomMaxSize) {
		Generator.player = player;
		this.board = new Board(width, height, roomMaxSize);
		Generator.ar = this.board.getArray();
		this.enemies = new ArrayList<>();
		this.gameEntities = new ArrayList<>();
		this.setPlayerInitialLocation();

		// Run all the populators, to populate the dungeon with stuff.
		populate(new PopulatorStair(this.board.getArray(), gameEntities));
		populate(new PopulatorSkulls(this.board.getArray(), gameEntities, 30.0));
		populate(new PopulatorGoldCoin(this.board.getArray(), gameEntities, 1));
		populate(new PopulatorEnemies(this.board.getArray(), gameEntities, 8.0));
		populate(new PopulatorCobbles(this.board.getArray(), gameEntities));
		populate(new PopulatorChests(this.board.getArray(), gameEntities, 5));
		
		// Show user their health, experience, gold, etc...
		this.status = new StatusBar(this, 20, 672);
		this.invBar = new InventoryBar();
	}

	public User getPlayer() {
		return Generator.player;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public StatusBar getStatus() {
		return this.status;
	}
	
	public void updateInventoryBar() {
		this.invBar.update();
	}


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
				} else if (ar[i][j] == Settings.STAIR_ID) {
					gc.drawImage(stair, x, y, Settings.TILESIZE, Settings.TILESIZE);
				}
			}
		}

		return cv;
	}

	
	/** Setup our dungeon images, this is for non-interactive sprites.
	 * 
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
	}

	/** takes a Populator, then populates the populator
	 * @param p - populator to be populated
	 */
	public void populate(Populator p) {
		p.populate();
	}

	/**
	 * iterate through the array to find the first zero location (top left),
	 * draw the User there. ... only once.
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
	 * TODO: Javadoc
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

	public void setPlayer(User player) {
		Generator.player = player;
	}

}
