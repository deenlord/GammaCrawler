package com.gammacrawler.test;

import com.gammacrawler.ArrayToSprite;
import com.gammacrawler.Sprite;

class TestArrayToSprite {

	int[][] intArray = {{0,0}, {1,0}, {0,1}, {1,1}};

	@org.junit.Test
	void testArrayToSprite() {
		ArrayToSprite arr = new ArrayToSprite(intArray);
		Sprite[][] spr = arr.getSprites();
		
		for (Sprite[] i : spr) {
			System.out.println(i);
		}
	}

}
