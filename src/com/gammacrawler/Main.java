package com.gammacrawler;

import java.util.ArrayList;

import com.gammacrawler.entity.Enemy;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;
import com.gammacrawler.item.Potion;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author jake, nathaniel, jeromie, crathke4
 *
 */
public class Main extends Application implements EventHandler<ActionEvent> {
	Button launchButton;
	Stage mainStage;
	private ArrayList<Sprite> characters = new ArrayList<>();
	public static Generator gen;

	/**
	 * @return the start menu Scene
	 */
	public Scene getMenu() {
		// a little setup
		Group menu = new Group();
		menu.getStylesheets().add("file:src/com/gammacrawler/css/launcher.css");
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
		Sprite imgY1 = new Sprite("file:src/com/gammacrawler/images/user.png");
		Sprite imgY2 = new Sprite("file:src/com/gammacrawler/images/user2.png");
		Sprite imgY3 = new Sprite("file:src/com/gammacrawler/images/witch.png");
		Sprite imgY4 = new Sprite("file:src/com/gammacrawler/images/ghostpirate.png");
		Sprite imgY5 = new Sprite("file:src/com/gammacrawler/images/chad.png");
		Sprite imgY6 = new Sprite("file:src/com/gammacrawler/images/dogmaskedzombie.png");
		Sprite imgY7 = new Sprite("file:src/com/gammacrawler/images/zombieviking.png");
		Sprite imgY8 = new Sprite("file:src/com/gammacrawler/images/bill.png");
		Sprite imgY9 = new Sprite("file:src/com/gammacrawler/images/blueguy.png");
		Sprite imgY10 = new Sprite("file:src/com/gammacrawler/images/zombieninja.png");

		Sprite imgX1 = new Sprite("file:src/com/gammacrawler/images/babydevil.png");
		Sprite imgX2 = new Sprite("file:src/com/gammacrawler/images/candle.png");
		Sprite imgX3 = new Sprite("file:src/com/gammacrawler/images/bigcreep.png");
		Sprite imgX4 = new Sprite("file:src/com/gammacrawler/images/bomb.png");
		Sprite imgX5 = new Sprite("file:src/com/gammacrawler/images/bat.png");
		Sprite imgX6 = new Sprite("file:src/com/gammacrawler/images/ghost.png");
		Sprite imgX7 = new Sprite("file:src/com/gammacrawler/images/slime.png");
		Sprite imgX8 = new Sprite("file:src/com/gammacrawler/images/ogre.png");
		Sprite imgX9 = new Sprite("file:src/com/gammacrawler/images/chestfull.png");

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
			
				if (counter >= 10) {
					spr.getImageView().setLayoutY(i * (counter - 9));
				}
				else {
					spr.getImageView().setLayoutX(i * counter);
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

		// create authors label
		Text authors = new Text();
		authors.setText("By: Nathaniel Butterfield \n      Christian Rathke\n      JakobVendegna");
		authors.setLayoutY(200);
		authors.setLayoutX(90);
		authors.setFill(Color.WHITESMOKE);
		authors.getStyleClass().add("authorLabel");

		// add the label and launchButton to a Pane
		pane.getChildren().add(label);
		pane.getChildren().add(launchButton);
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
		}
	}

	/**
	 * This sets up the Scene for the game
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

		//add a status bar
		root.getChildren().add(gen.getStatus());
		//root.getChildren().add(gen.invBar);
		return new Scene(root);
	}

	/**
	 * @return the game board scene with a character since 4/1
	 */
	public Scene gameLoop() {

		gen = new Generator(); // creates board and user
		System.out.println("Generator created");			
		// procedurally...
	
		Scene sc = setupScene();
		
		// event handling for the gameLoop Scene.
		sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				Item it;
				int x = (gen.getPlayer().getLocation()[0] / Settings.TILESIZE) - 1;
				int y = (gen.getPlayer().getLocation()[1] /  Settings.TILESIZE) - 1;
//				System.out.println(event.getCode());
				switch (event.getCode()) {
				
				case W:
					System.out.println("North");
					if (gen.ar[y - 1][x] < 10) {
						gen.getPlayer().move(Direction.NORTH);
						gen.getPlayer().updateDirection(Direction.NORTH);
						gen.handleCollisions();
						clearDead((Group) sc.getRoot());
					}
					break;
				case S:
					System.out.println("South");
					if (gen.ar[y + 1][x] < 10) {
						gen.getPlayer().move(Direction.SOUTH);
						gen.getPlayer().updateDirection(Direction.SOUTH);
						gen.handleCollisions();
						clearDead((Group) sc.getRoot());
					}
					break;
				case A:
					System.out.println("West");
					if (gen.ar[y][x - 1] < 10) {
						gen.getPlayer().move(Direction.WEST);
						gen.getPlayer().updateDirection(Direction.WEST);
						gen.handleCollisions();
						clearDead((Group) sc.getRoot());
					}
					break;
				case D:
					System.out.println("East");
					if (gen.ar[y][x + 1] < 10) {
						gen.getPlayer().move(Direction.EAST);
						gen.getPlayer().updateDirection(Direction.EAST);
						gen.handleCollisions();
						clearDead((Group) sc.getRoot());
					}
					break;
				case I:
					System.out.println(gen.getPlayer());
					System.out.println("North: " + gen.ar[y - 1][x]);
					System.out.println("South: " + gen.ar[y + 1][x]);
					System.out.println("East: " + gen.ar[y][x + 1]);
					System.out.println("West: " + gen.ar[y][x - 1]);
					break;
				case DIGIT1:
					it = Generator.player.getInventory().get(1);
					if (it instanceof Potion) {
						((Potion) it).drink(Generator.player);
					}
					Generator.player.getInventory().remove(1);
					break;	
//				case DIGIT2:
//					it = Generator.player.getInventory().get(2);
//					if (it instanceof Potion) {
//						((Potion) it).drink(Generator.player);
//						Generator.player.getInventory().remove(2);
//		
//					}
//					break;	
				default:
					break;
				}
				//move enemies
				for(Entity e: gen.gameEntities)
				{
					if(e instanceof Enemy) {
						((Enemy) e).moveAI();
						gen.handleCollisions();
					}
				}
				gen.getPlayer().getImageView().setLayoutX(gen.getPlayer().getLocation()[0]);
				gen.getPlayer().getImageView().setLayoutY(gen.getPlayer().getLocation()[1]);
				//update the status bar to reflect current player condition
				gen.getStatus().updateStatus(672, gen.getStatus().getHealth());
				//gen.updateInventoryBar();
			}
		});

		sc.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gen.getPlayer().getWeapon().getImageView().setVisible(true);
				gen.getPlayer().attack();
				//update the status bar to reflect current player condition
				gen.getStatus().updateStatus(672, gen.getStatus().getHealth());
				gen.handleCollisions();
				clearDead((Group) sc.getRoot());
				
			}
		});

		return sc;
	}

	

	@Override
	public void handle(ActionEvent event) {
		// main menu event handling
		// because we change scenes the only event being handled here is the button
		// click on the main menu. Click the button to change the scene.
		// Game event handling is done in that scene's handle method, located directly above.
		
		
		// if "Launch" button clicked
		if (event.getSource() == launchButton) {
			this.mainStage.setScene(gameLoop());
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
