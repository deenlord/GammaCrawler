package com.gammacrawler.generator.populators;

import java.util.ArrayList;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

public class PopulatorStair extends Populator {

	public PopulatorStair(int[][] tileArray, ArrayList<Entity> entities)
	{
		super(tileArray, entities);
	}

	@Override
	public void populate() {
		int[] emptySpace=getRandomFreeSpace();
		if(emptySpace!=null) {
			Point p = new Point(emptySpace[0], emptySpace[1]);
			if(checkSpace(p,tileArray)) {
				tileArray[p.x][p.y] = Settings.STAIR_ID;}
			else populate();
		}
	}
	
	public boolean checkSpace(Point point, int[][] tileArray)
	{
		int direction=0;
		int[] checkPoint= {point.x,point.y};
		boolean freeSpace=true;
		for(int iterator=0;iterator<direction;iterator++) {
			switch(direction)
			{
				case 0:
					checkPoint[1]--;
					break;
				case 1:
					checkPoint[0]++;
					break;
				case 2:
					checkPoint[1]++;
					break;
				case 3:
					checkPoint[0]--;
					break;
				case 4:
					checkPoint[1]--;
					checkPoint[0]--;
					break;
				case 5:
					checkPoint[1]++;
					checkPoint[0]++;
					break;
				case 6:
					checkPoint[1]--;
					checkPoint[0]++;
					break;
				case 7:
					checkPoint[1]++;
					checkPoint[0]--;
					break;
				default:
					break;
			}
			if(tileArray[checkPoint[0]][checkPoint[1]] == Settings.WALL_ID) freeSpace=false;
			checkPoint[0]=point.x;
			checkPoint[1]=point.y;
		}
		return freeSpace;
			
	}
}
