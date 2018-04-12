package com.gammacrawler;



/**
 * @author jakev
 *
 */
public class HealthPotion extends Potion {
	private static final String name = "Health Potion";
	private static final int value = 25;
	private static final Sprite sprite = new Sprite("file:src/com/cammacralwer/images/smallhealthpotion.png");
	
	public HealthPotion() {
		super(name, sprite, value);
	}

	@Override
	public void drink(Character c) {
		int val = c.getHP();
		int max = c.getMaxHP();
		int returnMe = val + value;
		int index = 0;
		
		if (returnMe > max) {
			returnMe = max;
		}
		
		for (int i = 0; i < c.inventory.size(); i++) {
			Item in = c.inventory.get(i);
			if(in.getName().equals("HealthPotion")) {
				index  = i;
				c.inventory.remove(index);
			}
		}
		
		c.setHP(returnMe);
		System.out.println("Health Potion Applied: User HP = " + c.getHP());
	
	}
}


