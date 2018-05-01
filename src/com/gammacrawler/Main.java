package com.gammacrawler;

import java.util.ArrayList;
import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.entity.User;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * @author jake, nathaniel, jeromie, crathke4
 *
 */
public class Main extends Application implements EventHandler<ActionEvent> {
	Button launchButton;
//	Button settingsButton;
	Stage mainStage;
	private ArrayList<Sprite> characters = new ArrayList<>();
	public static Generator gen;

	// counter for enemy movement
	int counter = 0;

	/**
	 * @return the start menu Scene
	 */
	public Scene getMenu() {
		// a little setup
		Group menu = new Group();
		menu.getStylesheets().add(getClass().getClassLoader().getResource("com/gammacrawler/css/launcher.css").toString());
		menu.getStyleClass().add("menu");
		Pane pane = new Pane();
		pane.getStyleClass().add("menuPane");
		pane.setPrefSize(Settings.STARTMENUSIZE, Settings.STARTMENUSIZE);
		// create the game name label
		Text label = new Text();
		label.setText("GammaCrawler!");
		label.setLayoutY(85);
		label.setLayoutX(45);
		label.setFill(Color.RED);
		label.getStyleClass().add("menuLabel");
		

		int i = 32;
		// border
		Sprite imgY1 = new Sprite("com/gammacrawler/images/user.png");
		Sprite imgY2 = new Sprite("com/gammacrawler/images/user2.png");
		Sprite imgY3 = new Sprite("com/gammacrawler/images/witch.png");
		Sprite imgY4 = new Sprite("com/gammacrawler/images/ghostpirate.png");
		Sprite imgY5 = new Sprite("com/gammacrawler/images/chad.png");
		Sprite imgY6 = new Sprite("com/gammacrawler/images/dogmaskedzombie.png");
		Sprite imgY7 = new Sprite("com/gammacrawler/images/zombieviking.png");
		Sprite imgY8 = new Sprite("com/gammacrawler/images/bill.png");
		Sprite imgY9 = new Sprite("com/gammacrawler/images/blueguy.png");
		Sprite imgY10 = new Sprite("com/gammacrawler/images/zombieninja.png");

		Sprite imgX1 = new Sprite("com/gammacrawler/images/babydevil.png");
		Sprite imgX2 = new Sprite("com/gammacrawler/images/candle.png");
		Sprite imgX3 = new Sprite("com/gammacrawler/images/bigcreep.png");
		Sprite imgX4 = new Sprite("com/gammacrawler/images/bomb.png");
		Sprite imgX5 = new Sprite("com/gammacrawler/images/bat.png");
		Sprite imgX6 = new Sprite("com/gammacrawler/images/ghost.png");
		Sprite imgX7 = new Sprite("com/gammacrawler/images/slime.png");
		Sprite imgX8 = new Sprite("com/gammacrawler/images/ogre.png");
		Sprite imgX9 = new Sprite("com/gammacrawler/images/chestfull.png");

		characters.add(imgY1);
		characters.add(imgY2);
		characters.add(imgY3);
		characters.add(imgY4);
		characters.add(imgY5);
		characters.add(imgY6);
		characters.add(imgY7);
		characters.add(imgY8);
		characters.add(imgY9);
		characters.add(imgY10);

		characters.add(imgX1);
		characters.add(imgX2);
		characters.add(imgX3);
		characters.add(imgX4);
		characters.add(imgX5);
		characters.add(imgX6);
		characters.add(imgX7);
		characters.add(imgX8);
		characters.add(imgX9);

		int counter = 0;
		for (Sprite spr : characters) {

			if (counter < 10) {
				spr.getImageView().setLayoutX(i * counter);
			} else if (counter < 19){
				spr.getImageView().setLayoutY(i * (counter - 9));
				
			}
			pane.getChildren().add(spr.getImageView());
			counter++;

		}
		// create the button
		launchButton = new Button();
		launchButton.setText("Enter the Dungeon");
		launchButton.setOnAction(this);
		launchButton.setLayoutY(125);
		launchButton.setLayoutX(70);
		
//		settingsButton = new Button();
//		settingsButton.setText("Settings");
//		settingsButton.setOnAction(this);
//		settingsButton.setLayoutX(125);
//		settingsButton.setLayoutY(175);

		// create authors label
		Text authors = new Text();
		authors.setText("Created By: \nNathaniel Butterfield\nChristian Rathke\nJakob Vendegna");
		authors.setTextAlignment(TextAlignment.CENTER);
		authors.setLayoutY(200);
		authors.setLayoutX(90);
		authors.setFill(Color.WHITESMOKE);
		authors.getStyleClass().add("authorLabel");

		// add the label and launchButton to a Pane
		pane.getChildren().add(label);
		pane.getChildren().add(launchButton);
//		pane.getChildren().add(settingsButton);
		pane.getChildren().add(authors);

		// add the pane to the group
		menu.getChildren().add(pane);

		// create the Scene
		Scene launcher = new Scene(menu);
		return launcher;
	}

