package com.gammacrawler;

public class ArrayToSprite {
	protected int[][] intArray;
	protected Sprite[][] sprArray;
	protected Sprite sp0;
	protected Sprite sp1;
	
	public ArrayToSprite(int[][] ar) {
		this.intArray = ar;
		this.sprArray = new Sprite[ar[0].length][ar[1].length];
		this.sp0 = new Sprite(0);
		this.sp1 = new Sprite(1);
		
		this.initArray();
		this.getSprites();
	}
	
	public void initArray() {
		int counter = 0;
		for (int i=0; i<=intArray[0].length; i++) {
			if (intArray[i][counter] == 0) {
				sprArray[i][counter] = sp0;
			}
			else if (intArray[i][counter] == 1) {
					sprArray[i][counter] = sp1;
			}
			else {
				System.out.println("Error: int not found at " + counter);
			}
			
			if (i == intArray[0].length) {
				i = 0;
				counter ++;
			}
		}
	}
	
	public Sprite[][] getSprites() {
		return this.sprArray;
	}
}
