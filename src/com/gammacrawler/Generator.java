package com.gammacrawler;

import java.util.ArrayList;

import com.gammacrawler.generator.Board;
import com.gammacrawler.generator.populators.Populator;
import com.gammacrawler.generator.populators.PopulatorEnemies;
import com.gammacrawler.generator.populators.PopulatorSkulls;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Generator {
	User player;
	Board board;
	int[][] ar;
	ArrayList<Enemy> enemies;
	
	public Generator() {
		this.player = new User("Richard");
		this.board = new Board(21,21);
		this.ar = this.board.getArray();
		this.enemies = new ArrayList<>();
		this.createEnemies();
		this.setPlayerInitialLocation();
		populate(new PopulatorSkulls(this.board.getArray(), enemies));
		populate(new PopulatorEnemies(this.board.getArray(), enemies));
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
		this.createEnemies();
		this.setPlayerInitialLocation();

	}
	
	
	
	public User getPlayer() {
		return this.player;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * @return ArrayList of enemies based on the player's xp.
	 */
	public ArrayList<Enemy> createEnemies() {
//		if (this.player.getXP() < 100) {
//			for (int i = 0; i <= 4; i++) {
				Enemy em = new EnemySlime();
				System.out.println("enemyslime created");
				int[] loc = this.board.getFreePosition();
				System.out.println("found empty location at " + loc[0] + " " + loc[1]);
				System.out.println("TEST");
				em.setLocation(loc[0], loc[1]);
				System.out.println("set enemy location");
				this.enemies.add(em);
				System.out.println("adding enemy to javafx scene");
				em.getImageView().setX(loc[0]);
				em.getImageView().setY(loc[1]);
				System.out.println("enemy added");
				
				Enemy ogre = new Ogre();
				int[] ogreloc = this.board.getFreePosition();
				ogre.setLocation(ogreloc[0], ogreloc[1]);
				this.enemies.add(ogre);
				ogre.getImageView().setX(ogreloc[0]);
				ogre.getImageView().setY(ogreloc[1] - Settings.TILESIZE);
//			}
//		}
		return this.enemies;
	}
	

	
	
	/**
	 * This is called any time the player presses a key that results in a
	 * successful movement. No more key pressed will be handled until this
	 * method finishes. This method moves the enemies around among other things.
	 */
	public void turn() {
		ArrayList<MoveRequest> moveRequests = new ArrayList<>();
		boolean conflicts = true;

		// This gets all the valid (not into a wall) MoveRequests from all all enemies.
		for (Enemy enemy : enemies) {

			// The valid ones that will be added to.
			ArrayList<MoveRequest> newMq = new ArrayList<>();

			for (MoveRequest mq : enemy.getMovePossibilities()) {
				int tX = (mq.x / Settings.TILESIZE) - 1;
				int tY = (mq.y / Settings.TILESIZE) - 1;

				if (!(ar[tY][tX] == 1)) {
					newMq.add(mq);
				}
			}

			// Makes the enemy choose one from its possible movements.
			moveRequests.add(enemy.getMoveRequest(newMq));
		}

		// Then we check and see if any 2 enemies are trying to move into the
		// same place. We keep doing this until we can iterate through all of it
		// with no conflicts. Each time we do this it moves it closer to that
		// condition because it moves towards no enemies moving which is a non
		// conflicted state.
		while (conflicts) {
			conflicts = false;
			for (int i = 0; i < moveRequests.size() - 1; i++) {
				for (int j = i + 1; j < moveRequests.size(); j++) {
					if (moveRequests.get(i).x == moveRequests.get(j).x) {
						if (moveRequests.get(i).y == moveRequests.get(j).y) {

							// For now I will choose in an arbitrary way
							Enemy e = moveRequests.get(j).e;

							// This sets the moveRequest to the failed request,
							// which should just be the enemy and its non moved
							// position.
							moveRequests.set(j, e.handleMoveRequestFailed());
							conflicts = true;
						}
					}
				}
			}
		}

		// Then we simply move the enemies to their requested position, which we
		// know is non conflicted.
		for (MoveRequest mq : moveRequests) {
			mq.e.setLocation(mq.x, mq.y);
		}
	}

	public Canvas getDungeon() {
		// import images to use as tiles
		Image wall = new Image("file:src/com/gammacrawler/images/wall.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		Image floor = new Image("file:src/com/gammacrawler/images/floor.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		Image door = new Image("file:src/com/gammacrawler/images/door.png", Settings.TILESIZE, Settings.TILESIZE, false, false);
		Image skull = new Image("file:src/com/gammacrawler/images/skull.png", Settings.TILESIZE, Settings.TILESIZE, false, false);

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
				} else if (ar[i][j] == 1) {
					// draw wall tile where you find a 1 in the array
					gc.drawImage(wall, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 2) {
					gc.drawImage(door, x, y, Settings.TILESIZE, Settings.TILESIZE);
				} else if (ar[i][j] == 3) {
					gc.drawImage(skull, x, y, Settings.TILESIZE, Settings.TILESIZE);
				}

			}
		}
		
		return cv;
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
}
