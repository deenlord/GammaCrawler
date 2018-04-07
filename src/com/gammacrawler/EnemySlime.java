package com.gammacrawler;

import java.util.ArrayList;

public class EnemySlime extends Enemy {
	int isCharged;

	@Override
	public ArrayList<MoveRequest> getMovePossibilities() {
		ArrayList<MoveRequest> mq = new ArrayList<>();
		mq.add(new MoveRequest(getLocation()[0] - Main.tileSize, getLocation()[1], this));
		mq.add(new MoveRequest(getLocation()[0] + Main.tileSize, getLocation()[1], this));
		mq.add(new MoveRequest(getLocation()[0], getLocation()[1] - Main.tileSize, this));
		mq.add(new MoveRequest(getLocation()[0], getLocation()[1] + Main.tileSize, this));
		return mq;
	}

	@Override
	public MoveRequest getMoveRequest(ArrayList<MoveRequest> possibilities) {
		ArrayList<Direction> directions = new ArrayList<>();
		Direction tryDir;
		directions.add(Direction.NORTH);
		directions.add(Direction.SOUTH);
		directions.add(Direction.EAST);
		directions.add(Direction.WEST);

		while (directions.size() > 0) {
			tryDir = directions.get((int) (Math.random() * directions.size()) + 1);

			switch (tryDir) {
			case NORTH:
				
				break;
			case SOUTH:
				break;
			case EAST:
				break;
			case WEST:
				break;
			}
		}

		return null;
	}

	@Override
	public MoveRequest handleMoveRequestFailed() {
		// TODO Auto-generated method stub
		return null;
	}

}
