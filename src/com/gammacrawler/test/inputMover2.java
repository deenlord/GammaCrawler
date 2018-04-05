package com.gammacrawler.test;

import com.gammacrawler.User;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;;
/**
 * 
 * @author crathke4
 *
 */
public class inputMover2 extends Application
{
	User user=new User("Richard");//(Goes by Dick)
	public static boolean keepGoing=true;
	Stage MainStage;
	
	/**
	 * 
	 * @return Direction based on WSAD input from console.
	 */

	public Text label=new Text();
	private void moveIn(Scene scene)
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) {
				switch(e.getCode()){
				case W:
					//code for NORTH
					label.setText("W");
					System.out.println("It works...");
					break;
				case S:
					//code for SOUTH
					label.setText("S");
					System.out.println("It works...");
					break;
				case A:
					//code for WEST
					label.setText("A");
					System.out.println("It works...");
					break;
				case D:
					//code for EAST
					label.setText("D");
					System.out.println("It works...");
					break;
				default:
					System.out.println("It doesn't works...");
					break;
				}
				
			}
		});
	}
	
	public void start(Stage stage)
	{
		Pane pane=new Pane();
		label.setText("Default");
		Text testLabel=new Text("Defacto");
		HBox hb=new HBox();
		pane.getChildren().add(label);
		pane.getChildren().add(testLabel);
		hb.getChildren().add(testLabel);
		hb.getChildren().add(label);
	//	this.MainStage=stage;
		Scene scene=new Scene(hb, 500, 500);
		moveIn(scene);
		stage.setScene(scene);
		stage.setTitle("InputMover 2.0");
		stage.show();
		
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