	/**
	 * This removes dead entities from the map
	 */
	private void clearDead(Group root) {
		ArrayList<Entity> deadEntities = new ArrayList<>();

		for (Entity en : gen.gameEntities) {
			if (en.isDead) {
				deadEntities.add(en);
			}
		}

		for (Entity en : deadEntities) {
			if (en.isDead) {
				gen.gameEntities.remove(en);
				root.getChildren().remove(en.getImageView());
			}
			if (en instanceof User) {
				mainStage.setScene(gameOver());
			}
		}
	}

	/**
	 * This sets up the Scene for the game
	 * 
	 * @return The game Scene
	 */
	private Scene setupScene() {

		// Canvas goes in a Group
		Group root = new Group();

		// Set the scene
		root.getChildren().add(gen.getDungeon());

		for (Entity en : gen.gameEntities) {
			root.getChildren().add(en.getImageView());
		}

		root.getChildren().add(gen.getPlayer().getWeapon().getImageView());
		gen.gameEntities.add(gen.getPlayer().getWeapon());
		gen.getPlayer().getWeapon().getImageView().setVisible(false);

		// add a status bar
		root.getChildren().add(gen.getStatus());
		root.getChildren().add(gen.invBar);
		return new Scene(root);
	}


	/**
	 * @return the game board scene with a character since 4/1
	 * @param player - A User
	 * @return - the main game loop as a Scene
	 */
	public Scene gameLoop(User player) {

		// This makes a new generator if the player exists. If not it makes a
		// new stage with 0 xp
		if (player != null) {
			gen = new Generator(player);
		} else {
			gen = new Generator();
		}

		StatusBar.addStatus("Generator created");
		// procedurally...

		Scene sc = setupScene();

		// event handling for the gameLoop Scene.
		sc.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				// Get the tile x and y of the player from the real x and y.
				int x = (gen.getPlayer().getLocation()[0] / Settings.TILESIZE) - 1;
				int y = (gen.getPlayer().getLocation()[1] / Settings.TILESIZE) - 1;

				// This will be set to true if we want the enemies to be
				// calculated
				boolean validKeyPressed = false;
				int useItem = 0;
				Direction direction = null;
				switch (event.getCode()) {

				case W:
					StatusBar.addStatus("North");
					if (Generator.ar[y - 1][x] < 10 || Generator.player.invisibleTurns > 0) {
						direction = Direction.NORTH;
						validKeyPressed = true;
					}
					break;
				case S:
					StatusBar.addStatus("South");
					if (Generator.ar[y + 1][x] < 10 || Generator.player.invisibleTurns > 0) {
						direction = Direction.SOUTH;
						validKeyPressed = true;
					}
					break;
				case A:
					StatusBar.addStatus("West");
					if (Generator.ar[y][x - 1] < 10 || Generator.player.invisibleTurns > 0) {
						direction = Direction.WEST;
						validKeyPressed = true;
					}
					break;
				case D:
					StatusBar.addStatus("East");
					if (Generator.ar[y][x + 1] < 10 || Generator.player.invisibleTurns > 0) {
						direction = Direction.EAST;
						validKeyPressed = true;
					}
					break;
				case I:
					StatusBar.addStatus(gen.getPlayer().getName());
					StatusBar.addStatus("North: " + Generator.ar[y - 1][x]);
					StatusBar.addStatus("South: " + Generator.ar[y + 1][x]);
					StatusBar.addStatus("East: " + Generator.ar[y][x + 1]);
					StatusBar.addStatus("West: " + Generator.ar[y][x - 1]);
					break;
				case X:
					printInventoryDebug();
					break;
				case DIGIT1:
					useItem = 1;
					break;
				case DIGIT2:
					useItem = 2;
					break;
				case DIGIT3:
					useItem = 3;
					break;
				case DIGIT4:
					useItem = 4;
					break;
				case DIGIT5:
					useItem = 5;
					break;
				case DIGIT6:
					useItem = 6;
					break;
				case DIGIT7:
					useItem = 7;
					break;
				case DIGIT8:
					useItem = 8;
					break;
				case DIGIT9:
					useItem = 9;

					break;
				default:
					break;
				}

				// Use items
				useItem(useItem);

				// Handle collisions and move if valid key pressed
				if (validKeyPressed) {
					gen.getPlayer().move(direction);
					gen.getPlayer().updateDirection(direction);
					gen.handleCollisions();

					// Move enemies
					moveEnemies();

					// Reduce the players invisibility
					reduceInvisibility();

					x = (gen.getPlayer().getLocation()[0] / Settings.TILESIZE) - 1;
					y = (gen.getPlayer().getLocation()[1] / Settings.TILESIZE) - 1;

					// Kill the player if they phased into a wall with the Ghost
					// Potion
					if (gen.board.getArray()[y][x] >= 10 && Generator.player.invisibleTurns < 1) {
						System.out.println(x + " " + y + " " + gen.board.getArray()[x][y]);
						Generator.player.setHP(0);
						Generator.player.die(Generator.player);
					}

					// Check for staircase
					if(Generator.ar[y][x] == Settings.STAIR_ID) {
						mainStage.setScene(newLevel(gen.getPlayer()));
					}
				}

				gen.getPlayer().getImageView().setLayoutX(gen.getPlayer().getLocation()[0]);
				gen.getPlayer().getImageView().setLayoutY(gen.getPlayer().getLocation()[1]);
				// update the status bar to reflect current player condition
				gen.getStatus().updateStatus(672, gen.getStatus().getHealth());
				gen.updateInventoryBar();

				clearDead((Group) sc.getRoot());
			}
		});

		sc.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gen.getPlayer().getWeapon().getImageView().setVisible(true);
				gen.getPlayer().attack();
				// update the status bar to reflect current player condition
				gen.getStatus().updateStatus(672, gen.getStatus().getHealth());
				gen.handleCollisions();
				gen.updateInventoryBar();
				clearDead((Group) sc.getRoot());

				// Make sure the players sword is not hanging around doing
				// damage invisibly
				gen.getPlayer().getWeapon().setDoingDamage(false);
			}
		});

		return sc;
	}

	/**
	 * Just prints out the inventory of all enemies and the player.
	 */
	private void printInventoryDebug() {
		for (Entity e : gen.gameEntities) {
			if (e instanceof Enemy) {
				System.out.print(e.getClass().getSimpleName() + ": ");
				for (Item i : ((Enemy) e).getInventory()) {
					System.out.print(i.getClass().getSimpleName() + " ");
				}
				System.out.println();
			}
		}
		System.out.print("Player: ");
		for (Item i : Generator.player.getInventory()) {
			System.out.print(i.getClass().getSimpleName() + " ");
		}
		System.out.println();
	}

	/**
	 * If useItem is more than 0, it uses the item in the slot useItem, and sets
	 * useItem to 0.
	 * 
	 * @param useItem
	 *            The slot to attempt to use.
	 */
	private void useItem(int useItem) {
		if (useItem > 0) {
			if (gen.getPlayer().getInventory().size() > useItem) {

				// Show the player what item they used
				StatusBar.addStatus(Generator.player.getInventory().get(useItem).getName().toString());
				gen.getPlayer().getInventory().get(useItem).use(gen.getPlayer());
			} else {

				// Or tell them they don't have an item in that slot
				StatusBar.addStatus("Can't use what you don't have");
			}
			useItem = 0;
		}
	}

	/**
	 * Reduces the player visibility by one turn, making them visible if it
	 * reaches 0.
	 */
	private void reduceInvisibility() {
		if (Generator.player.invisibleTurns > 0) {
			Generator.player.invisibleTurns--;
		}
		if (Generator.player.invisibleTurns < 1) {
			Generator.player.getImageView().setOpacity(1.0);
		}
	}

	/**
	 * Moves all the enemies.
	 */
	private void moveEnemies() {
		counter++;
		if (counter > 2) {
			for (Entity e : gen.gameEntities) {
				if (e instanceof Enemy) {
					((Enemy) e).moveAI();
				}
			}
			gen.handleCollisions();
			counter = 0;
		}
	}

	/**
	 * Returns a Scene for when the player has died.
	 * 
	 * @return gameOver Scene
	 */
	public Scene gameOver() {
		// Set up menu group
		Group group = new Group();
		group.getStylesheets().add(getClass().getClassLoader().getResource("com/gammacrawler/css/launcher.css").toString());
		group.getStyleClass().add("menu");

		// Display red gameOver text
		Text gameOver = new Text();
		gameOver.setText("GAME OVER!");
		gameOver.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 72));
		gameOver.setFill(Color.RED);
		gameOver.xProperty()
				.bind(mainStage.widthProperty().divide(2).subtract((gameOver.getLayoutBounds().getWidth()) / 2));
		gameOver.yProperty().bind(mainStage.heightProperty().divide(2));
		group.getChildren().add(gameOver);

		// Add main menu button
		Button restart = new Button("Main Menu");
		restart.setOnAction(e -> {
			try {
				start(mainStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		group.getChildren().add(restart);
		restart.layoutXProperty().bind(gameOver.xProperty().add((gameOver.getLayoutBounds().getWidth() / 2) - 57));
		restart.layoutYProperty().bind(gameOver.yProperty().add(gameOver.getLayoutBounds().getHeight() / 2));

		// Add score text
		int points;
		try {
			points = Generator.player.getPoints() / (Generator.player.getXP() / 100);
		} catch (Exception e) {
			points = 0;
		}
		Text score = new Text("Score: " + points);
		score.setFont(Font.font(null, FontWeight.BOLD, 20));
		score.setFill(Color.GOLDENROD);
		score.xProperty().bind(mainStage.widthProperty().divide(2).subtract((score.getLayoutBounds().getWidth()) / 2));
		score.yProperty().bind(mainStage.heightProperty().divide(2)
				.add(restart.getHeight() + gameOver.getLayoutBounds().getHeight() + 20));
		group.getChildren().add(score);

		// Set up menu pane
		Pane pane = new Pane(group);
		pane.setPrefSize(1000, 562.5);
		pane.getStylesheets().add(getClass().getClassLoader().getResource("com/gammacrawler/css/launcher.css").toString());
		pane.getStyleClass().add("gameOver");

		// return scene
		Scene scene = new Scene(pane);
		return scene;
	}

	public Scene newLevel(User player)
	{
		Scene newLevel=gameLoop(player);
		return newLevel;
	}
	
	@Override
	public void handle(ActionEvent event) {
		// main menu event handling
		// because we change scenes the only event being handled here is the
		// button
		// click on the main menu. Click the button to change the scene.
		// Game event handling is done in that scene's handle method, located
		// directly above.

		// if "Launch" button clicked
		if (event.getSource() == launchButton) {
			this.mainStage.setScene(gameLoop(null));
			
			// I think this is where we can update the game animations.

			new AnimationTimer() {
				@Override
				public void handle(long now) {
					mainStage.setAlwaysOnTop(true);
				}
			}.start();

		}

	}

	public static Generator getGenerator() {
		Generator generator = gen;

		return generator;
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		// make the Stage usable throughout the Class
		this.mainStage = mainStage;
		// This is the main game menu screen.
		mainStage.setTitle("GammaCrawler!");

		// launch the start menu
		mainStage.setScene(getMenu());
		mainStage.show();
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				mainStage.setAlwaysOnTop(true);
			}
		}.start();

	}

	public static void main(String[] args) {
		// Don't add anything else here, create a method that returns a Scene
		// and add an event handler in the handle method to call it.
		// Nothing else can go here!
		launch(args);
	}

}
