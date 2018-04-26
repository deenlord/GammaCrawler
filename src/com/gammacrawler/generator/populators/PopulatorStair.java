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
			tileArray[p.x][p.y] = Settings.STAIRS_ID;
		}
	}
}
