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
	int[][] array = new int[301][301];
	Board board;

	public GamePanel() {
		initArray();

		board = new Board(array);
		this.setPreferredSize(new Dimension(800, 800));
		board.makeMaze();
		board.clearArray(array);
	}

	private void initArray() {
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				array[x][y] = 1;
			}
		}

		attemptPlaceRooms(1000, 3, 20, 3, 20);
	}

	@Override
	public void paint(Graphics g) {
		board.paintArray(g, 800 / array.length, array);
	}

	// TODO: This javadoc
	/**
	 * Attempt to place randomized white rooms. Size parameters will be made odd
	 * by adding 1 if they are even.
	 * 
	 * @param attempts
	 * @param minRoomWidth
	 * @param maxRoomWidth
	 * @param minRoomHeight
	 * @param maxRoomHeight
	 */
	public void attemptPlaceRooms(int attempts, int minRoomWidth, int maxRoomWidth, int minRoomHeight,
			int maxRoomHeight) {
		int xPointMin;
		int xPointMax;
		int yPointMin;
		int yPointMax;
		int xPoint;
		int yPoint;
		int thisRoomWidth;
		int thisRoomHeight;

		xPointMin = 1;
		yPointMin = 1;

		// Make any even numbers odd.
		minRoomWidth = (minRoomWidth % 2 == 0 ? minRoomWidth + 1 : minRoomWidth);
		maxRoomWidth = (maxRoomWidth % 2 == 0 ? maxRoomWidth + 1 : maxRoomWidth);
		minRoomHeight = (minRoomHeight % 2 == 0 ? minRoomHeight + 1 : minRoomHeight);
		maxRoomHeight = (maxRoomHeight % 2 == 0 ? maxRoomHeight + 1 : maxRoomHeight);

		for (int i = 0; i < attempts; i++) {
			thisRoomWidth = DunMath.randomOdd(minRoomWidth, maxRoomWidth);
			thisRoomHeight = DunMath.randomOdd(minRoomHeight, maxRoomHeight);

			// Account for room width
			xPointMax = array.length - thisRoomWidth - 1;
			yPointMax = array[0].length - thisRoomHeight - 1;

			xPoint = DunMath.randomOdd(xPointMin, xPointMax);
			yPoint = DunMath.randomOdd(yPointMin, yPointMax);

			makeRoom(xPoint, yPoint, thisRoomWidth, thisRoomHeight);
		}
	}

	/**
	 * Fills in a rectangle with empty space.
	 * 
	 * @param pointX
	 *            Rectangle x point.
	 * @param pointY
	 *            Rectangle y point.
	 * @param width
	 *            Rectangle width.
	 * @param height
	 *            Rectangle height.
	 */
	private void makeRoom(int pointX, int pointY, int width, int height) {

		// Check to make sure no intersection with existing open spaces
		if (isSpace(pointX, pointY, width, height)) {
			for (int x = pointX; x < pointX + width; x++) {
				for (int y = pointY; y < pointY + height; y++) {
					array[x][y] = 0;
				}
			}
		}
	}

	private boolean isSpace(int pointX, int pointY, int width, int height) {
		for (int x = pointX; x < pointX + width; x++) {
			for (int y = pointY; y < pointY + height; y++) {
				if (array[x][y] == 0 || octNeighborCount(0, x, y) > 0)
					return false;
			}
		}
		return true;
	}

	private int octNeighborCount(int num, int x, int y) {
		int count = 0;

		for (int cx = x - 1; cx < x + 2; cx++) {
			for (int cy = y - 1; cy < y + 2; cy++) {

				// If out of bounds count as a neighbor
				if (cx < 0 || cx >= array.length) {
					count++;
				} else if (array[cx][cy] == num) {
					count++;
				}
			}
		}
		return count;
	}

}
