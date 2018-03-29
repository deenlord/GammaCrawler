package com.gammacrawler.generator.test;

import javax.swing.JFrame;

import com.gammacrawler.generator.GamePanel;

public class GeneratorTest {
	public static JFrame frame; 
	public static GamePanel panel = new GamePanel(51, 51);

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
