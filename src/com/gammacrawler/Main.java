package com.gammacrawler;

import com.gammacrawler.generator.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * @author jakev
 *
 */
public class Main extends Application implements EventHandler<ActionEvent> {
	Button launchButton;
	Stage mainStage;
	User player;
	
	/**
	 * @return the start menu Scene
	 */
	public Scene getMenu()  {
		Text label = new Text();
		label.setText("GammaCrawler!");
		label.setId("title");
		label.setLayoutY(125);
		label.setLayoutX(95);
		
		// create the button
		launchButton = new Button();
		launchButton.setText("Enter the Dungeon");
		launchButton.setOnAction(this);
		launchButton.setLayoutY(125);
		launchButton.setLayoutX(95);
		// add the label and launchButton to a Pane
		Pane menuLayout = new Pane();
		menuLayout.getChildren().add(label);
		menuLayout.getChildren().add(launchButton);
		// create the Scene
		Scene launcher = new Scene(menuLayout, 300, 300);
		launcher.getStylesheets().add("file:src/com/gammacrawler/css/launcher.css");
		return launcher;
	}
	
	/**
	 * @return the game board scene
	 * with a character since 4/1
	 */
	public Scene gameLoop() {
		
		// Canvas goes in a Group
		Group root = new Group();
		// Create the array
		int[][] ar = setupArray();
		// Control variable for size of tile
		final double tileSize = 16;
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
		User player = new User("Player1");
		player.setTileSize((int) tileSize);
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
		        	player.getPlayerImageView().setLayoutX(x);
		        	player.getPlayerImageView().setLayoutY(y);
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
	    root.getChildren().add(player.getPlayerImageView());
	    Scene sc = new Scene(root);
	      sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent event) {
	                switch (event.getCode()) {
	                    case W:  
	                    	System.out.println("North"); 
	                    	player.move(Direction.NORTH); 
	                    	break;
	                    case S:  
	                    	System.out.println("South"); 
	                    	player.move(Direction.SOUTH); 
	                    	break;
	                    case A:  
	                    	System.out.println("West"); 
	                    	player.move(Direction.WEST); 
	                    	break;
	                    case D: 
	                    	System.out.println("East"); 
	                    	player.move(Direction.EAST); 
	                    	break;
	                    case I: 
	                    	System.out.println(player); 
	                    	break;
	                    default: break;
	                }
		        	player.getPlayerImageView().setLayoutX(player.getLocation()[0]);
		        	player.getPlayerImageView().setLayoutY(player.getLocation()[1]);
	            }
	        });
	    
	    return sc;
	}
		
		
	public int[][] setupArray() {
		
		Board board = new Board(43,51);
		board.addMaze();
		
		int[][] array= board.getArray();
		
		return array;
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		// if "Launch" button clicked
		if(event.getSource() == launchButton ) {
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
