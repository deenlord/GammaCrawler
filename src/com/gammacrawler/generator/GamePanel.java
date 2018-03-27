package com.gammacrawler.generator;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.gammacrawler.generator.math.DunMath;

public class GamePanel extends JPanel {
	/**
	 * Default ID it wanted me to add.
	 */
	private static final long serialVersionUID = 1L;
	Board board;

	public GamePanel(int width, int height) {
		board = new Board(width, height);
		this.setPreferredSize(new Dimension(800, 800));

		board.addMaze();
	}

	@Override
	public void paint(Graphics g) {
		board.paint(g);
	}

}
