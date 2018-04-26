package com.gammacrawler.generator.populators;

import java.util.ArrayList;
import java.util.Collections;

import com.gammacrawler.Settings;
import com.gammacrawler.entity.Entity;
import com.gammacrawler.util.Point;

public class PopulatorGraveyard extends Populator {
	private static int attempts = 20;
	private static int height = 7;
	private static int width = 8;
	private static int[] wallOptions = new int[]{
		Settings.FLOOR_ID,
		Settings.STONES1_ID,
		Settings.STONES1_ID,
		Settings.STONES1_ID,
		Settings.STONES2_ID,
		Settings.STONES2_ID,
		Settings.STONES3_ID
	};
	private static int[] floorOptions = new int[]{
		Settings.STONES1_ID,
		Settings.STONES2_ID,
		Settings.STONES2_ID,
		Settings.STONES3_ID,
		Settings.STONES3_ID,
		Settings.STONES3_ID,
	};
	private static int[] emptyOptions = new int[]{
		Settings.SKULL_ID,
		Settings.FLOOR_ID,
	};

	public PopulatorGraveyard(int[][] tileArray, ArrayList<Entity> entities) {
		super(tileArray, entities);
	}

	class GraveSite {
		public int minX;
		public int minY;
		public int maxX;
		public int maxY;

		public GraveSite(int minX, int maxX, int minY, int maxY) {
			this.minX = minX;
			this.maxX = maxX;
			this.minY = minY;
			this.maxY = maxY;
		}
	}

	@Override
	public void populate() {
		for (int i = 0; i < attempts; i++) {

			// Choose a random point
			int x = (int) (Math.random() * ((tileArray.length - 2) - width)) + 1;
			int y = (int) (Math.random() * ((tileArray[0].length - 2) - height)) + 1;

			ArrayList<GraveSite> sites = getLocations();

			for (GraveSite site : sites) {
				makeGraveyard(site.minX, site.maxX, site.minY, site.maxY);
			}
		}
	}

	private ArrayList<GraveSite> getLocations() {
		ArrayList<GraveSite> sites = new ArrayList<>();

		// Iterate through each square
		Check:
		for (int x = 1; x < tileArray.length - 1; x++) {
			for (int y = 1; y < tileArray[0].length - 1; y++) {

				// If this is the top-left corner of a room
				if (tileArray[x][y] == Settings.FLOOR_ID &&
						tileArray[x - 1][y] == Settings.WALL_ID &&
						tileArray[x][y - 1] == Settings.WALL_ID &&
						tileArray[x + 1][y] == Settings.FLOOR_ID &&
						tileArray[x][y + 1] == Settings.FLOOR_ID &&
						tileArray[x + 1][y + 1] == Settings.FLOOR_ID) {

					// See how far each dimension is
					int tX = x;
					int tY = y;
					int lenX = 1;
					int lenY = 1;
					while (tileArray[tX + 1][y] == Settings.FLOOR_ID) {
						lenX ++;
						tX ++;
					}
					while (tileArray[x][tY + 1] == Settings.FLOOR_ID) {
						lenY ++;
						tY ++;
					}

					if (lenX < 5 || lenY < 5) {
						break Check;
					} else {
						sites.add(new GraveSite(x + 1, x + lenX - 2, y + 1, y + lenY - 2));
					}
				}
			}
		}

		return sites;
	}

	private void makeGraveyard(int minX, int maxX, int minY, int maxY) {
		makeWalls(minX, maxX, minY, maxY);
		makeSkulls(minX, maxX, minY, maxY);
	}

	private void makeSkulls(int minX, int maxX, int minY, int maxY) {
		int random;
		ArrayList<Point> points = new ArrayList<>();

		for (int x = minX + 1; x < maxX; x++) {
			for (int y = minY + 1; y < maxY; y++) {
				points.add(new Point(x, y));
			}
		}

		Collections.shuffle(points);

		for (Point p : points) {
			if (!doesBlockRoute(p, 0)) {
				random = (int) (Math.random() * floorOptions.length);
				tileArray[p.x][p.y] = floorOptions[random];
			}
		}

		for (Point p : points) {
			if (tileArray[p.x][p.y] == Settings.FLOOR_ID) {
				random = (int) (Math.random() * emptyOptions.length);
				tileArray[p.x][p.y] = emptyOptions[random];
			}
		}
	}

	private void makeWalls(int minX, int maxX, int minY, int maxY) {
		int random;

		tileArray[minX][minY] = Settings.STONES1_ID;
		tileArray[minX][maxY] = Settings.STONES1_ID;
		tileArray[maxX][minY] = Settings.STONES1_ID;
		tileArray[maxX][maxY] = Settings.STONES1_ID;

		for (int x = minX + 1; x < maxX; x++) {
			random = (int) (Math.random() * wallOptions.length);
			tileArray[x][minY] = wallOptions[random];
			tileArray[x][maxY] = wallOptions[random];
		}

		for (int y = minY + 1; y < maxY; y++) {
			random = (int) (Math.random() * wallOptions.length);
			tileArray[minX][y] = wallOptions[random];
			tileArray[maxX][y] = wallOptions[random];
		}
	}

	private boolean isRoom(int minX, int maxX, int minY, int maxY) {

		// Test to see if there is room
		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {
				if (getOctNeighborCount(new Point(x, y), 0, true) < 8) {
					return false;
				}
			}
		}
		return true;
	}

}
