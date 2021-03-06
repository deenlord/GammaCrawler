package com.gammacrawler.generator.map.connector;

import java.util.ArrayList;

/**
 * @author WolfieWaffle
 *
 */
public class ConnectorBucket {
	int regionOne;
	int regionTwo;
	private ArrayList<DungeonConnector> connectors = new ArrayList<>();

	/**
	 * @param regionIDOne - int
	 * @param regionIDTwo - int
	 */
	public ConnectorBucket(int regionIDOne, int regionIDTwo) {
		this.regionOne = regionIDOne;
		this.regionTwo = regionIDTwo;
	}

	/**
	 * @return connectors
	 */
	public ArrayList<DungeonConnector> getConnectors() {
		return connectors;
	}

	/**
	 * @param regionIDOne - first number
	 * @param regionIDTwo - second number
	 * @return Boolean - if true, numbers match forwards or backwards
	 */
	public boolean fits(int regionIDOne, int regionIDTwo) {
		if (regionIDOne == regionOne) {
			if (regionIDTwo == regionTwo) {
				return true;
			}
		} else if (regionIDOne == regionTwo) {
			if (regionIDTwo == regionOne) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sorts an arrayList of connectors
	 * @param connectors - connectors to be sorted
	 * @return connectors, sorted by which regions they connect
	 */
	public static ArrayList<ConnectorBucket> getSortedList(ArrayList<DungeonConnector> connectors) {
		ArrayList<ConnectorBucket> buckets = new ArrayList<>();
		boolean hasBucket = false;

		// Here we iterate through the existing list of buckets to see if this
		// connector fits anywhere. If not we create a new one.
		for (DungeonConnector connector : connectors) {
			for (ConnectorBucket bucket : buckets) {
				hasBucket = false;
				if (bucket.fits(connector.getRegionIDOne(), connector.getRegionIDTwo())) {
					bucket.getConnectors().add(connector);
					hasBucket = true;
					break;
				}
			}
			if (!hasBucket) {
				buckets.add(new ConnectorBucket(connector.getRegionIDOne(), connector.getRegionIDTwo()));
			}
		}
		return buckets;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{");
		if (connectors.size() > 0) {
			sb.append(connectors.get(0));
			for (int i = 1; i < connectors.size(); i++) {
				sb.append(connectors.get(i));
				sb.append(" ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

}
