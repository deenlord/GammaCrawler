package com.gammacrawler;

import com.gammacrawler.generator.Board;

public class Generator {
	User player;
	Board board;
	
	public Generator() {
		this.player = new User("Richard");
		this.board = new Board(21,21);
		
	}
	
	public Generator(Sprite sprite) {
		this.player = new User("Richard");
		this.player.setSprite(sprite);
		this.board = new Board(21,21);
	}
	
	public Generator(User player) {
		this.player = player;
		if (player.getExp() < 100)
			this.board = new Board(21, 21);
		else if(player.getExp() > 100 && player.getExp() < 500)
			this.board = new Board(45,51);
		else
			this.board = new Board(55,55);

	}
	
	public Generator(User player, Board board) {
		this.player = player;
		this.board = board;

	}
	
	
	public User getPlayer() {
		return this.player;
	}
	
	public Board getBoard() {
		return this.board;
	}
}
