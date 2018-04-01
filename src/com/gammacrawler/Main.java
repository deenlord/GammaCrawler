package com.gammacrawler;

import javax.swing.SwingUtilities;
import com.gammacrawler.generator.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.embed.swing.SwingNode;
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
		label.setFont(Font.font("Veranda", 22));
		label.setY(100);
		label.setX(80);
		
		launchButton = new Button();
		launchButton.setText("Enter the Dungeon");
		launchButton.setOnAction(this);
		launchButton.setLayoutY(125);
		launchButton.setLayoutX(95);
		
		Pane menuLayout = new Pane();
		menuLayout.getChildren().add(label);
		menuLayout.getChildren().add(launchButton);
		
		Scene launcher = new Scene(menuLayout, 300, 300);
		
		mainStage.setScene(launcher);
		mainStage.show();
		
	}

	@Override
	public void handle(ActionEvent event) {
		// if "Launch" button clicked
		if(event.getSource() == launchButton ) {
			// create a pane to add map and players to.
			Pane gamePane = new Pane();
			
			// create a SwingNode - this lets us use Swing elements in JavaFX
			SwingNode gameBoard = new SwingNode();
			// add the map to the gameBoard
			Map newMap = new Map();
			createSwingContent(gameBoard, newMap);

			// add the map to the pane
			gamePane.getChildren().add(gameBoard);
			
			
			// Create an image - representative of a character for now.
			// no license for this image, I couldn't figure
			// out how to add an image from the file system, this works fine, but we need to replace before deployment.
			Image playerImage = new Image("https://cdn3.iconfinder.com/data/icons/food-set-3/91/Food_C230-128.png");
			ImageView p1 = new ImageView();
			p1.setImage(playerImage);
			p1.setFitWidth(25);
			p1.setPreserveRatio(true);
			p1.setCache(true);
			
			// add the player image to the pane
			gamePane.getChildren().add(p1);
			
			// Create a new scene, add the pane to the scene
			// Create a Stage, add the Scene to the Stage.
			Scene gameScene = new Scene(gamePane, 300, 300);
			Stage gameStage = new Stage();
			gameStage.setTitle("Gamma Crawler - Level 1");
			gameStage.setScene(gameScene);
			// Launch
			gameStage.show();
			
		}
	}
	
	

	 /**
	 * @param swingNode - a SwingNode
	 * @param map - a Map
	 * invokes swing, creates a GamePanel out of the map
	 * sets the content of the swingNode to the GamePanel
	 */
	private void createSwingContent(final SwingNode swingNode, Map map) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	    			GamePanel frame = map.getMap();
	                swingNode.setContent(frame);
	            }
	        });
	 }
	 

	 

}
