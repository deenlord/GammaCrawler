package com.gammacrawler.generator.map.connector;

import java.util.ArrayList;

public class ConnectorBucket {
	int regionOne;
	int regionTwo;
	private ArrayList<DungeonConnector> connectors = new ArrayList<>();

	public ConnectorBucket(int regionIDOne, int regionIDTwo) {
		this.regionOne = regionIDOne;
		this.regionTwo = regionIDTwo;
	}

	public ArrayList<DungeonConnector> getConnectors() {
		return connectors;
	}

	/**
	 * Returns if the numbers match either forwards or backwards.
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
	 * Takes a list of DungeonConnectors and puts them in a list of buckets,
	 * sorting them by what regions they connect.
	 * 
	 * @param connectors
	 *            The big list of connectors to sort.
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
