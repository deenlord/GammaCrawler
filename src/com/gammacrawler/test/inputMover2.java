package com.gammacrawler.test;

import com.gammacrawler.Direction;
import com.gammacrawler.entity.User;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
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
	String msg=new String("WSAD to move, ESC to close\n"+user.getName()+"'s coordinates are\nKey Pressed: ");
	Text label=new Text(msg);
	Text location=new Text("\n"+user.getLocation()[0]+", "+user.getLocation()[1]);
	/**
	 * 
	 * @param scene - the scene in which the user is placed
	 */
	private void move(Scene scene)
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) {
				switch(e.getCode()){
				case W:
					user.move(Direction.NORTH);
					label.setText(msg+"W");
					break;
				case S:
					user.move(Direction.SOUTH);
					label.setText(msg+"S");
					break;
				case A:
					user.move(Direction.WEST);
					label.setText(msg+"A");
					break;
				case D:
					user.move(Direction.EAST);
					label.setText(msg+"D");
					break;
				case ESCAPE:
					stop();
				default:
					System.out.println("Standard WSAD controls");
					break;
				}
				location.setText("\n"+user.getLocation()[0]+", "+user.getLocation()[1]);
			}
		});
	}
	
	public void stop()
	{
		MainStage.close();
	}
	
	public void start(Stage stage)
	{
		HBox hb=new HBox();
		Scene scene=new Scene(hb, 500, 500);
		this.MainStage=stage;
		hb.getChildren().addAll(label,location);
		move(scene);
		stage.setScene(scene);
		stage.setTitle("InputMover 2.0");
		stage.show();
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
