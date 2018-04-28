package com.gammacrawler.item;

import com.gammacrawler.Direction;
import com.gammacrawler.Generator;
import com.gammacrawler.entity.Character;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.entity.Item;
import com.gammacrawler.entity.Sprite;

/**
 * <h3>GhostPotion - a Potion</h3>
 *  <p> name = "Ghost Potion"
 *  <br> value = 50
 *  <br> allows user to walk through walls for 3 moves.
 *  <br> User dies if they land in a wall.
 *  @author wolfiewaffle
 */
public class GhostPotion extends Potion {
	private static final String name = "Ghost Potion";
	private static final int value = 50;
	private static final Sprite sprite = new Sprite("file:src/com/gammacrawler/images/ghostpotion.png");
	
	public GhostPotion() {
		super(name, sprite, value);
	}

	@Override
	public void use(Character c) {
		int index  = 0;
		// init counter which allows User to occupy TILE_IDs > 10
		// Set the player's opacity to make them look like a ghost
		// 
		Generator.player.invisibleTurns = 3;
		Generator.player.getImageView().setOpacity(0.5);

		c.getInventory().remove(this);
	}

	@Override
	public void collide(Entity e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}

}
