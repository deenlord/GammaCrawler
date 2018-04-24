package com.gammacrawler;

import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.User;
import com.gammacrawler.item.Weapon;

import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatusBar extends Pane{
	/**
	 * @author Crathke4
	 */
	private User player;
	private Rectangle darkHealth, lightHealth;
	private Rectangle healthBorder;
	private Text health, exp, points;
	private static Text gameStatus;
	private int coordX=32, coordY=14;
	/**
	 * Generates graphic and text representations of health and XP formatted into a single node
	 * @param gen-Generator used to read player Health
	 * @param height-Height of Status Bar
	 * @param length-Length of each bar
	 */
	StatusBar(Generator gen, int height, int length)
	{
		this.player=gen.getPlayer();
		generateHealthBar(height, (length));
		generateExpLabel();
		generatePointsLabel();
		generateGameStatusLabel();
		updateStatus(length, health);
		
	}
	/**
	 * Generates graphic and text representations of players health
	 * @param height - inherit from Constructor
	 * @param length - inherit from Constructor
	 */
	private void generateHealthBar(int height, int length)
	{
		health=new Text(coordX,coordY-1,"HP: "+player.getHP());
		health.setFont(Font.font(null,FontWeight.BOLD, 14));
		health.setFill(Color.DARKRED);
		this.healthBorder=new Rectangle(coordX,coordY,length,height);
		this.darkHealth=new Rectangle(coordX+5,coordY+5,											//Layout
									 (player.getHP()/player.getMaxHP())*(length-10),(height)-10);	//Size
		this.lightHealth=new Rectangle(coordX+5,darkHealth.getLayoutX()+(darkHealth.getHeight()*2),	//Layout
										darkHealth.getWidth(),(darkHealth.getHeight()/2));			//Size
		this.healthBorder.setFill(Color.BLACK);
		darkHealth.setFill(Color.RED);
		lightHealth.setFill(Color.ORANGERED);
		this.getChildren().addAll(health,healthBorder,darkHealth,lightHealth);
	}

	/**
	 * Getter to be used in the updateHealth method in Main.java
	 * @return the players health
	 */
	public Text getHealth() {
		return health;
	}
	
	/**
	 * Generates a text representation of players XP
	 */
	private void generateExpLabel()
	{
		exp=new Text("XP: "+player.getXP()+"/"+player.getMaxHP());
		exp.setX(this.health.getLayoutBounds().getMaxX()+10);
		exp.setY(coordY-1);
		exp.setFont(Font.font(null,FontWeight.BOLD, 14));
		exp.setFill(Color.DARKBLUE);
		this.getChildren().add(exp);
	}
	
	/**
	 * Generates a text representation of players Points
	 */
	private void generatePointsLabel()
	{
		points=new Text("Points: "+player.getPoints());
		points.setX(this.exp.getLayoutBounds().getMaxX()+10);
		points.setY(coordY-1);
		points.setFont(Font.font(null, FontWeight.BOLD, 14));
		points.setFill(Color.DARKGOLDENROD);
		this.getChildren().add(points);
	}
	
	public void generateGameStatusLabel()
	{
		gameStatus=new Text();
		gameStatus.setX(points.getLayoutBounds().getMaxX()+10);
		gameStatus.setY(coordY-1);
		gameStatus.setFont(Font.font(null, FontWeight.BOLD, 14));
		this.getChildren().add(gameStatus);
		
	}
	
	public static void addStatus(String message)
	{
		if(message!=null&&message.length()>1) {
			gameStatus.setText(message);
		}
	}

	/**
	 * Updates the current statusBar to reflect the users condition
	 * @param length  - inherit from generateHealthBar
	 * @param health - inherit from generateHealthBar
	 */
	public void updateStatus(int length, Text health)
	{
		health.setText("HP: "+player.getHP()+"/"+player.getMaxHP());
		this.darkHealth.setWidth(((double)player.getHP()/(double)player.getMaxHP())*(length-10));
		this.lightHealth.setWidth(((double)player.getHP()/(double)player.getMaxHP())*(length-10));
		this.exp.setText("XP: "+player.getXP());
		this.points.setText("Points: "+player.getPoints());
		this.exp.setX(this.health.getLayoutBounds().getMaxX()+10);
		this.points.setX(this.exp.getLayoutBounds().getMaxX()+10);
		gameStatus.setX(points.getLayoutBounds().getMaxX()+10);
	}


}