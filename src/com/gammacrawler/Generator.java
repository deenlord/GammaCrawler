package com.gammacrawler;

import java.util.ArrayList;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.entity.User;
import com.gammacrawler.generator.Board;
import com.gammacrawler.generator.populators.Populator;
import com.gammacrawler.generator.populators.PopulatorCobbles;
import com.gammacrawler.generator.populators.PopulatorEnemies;
import com.gammacrawler.generator.populators.PopulatorGoldCoin;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author deenlord, crathke4, wolfiewaffle
 *
 */
public class Generator {
	User player;
	Board board;
	int[][] ar;
	ArrayList<Enemy> enemies;
	ArrayList<Entity> gameEntities;
	StatusBar status;
	Image wall;
	Image floor;
	Image door;
	Image skull;
	Image cobbles1;
	Image cobbles2;
	Image cobbles3;

	public Generator() {
		this.player = new User("Richard");
		this.board = new Board(21,21);
		
		this.ar = this.board.getArray();
		this.enemies = new ArrayList<>();
		this.gameEntities = new ArrayList<>();
		//this.createEnemies();
		this.setPlayerInitialLocation();
		//populate(new PopulatorSkulls(this.board.getArray(), enemies));
		populate(new PopulatorEnemies(this.board.getArray(), gameEntities));
		populate(new PopulatorCobbles(this.board.getArray(), gameEntities));
		populate(new PopulatorGoldCoin(this.board.getArray(), gameEntities));
		
		this.status = new StatusBar(this, 20, 672);

	}
	
	public Generator(Sprite userSprite) {
		new Generator();
		this.player.setSprite(userSprite);

	}
	
	public Generator(User player) {
		this.player = player;
		if (player.getXP() < 100)
			this.board = new Board(21, 21);
		else if(player.getXP() > 100 && player.getXP() < 500)
			this.board = new Board(45,51);
		else
			this.board = new Board(55,55);
		
		this.enemies = new ArrayList<>();
		//this.createEnemies();
		this.setPlayerInitialLocation();

	}
	
	public User getPlayer() {
		return this.player;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public StatusBar getStatus() {
		return this.status;
	}
	
	/**
	 * @return ArrayList of enemies based on the player's xp.
	 */
	// THIS IS APPERENTLY DEPRECATED
//	public ArrayList<Enemy> createEnemies() {
////		if (this.player.getXP() < 100) {
////			for (int i = 0; i <= 4; i++) {
//				Enemy em = new EnemySlime();
//				System.out.println("enemyslime created");
//				int[] loc = this.board.getFreePosition();
//				System.out.println("found empty location at " + loc[0] + " " + loc[1]);
//				System.out.println("TEST");
//				em.setLocation(loc[0], loc[1]);
//				System.out.println("set enemy location");
//				
//				//this.enemies.add(em);
//				gameEntities.add(em);
//				
//				System.out.println("adding enemy to javafx scene");
//				em.getImageView().setX(loc[0]);
//				em.getImageView().setY(loc[1]);
//				System.out.println("enemy added");
//				
////				Enemy ogre = new Ogre(null);
////				int[] ogreloc = this.board.getFreePosition();
////				ogre.setLocation(ogreloc[0], ogreloc[1]);
////				this.enemies.add(ogre);
////				ogre.getImageView().setX(ogreloc[0]);
////				ogre.getImageView().setY(ogreloc[1] - Settings.TILESIZE);
////			}
////		}
//		return this.enemies;
//	}

	public Canvas getDungeon() {
		setupImages();

		// to use as coordinates
		double x;
		double y;

		// create the Canvas (feel free to resize)
		Canvas cv = new Canvas(Settings.SCENEWIDTH, Settings.SCENEHEIGHT);
		GraphicsContext gc = cv.getGraphicsContext2D();

		// iterate through the array and draw the appropriate sprite
		// based on 1 or 0 found at index
		for (int i = 0; i < (ar.length); i++) {
			for (int j = 0; j < ar[i].length; j++) {
				// for each j set x and y
				y = (i + 1) * Settings.TILESIZE; // plus one to avoid dividing by zero
				x = (j + 1) * Settings.TILESIZE;

				if (ar[i][j] == 0) {
					// draw floor tile where you find a 0 in the array
					gc.drawImage(floor, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 99) {
					// draw wall tile where you find a 1 in the array
					gc.drawImage(wall, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 2) {
					gc.drawImage(door, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 3) {
					gc.drawImage(cobbles1, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 4) {
					gc.drawImage(cobbles2, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 5) {
					gc.drawImage(cobbles3, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 10) {
					gc.drawImage(skull, x, y, Settings.TILESIZE, Settings.TILESIZE);
				}
			}
		}

		return cv;
	}

	private void setupImages() {

		// Import images to use as tiles
		wall = new Image("file:src/com/gammacrawler/images/wall.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		floor = new Image("file:src/com/gammacrawler/images/floor.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		door = new Image("file:src/com/gammacrawler/images/door.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		skull = new Image("file:src/com/gammacrawler/images/skull.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		cobbles1 = new Image("file:src/com/gammacrawler/images/cobbles1.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		cobbles2 = new Image("file:src/com/gammacrawler/images/cobbles2.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		cobbles3 = new Image("file:src/com/gammacrawler/images/cobbles3.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
	}

	public void populate(Populator p) {
		p.populate();
	}

	private void setPlayerInitialLocation() {
		int counter = 0;
		// iterate through the array to find the first zero location,
		// draw the User there. ... only once.
		for (int z = 0; z < (ar.length); z++) {
			for (int j = 0; j < (ar[z].length); j++) {
				if (ar[z][j] == 0 & counter == 0) {
					int y = (z + 1) * Settings.TILESIZE; // avoid z/0
					int x = (j + 1) * Settings.TILESIZE;
					player.setLocation(x, y);
					player.getImageView().setLayoutX(x);
					player.getImageView().setLayoutY(y);
					//gameEntities.add(player);
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
		for (int i = 0; i < enemies.size() - 1; i++) {
			for (int j = i + 1; j < enemies.size(); j++) {
				boolean areColliding = false;
				Enemy enemy1 = enemies.get(i);
				Enemy enemy2 = enemies.get(j);

				// Check if these entities are colliding.
				if (enemy1.getLocation()[0] == enemy2.getLocation()[0]) {
					if (enemy1.getLocation()[1] == enemy2.getLocation()[1]) {
						areColliding = true;
					}					
				}

				// If the entities occupy the same space we collide them.
				if (areColliding) {
					enemy1.collide(enemy2);
					enemy1.collide(enemy1);
				}
			}			
		}
	}

}
