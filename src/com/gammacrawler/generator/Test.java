package com.gammacrawler.generator;

import javax.swing.JFrame;

public class Test {
	public static JFrame frame; 
	public static OrthelloPanel panel = new OrthelloPanel();

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setContentPane(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
