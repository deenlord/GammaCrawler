package com.gammacrawler;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/**
 * @author jake, nathaniel, jeromie
 *
 */
public class Main extends Application implements EventHandler<ActionEvent> {
	Button launchButton;
	Stage mainStage;
	User player;
	public final static double tileSize = 32;
	private ArrayList<Sprite> characters = new ArrayList<>();

	
	/**
	 * @return the start menu Scene
	 */
	public Scene getMenu()  {
		// a little setup
		Group menu = new Group();
		menu.getStylesheets().add("file:src/com/gammacrawler/css/launcher.css");
		menu.getStyleClass().add("menu");
		Pane pane = new Pane();
		pane.getStyleClass().add("menuPane");
		pane.setPrefSize(320,320);
		
		
		// create the game name label
		Text label = new Text();
		label.setText("GammaCrawler!");
		label.setLayoutY(85);
		label.setLayoutX(45);
		label.setFill(Color.RED);
		label.getStyleClass().add("menuLabel");
		
		int i = 32;
		// border
		Sprite imgY1 = new Sprite("file:src/com/gammacrawler/images/user.png", 32);
		Sprite imgY2 = new Sprite("file:src/com/gammacrawler/images/user2.png", 32);
		Sprite imgY3 = new Sprite("file:src/com/gammacrawler/images/witch.png", 32);
		Sprite imgY4 = new Sprite("file:src/com/gammacrawler/images/ghostpirate.png", 32);
		Sprite imgY5 = new Sprite("file:src/com/gammacrawler/images/chad.png", 32);
		Sprite imgY6 = new Sprite("file:src/com/gammacrawler/images/dogmaskedzombie.png", 32);
		Sprite imgY7 = new Sprite("file:src/com/gammacrawler/images/zombieviking.png", 32);
		Sprite imgY8 = new Sprite("file:src/com/gammacrawler/images/bill.png", 32);
		Sprite imgY9 = new Sprite("file:src/com/gammacrawler/images/blueguy.png", 32);
		Sprite imgY10 = new Sprite("file:src/com/gammacrawler/images/zombieninja.png", 32);
		
		
		
		Sprite imgX1 = new Sprite("file:src/com/gammacrawler/images/woodensword.png", 32);
		
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
		
		
		int counter = 0;
		for (Sprite spr : characters) {
			spr.getSprite().setLayoutX(i * counter);
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
	 * @return the game board scene
	 * with a character since 4/1
	 */
	public Scene gameLoop(Sprite sprite) {

		Generator gen = new Generator(sprite); // creates board and user procedurally...
		// Canvas goes in a Group
		Group root = new Group();
		// Create the array
		int[][] ar = gen.getBoard().getArray();
		// import images to use as tiles
		
		Image wall = new Image("file:src/com/gammacrawler/images/wall.png", tileSize, tileSize, false, false);
		Image floor = new Image("file:src/com/gammacrawler/images/floor.png", tileSize, tileSize, false, false);
		
		
		// to use as coordinates
		double x;
		double y;
		
		// create the Canvas (feel free to resize)
		Canvas cv = new Canvas(855,860);
		GraphicsContext gc = cv.getGraphicsContext2D();
		
	    // iterate through the array and draw the appropriate sprite
		// based on 1 or 0 found at index
	    for (int i=0; i < (ar.length); i++ ) {
		    for (int j=0; j<ar[i].length; j++) {
		        // for each j set x and y
		    	y = (i + 1) * tileSize; // plus one to avoid dividing by zero
	        	x = (j + 1) * tileSize;
	        	
		        if (ar[i][j] == 1) {
		        	// draw wall tile where you find a 1 in the array
		        	gc.drawImage(wall, x, y, tileSize, tileSize);		        	
		        }
		        else  {
		        	// draw floor tile where you find a 0
		        	gc.drawImage(floor, x, y, tileSize, tileSize);
		        }
		    
		    }   
	    }
		
	    // create a User
	    User player = gen.getPlayer();
		// only add them once...
	    int counter = 0;
	    
	    // iterate through the array to find the first zero location,
	    // draw the User there. ... only once.
	    for (int z=0; z < (ar.length); z++) {
	    	for (int j=0; j < (ar[z].length); j++) {
	    		if ( ar[z][j] == 0 & counter == 0 ) {
	    			y = (z + 1) * tileSize; // avoid z/0
		        	x = (j + 1) * tileSize;
		        	player.setLocation((int) x, (int) y);
		        	player.getSprite().setLayoutX(x);
		        	player.getSprite().setLayoutY(y); 
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

	    //set the scene and return it
	    root.getChildren().add(cv);
	    root.getChildren().add(player.getSprite());
	    root.getChildren().add(player.getWeapon());
	    player.getWeapon().setVisible(false);
	    Scene sc = new Scene(root);
	      sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	            	
	            	int x = (player.getLocation()[0] / (int) tileSize) -1;
	            	int y = (player.getLocation()[1] / (int) tileSize) -1;
	            	
	                switch (event.getCode()) {
	                    case W:  
	                    	System.out.println("North");
	                    	if (ar[y-1][x] == 0 || ar[y-1][x] == 2) 
	                    		player.move(Direction.NORTH); 
	                    	break;
	                    case S:  
	                    	System.out.println("South"); 
	                    	if (ar[y+1][x] == 0 || ar[y+1][x] == 2) 
	                    		player.move(Direction.SOUTH); 
	                    	break;
	                    case A:  
	                    	System.out.println("West"); 
	                    	if (ar[y][x-1] == 0 || ar[y][x-1] == 2)
	                    		player.move(Direction.WEST); 
	                    	break;
	                    case D: 
	                    	System.out.println("East"); 
	                    	if (ar[y][x+1] == 0 || ar[y][x+1] == 2)
	                    		player.move(Direction.EAST); 
	                    	break;
	                    case I: 
	                    	System.out.println(player); 
	                    	System.out.println("North: " +  ar[y-1][x]);
	                    	System.out.println("South: " + ar[y+1][x]);
	                    	System.out.println("East: " + ar[y][x+1]);
	                    	System.out.println("West: " + ar[y][x-1]);
	                    	break;
	                    default: break;
	                }
		        	player.getSprite().setLayoutX(player.getLocation()[0]);
		        	player.getSprite().setLayoutY(player.getLocation()[1]);
	            }
	        });
	      
	      sc.setOnMouseClicked(new EventHandler<MouseEvent>() {
	          @Override
	          public void handle(MouseEvent event) {
	        	  player.getWeapon().setVisible(true);
	        	  player.attack();
	        	  
//	              System.out.println("mouse click detected! "+ event.getSource());
//	              System.out.println("x = " + player.getLocation()[0]);
//	              System.out.println("y = " + player.getLocation()[1]);
//	              int location = player.getLocation()[0] + 1;
//	              System.out.println("x = " + location);     
	          }
	      });
	    
	    return sc;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		// if "Launch" button clicked
		if(event.getSource() == launchButton ) {
			double x =  (Math.random() * 10) - (1);
			System.out.println("Character #: " + x);
			this.mainStage.setScene(gameLoop(characters.get((int)x)));
			// I think this is where we can update the game animations.
			
			new AnimationTimer() {
				@Override
				public void handle(long now) {
					mainStage.setAlwaysOnTop(true);
				}
			}.start();
			
		}
		
		
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
