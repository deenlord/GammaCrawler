package com.gammacrawler;

import com.gammacrawler.entity.User;

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
		updateStatus(length, health);
	}
	/**
	 * Generates graphic and text representations of players health
	 * @param height - inherit from Constructor
	 * @param length - inherit from Constructor
	 */
	private void generateHealthBar(int height, int length)
	{
		health=new Text(coordX,coordY-1,"HP: "+player.getHP()+"/"+player.getMaxHP());
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
		exp=new Text(coordX+health.getLayoutBounds().getWidth()+10,coordY-1,"XP: "+player.getXP());
		exp.setFont(Font.font(null,FontWeight.BOLD, 14));
		exp.setFill(Color.DARKBLUE);
		this.getChildren().add(exp);
	}
	
	/**
	 * Generates a text representation of players Points
	 */
	private void generatePointsLabel()
	{
		points=new Text(coordX+health.getLayoutBounds().getWidth()+10+exp.getLayoutBounds().getWidth(), coordY-1, "Points: "+player.getPoints());
		points.setFont(Font.font(null, FontWeight.BOLD, 14));
		points.setFill(Color.DARKGOLDENROD);
		this.getChildren().add(points);
	}
	
	/**
	 * 
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
	}


}