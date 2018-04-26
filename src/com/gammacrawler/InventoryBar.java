package com.gammacrawler;

import java.util.ArrayList;

import com.gammacrawler.entity.Item;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author jakev, crathke4, nathaniel
 *
 */
public class InventoryBar extends Pane {

	public InventoryBar() {
		this.setupInventoryBar();
	}

	public void setupInventoryBar() {
		this.getChildren().clear();
		ArrayList<Item> inv = Generator.player.getInventory();

		for (int i = 0; i < inv.size(); i++) {
			ImageView iv = inv.get(i).getSprite().getNewImageView();
			iv.setX(i * Settings.TILESIZE);
			iv.setY(Settings.TILESIZE);

			this.getChildren().add(iv);
		}
	}

	public void update() {
		this.setupInventoryBar();
	}

}