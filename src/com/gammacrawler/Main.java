package com.gammacrawler;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.gammacrawler.generator.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.embed.swing.SwingNode;


public class Main extends Application implements EventHandler<ActionEvent> {
	Button launchButton;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		// TODO Auto-generated method stub
		mainStage.setTitle("Gamma Crawler!");
		
		
		StackPane menuLayout = new StackPane();
		menuLayout.getChildren().add(launchButton);
		
		Scene launcher = new Scene(menuLayout, 300, 300);
		
		mainStage.setScene(launcher);
		mainStage.show();
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == launchButton ) {
			SwingNode gameBoard = new SwingNode();
			createSwingContent(gameBoard);
			
			StackPane gameLayout = new StackPane();
			gameLayout.getChildren().add(gameBoard);
			Stage gameStage = new Stage();
			gameStage.setTitle("Gamma Crawler - Level 1");
			gameStage.setScene(new Scene(gameLayout, 250,250));
			gameStage.show();
			
		}
	}
	
	 private void createSwingContent(final SwingNode swingNode) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	Map levelOne = new Map();
	    			GamePanel frame = levelOne.getMap();
	                swingNode.setContent(frame);
	            }
	        });
	 }
	 
	 private void setupMenuButtons() {
		 launchButton = new Button();
		 launchButton.setText("Enter the Dungeon");
		 launchButton.setOnAction(this);
		 
	 }

}
