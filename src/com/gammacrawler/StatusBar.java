package com.gammacrawler;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class StatusBar extends Pane{
	
	private User player;
	private Rectangle border;
	private Rectangle health;
	private Shape healthBar= Shape.union(border, health);
	private Rectangle experienceBar;
	
	/**
	 * 
	 * @param gen-Generator used to read player Health
	 * @param height-Height of Status Bar
	 * @param length-Length of each bar
	 */
	StatusBar(Generator gen, int height, int length)
	{
		this.player=gen.getPlayer();
		this.getChildren().add(generateHealthBar(60,800));
	}
	
	private Shape generateHealthBar(int height, int length)
	{
		border=new Rectangle(0,0,height,length);
		Rectangle darkHealth=new Rectangle(5,5,(player.getHP()/player.getMaxHP())*(length-10),(height/2)-10),
				 lightHealth=new Rectangle(5,darkHealth.getHeight()+5,(player.getHP()/player.getMaxHP())*(length-10),(height/2)-10);
		health=(Rectangle) Shape.union(lightHealth, darkHealth);
		border.setFill(Color.BLACK);
		health.setFill(Color.DARKRED);
		update(length);
		return healthBar;
	}

	
	public void update(int length)
	{
		health.setWidth(((double)player.getHP()/(double)player.getMaxHP())*(length-10));
	}


}
