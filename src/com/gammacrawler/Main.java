package com.gammacrawler;

import javax.swing.SwingUtilities;
import com.gammacrawler.generator.*;
import com.gammacrawler.generator.map.Map;

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
import javafx.scene.Group;
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
		// launch
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
			gameBoard.setCache(true);
			// add the map to the pane
			gamePane.getChildren().add(gameBoard);
			
			// add user to game
			User p1 = new User("Player1");
			
			// add the player image to the pane
			gamePane.getChildren().add(p1.getImageView());
			
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
