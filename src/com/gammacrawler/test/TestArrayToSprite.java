package com.gammacrawler.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.gammacrawler.ArrayToSprite;
import com.gammacrawler.Sprite;

class TestArrayToSprite {

	int[][] intArray = {{0,0}, {1,0}, {0,1}, {1,1}};

	@Test
	void testArrayToSprite() {
		ArrayToSprite arr = new ArrayToSprite(intArray);
		Sprite[][] spr = arr.getSprites();
		
		for (Sprite[] i : spr) {
			System.out.println(i);
		}
	}

}
