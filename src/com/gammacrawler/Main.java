package com.gammacrawler;

import com.gammacrawler.generator.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;


/**
 * @author deenlord
 *
 */
public class Main extends Application implements EventHandler<ActionEvent> {
	Button launchButton;
	// Add nothing else here, all other functionality goes in the handler function.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		// This is the main game menu screen.
		mainStage.setTitle("GammaCrawler!");
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
		// launch
		mainStage.setScene(launcher);
		mainStage.show();
		
	}

	@Override
	public void handle(ActionEvent event) {
		
		// if "Launch" button clicked
		if(event.getSource() == launchButton ) {
			Stage stg = new Stage();
			Group root = new Group();
			// Create the array
			int[][] ar = setupArray();
			// import images to use as tiles
			Image wall = new Image("file:src/com/gammacrawler/images/wall.png");
			Image floor = new Image("file:src/com/gammacrawler/images/floor.png");
			// Control var for size of tile
			double tileSize = 32.0;
			double x;
			double y;
			Canvas cv = new Canvas(960,720);
			GraphicsContext gc = cv.getGraphicsContext2D();
			
		    // iterate through the array and convert Image to appropriate ImageView
		    // and add it, as a tile, to the TilePane
		    for (int i=0; i < (ar.length); i++ ) {
			    for (int j=0; j<ar[i].length; j++) {
			        // for each j
			    	y = (i + 1) * tileSize;
		        	x = (j + 1) * tileSize;
		        	
			        if (ar[i][j] == 1) {
			        	gc.drawImage(wall, x, y, tileSize, tileSize);		        	
			        }
			        else  {
			        	gc.drawImage(floor, x, y, tileSize, tileSize);
			        }
			    }
			}
		    
		    //set the scene, stage, and launch app
		    root.getChildren().add(cv);
		    Scene sc = new Scene(root);
		    stg.setScene(sc);
		    stg.show();
		}
		    
	}
		
		
	public int[][] setupArray() {
		
		Board board = new Board(51, 51);
		board.addMaze();
		
		int[][] array= board.getArray();
		
		// #wolfiewaffle replace my array with yours.
		return array;
	}
	 

}
