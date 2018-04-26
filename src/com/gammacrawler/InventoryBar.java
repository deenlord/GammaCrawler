package com.gammacrawler;

import java.util.ArrayList;

import com.gammacrawler.entity.Item;
import com.gammacrawler.item.GoldPotion;
import com.gammacrawler.item.HealthPotion;
import com.gammacrawler.item.WoodenSword;

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
		this.setupInventoryBar(gr);
	}

	public void setupInventoryBar(Group g) {
		int x = Settings.TILESIZE;
		int y = Settings.TILESIZE;
		this.getChildren().clear();
		ArrayList<Item> inv = Generator.player.getInventory();

		for (int i = 0; i < inv.size(); i++) {
			ImageView iv = inv.get(i).getSprite().getNewImageView();
			iv.setX(i * Settings.TILESIZE);
			iv.setY(Settings.TILESIZE);
			
			this.getChildren().add(iv);
		}
//		for (Item item:Generator.player.getInventory())
//		{
//			
//		//if(!(item instanceof Weapon))
//			{
//			System.out.println("Found " + item + " in inventory");
//			ImageView iv = item.getSprite().getNewImageView();
//			iv.setX(x);
//			iv.setY(y);
//			x += x;
//			g.getChildren().add(iv);
//			}
//		}
		
		//this.getChildren().add(g);
	}
	
	public void update() {
		this.setupInventoryBar(this.gr);
	}
}