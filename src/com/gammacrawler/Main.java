package com.gammacrawler;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
					spr.getSprite().setLayoutY(i * (counter - 9));
				}
				else {
					spr.getSprite().setLayoutX(i * counter);
				}
			pane.getChildren().add(spr.getSprite());
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
	 * @return the game board scene with a character since 4/1
	 */
	public Scene gameLoop() {

		gen = new Generator(); // creates board and user
												// procedurally...
		// Canvas goes in a Group
		Group root = new Group();
		// create a User


		// set the scene and return it
		root.getChildren().add(gen.getDungeon());
		root.getChildren().add(gen.getPlayer().getImageView());
		root.getChildren().add(gen.getPlayer().getWeapon());
		gen.getPlayer().getWeapon().setVisible(false);
		
		
		// uncomment below once we have enemies and want to draw them to the screen
		
//		for (Enemy enemy : gen.enemies) {
//			root.getChildren().add(enemy.getSprite());
//		}
		
		
		Scene sc = new Scene(root);
		
		// event handling for the gameLoop Scene.
		sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {

				int x = (gen.getPlayer().getLocation()[0] / Settings.TILESIZE) - 1;
				int y = (gen.getPlayer().getLocation()[1] /  Settings.TILESIZE) - 1;

				switch (event.getCode()) {
				case W:
					System.out.println("North");
					if (gen.ar[y - 1][x] == 0 || gen.ar[y - 1][x] == 2) {
						gen.getPlayer().move(Direction.NORTH);
//						gen.turn();
					}
					break;
				case S:
					System.out.println("South");
					if (gen.ar[y + 1][x] == 0 || gen.ar[y + 1][x] == 2) {
						gen.getPlayer().move(Direction.SOUTH);
//						gen.turn();
					}
					break;
				case A:
					System.out.println("West");
					if (gen.ar[y][x - 1] == 0 || gen.ar[y][x - 1] == 2) {
						gen.getPlayer().move(Direction.WEST);
//						gen.turn();
					}
					break;
				case D:
					System.out.println("East");
					if (gen.ar[y][x + 1] == 0 || gen.ar[y][x + 1] == 2) {
						gen.getPlayer().move(Direction.EAST);
//						gen.turn();
					}
					break;
				case I:
					System.out.println(gen.getPlayer());
					System.out.println("North: " + gen.ar[y - 1][x]);
					System.out.println("South: " + gen.ar[y + 1][x]);
					System.out.println("East: " + gen.ar[y][x + 1]);
					System.out.println("West: " + gen.ar[y][x - 1]);
					break;
				default:
					break;
				}
				gen.getPlayer().getImageView().setLayoutX(gen.getPlayer().getLocation()[0]);
				gen.getPlayer().getImageView().setLayoutY(gen.getPlayer().getLocation()[1]);
			}
		});

		sc.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gen.getPlayer().getWeapon().setVisible(true);
				gen.getPlayer().attack();

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
