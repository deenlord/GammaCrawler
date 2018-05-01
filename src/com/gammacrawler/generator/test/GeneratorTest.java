package com.gammacrawler.generator.test;

import javax.swing.JFrame;

import com.gammacrawler.StatusBar;
import com.gammacrawler.generator.Board;

public class GeneratorTest {
	public static JFrame frame; 
	public static Board board;
//	public static GamePanel panel;

	public static void main(String[] args) {
		board = new Board(51, 51, 21);
		board.addMaze(0);
//		panel = new GamePanel(board);

		int[][] iArray = board.getArray();
		for (int x = 0; x < iArray.length; x++) {
			for (int y = 0; y < iArray[0].length; y++) {
				StatusBar.addStatus(""+iArray[x][y]);
			}
		}

		frame = new JFrame();
//		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
