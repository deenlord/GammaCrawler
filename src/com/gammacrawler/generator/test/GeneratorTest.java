package com.gammacrawler.generator.test;

import javax.swing.JFrame;

import com.gammacrawler.generator.Board;

public class GeneratorTest {
	public static JFrame frame; 
	public static Board board;
//	public static GamePanel panel;

	public static void main(String[] args) {
		board = new Board(51, 51);
		board.addMaze();
//		panel = new GamePanel(board);

		int[][] iArray = board.getArray();
		for (int x = 0; x < iArray.length; x++) {
			for (int y = 0; y < iArray[0].length; y++) {
				System.out.println(iArray[x][y]);
			}
		}

		frame = new JFrame();
//		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
