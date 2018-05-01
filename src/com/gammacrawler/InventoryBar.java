package com.gammacrawler;

import java.util.ArrayList;

import com.gammacrawler.entity.Item;
import com.gammacrawler.item.Weapon;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author jakev, crathke4, nathaniel
 *
 */
public class InventoryBar extends Pane {

	/**
	 * Creates an inventory bar
	 */
	public InventoryBar() {
		this.setupInventoryBar();
		this.setPrefSize(Settings.TILESIZE * 9, Settings.TILESIZE);
	}

	/**
	 * updates the inventory bar to reflect the players inventory
	 */
	public void setupInventoryBar() {
		this.getChildren().clear();
		ArrayList<Item> inv = Generator.player.getInventory();

		for (int i = 0; i < inv.size(); i++) {
			
			
			
				ImageView iv = inv.get(i).getSprite().getNewImageView();
				iv.setX(i * Settings.TILESIZE);
				iv.setY(Settings.TILESIZE);
	
				this.getChildren().add(iv);
				
				if (inv.get(i) instanceof Weapon) {
					iv.setVisible(false);
				}
			}
		}

	/**
	 * updates the inventory bar to reflect the players inventory
	 */
	public void update() {
		this.setupInventoryBar();
	}

}