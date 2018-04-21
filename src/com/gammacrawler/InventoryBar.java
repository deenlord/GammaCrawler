package com.gammacrawler;

import com.gammacrawler.entity.Item;
import com.gammacrawler.item.Weapon;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class InventoryBar extends Pane {
	Group g;
	int x = Settings.TILESIZE;
	int y = (Settings.TILESIZE * 2);
	public InventoryBar() {
		this.setPrefSize(615, 32);
		g = new Group();
		g.getChildren().add(this);
	}
	
	public void update() {
		for (Item i : Generator.player.getInventory()) {
			if (i instanceof Weapon) {
				break;
			}
			else {
				i.getImageView().setLayoutX(x);
				i.getImageView().setLayoutY(y);
				x = x + Settings.TILESIZE;
				this.getChildren().add(i.getImageView());
			}
		}
	}
}
