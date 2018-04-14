package com.gammacrawler.entity;

import java.util.ArrayList;

import com.gammacrawler.Direction;
import com.gammacrawler.Settings;
import com.gammacrawler.util.MoveRequest;

public class EnemySlime extends Enemy {
	
	private static final String name = "Slime";
	protected static final Sprite sprite = new Sprite("file:src/com/gammacrawler/images/slime.png");
	private static final int damage = 5;
	
	public EnemySlime() {
		super(name, sprite);
		this.setHP(10);
		this.setXP(5);
	}
	
	public static int getDamage() {
		return damage;
	}
	

	@Override
	public ArrayList<MoveRequest> getMovePossibilities() {
		ArrayList<MoveRequest> mq = new ArrayList<>();
		mq.add(new MoveRequest(getLocation()[0] - Settings.TILESIZE, getLocation()[1], this));
		mq.add(new MoveRequest(getLocation()[0] + Settings.TILESIZE, getLocation()[1], this));
		mq.add(new MoveRequest(getLocation()[0], getLocation()[1] - Settings.TILESIZE, this));
		mq.add(new MoveRequest(getLocation()[0], getLocation()[1] + Settings.TILESIZE, this));
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

	@Override
	public void move(Direction dir) {
		// TODO Auto-generated method stub
		switch(dir)	 {
		case EAST:
			this.setLocation(this.getLocation()[0] +1, this.getLocation()[1]);
			break;
		case WEST:
			this.setLocation(this.getLocation()[0] -1, this.getLocation()[1]);
			break;
		case NORTH:
			break;
		case SOUTH:
			break;
		default:
			break;
		}
			
	}




}
