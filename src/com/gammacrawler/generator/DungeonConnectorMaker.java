package com.gammacrawler.generator;

import java.util.ArrayList;

public class DungeonConnectorMaker {

	/**
	 * Returns a list of DungeonConnectors, each with an x and y position and the 2 regiongs they connect.
	 * @param regionArray An integer array describing the regionID of each tile.
	 * @return An ArrayList of DungeonConnectors
	 */
	public static ArrayList<DungeonConnector> getConnectors(int[][] regionArray) {
		ArrayList<DungeonConnector> connectors = new ArrayList<>();

		for (int x = 0; x < regionArray.length; x++) {
			for (int y = 0; y < regionArray[0].length; y++) {

				// This will return either a connector or null, depending on if
				// this tile has a valid connection.
				DungeonConnector connector = connector(x, y, regionArray);
				if (!(connector == null)) {
					connectors.add(connector);
				}
			}
		}
		return connectors;
	}

	/**
	 * Returns a connector with this x and y and proper regions, or null.
	 * @param x The x coord of the tile that connects 2 regions.
	 * @param y The y coord of the tile that connects 2 regions.
	 * @param regionArray The array showing the regions of each tile.
	 * @return DungeonConnector or null if this does not connect 2 separate regions.
	 */
	private static DungeonConnector connector(int x, int y, int[][] regionArray) {
		int regionOne = 0;
		int regionTwo;

		if (x > 0) {
			if (regionOne == 0)
				regionOne = regionArray[x - 1][y];
			else {
				regionTwo = regionArray[x - 1][y];
				if (regionOne != regionTwo && regionTwo != 0) {
					return new DungeonConnector(x, y, regionOne, regionTwo);
				}
			}
		}
		if (x < regionArray.length - 1) {
			if (regionOne == 0)
				regionOne = regionArray[x + 1][y];
			else {
				regionTwo = regionArray[x + 1][y];
				if (regionOne != regionTwo && regionTwo != 0) {
					return new DungeonConnector(x, y, regionOne, regionTwo);
				}
			}
		}
		if (y > 0) {
			if (regionOne == 0)
				regionOne = regionArray[x][y - 1];
			else {
				regionTwo = regionArray[x][y - 1];
				if (regionOne != regionTwo && regionTwo != 0) {
					return new DungeonConnector(x, y, regionOne, regionTwo);
				}
			}
		}
		if (y < regionArray[0].length - 1) {
			if (regionOne == 0)
				regionOne = regionArray[x][y + 1];
			else {
				regionTwo = regionArray[x][y + 1];
				if (regionOne != regionTwo && regionTwo != 0) {
					return new DungeonConnector(x, y, regionOne, regionTwo);
				}
			}
		}
		return null;
	}

}
