package com.gammacrawler;

import com.gammacrawler.entity.Item;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * @author jakev, crathke4
 *
 */
public class InventoryBar extends Pane {
	Group gr;
	
	public InventoryBar() {
		gr = new Group();
		this.getInventoryBar(gr);
		
	}
	
	
	public Pane getInventoryBar(Group g) {
		int x = Settings.TILESIZE;
		int y = Settings.TILESIZE;
		for (Item item:Generator.player.getInventory())
		{
			
		//if(!(item instanceof Weapon))
			{
			System.out.println("Found " + item + " in inventory");
			ImageView iv = item.getSprite().getNewImageView();
			iv.setX(x);
			iv.setY(y);
			x += x;
			g.getChildren().add(iv);
			}
		}
		this.getChildren().clear();
		this.getChildren().add(g);
		return this;
	}
	
	public void update() {
		this.getInventoryBar(this.gr);
	}
}