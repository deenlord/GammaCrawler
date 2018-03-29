package com.gammacrawler.test;
/**
 * @author crathke4
 */
import java.awt.Point;
import java.util.Scanner;

public class inputMover {

	private int[][] board=new int[4][4];
	private Point coordinates=new Point();
	/**
	 * Makes a board in the shape of a doughnut, with four empty "tiles"
	 */
	public void makeDoughnut()
	{
		//Set outside walls (walls are one values) and empty space (spaces are zero values)
		board=new int[][] {{1,1,1,1},
						   {1,0,0,1},
						   {1,0,0,1},
						   {1,1,1,1}};
		//Set object location
		coordinates.setLocation(1, 1);
	}
	
	/**
	 * Scans for input from user, checks if move is valid, and resets the coordinates of the point.
	 */
	public void move()
	{
		Scanner scn=new Scanner(System.in);
		System.out.println("Coordinates: "+coordinates);
		
		boolean keepGoing=true;
		while(keepGoing) {
			String choice=scn.next();
			switch(choice)
			{
				case "w":
					if(check(choice)) {
					coordinates.setLocation(coordinates.getX(), coordinates.getY()-1);}
					break;
				case "s":
					if(check(choice)) {
					coordinates.setLocation(coordinates.getX(), coordinates.getY()+1);}
					break;
				case "a":
					if(check(choice)) {
					coordinates.setLocation(coordinates.getX()-1, coordinates.getY());}
					break;
				case "d":
					if(check(choice)) {
					coordinates.setLocation(coordinates.getX()+1, coordinates.getY());}
					break;
				case "q":
					keepGoing=false;
					break;
				default:
					System.out.println("w, s, a, or d");
					break;
			}
			System.out.println("New Coordinates: "+coordinates);
		}
	}
	
	/**
	 * Checks whether point can be moved in the desired direction (choice)
	 * @param choice - directional input from user to be checked
	 * @return true if move is valid, false if not.
	 */
	public boolean check(String choice)
	{
		
		boolean moveValid=true;
		switch(choice)
		{
		case "w":
			if(board[(int) (coordinates.getY()-1)][(int) coordinates.getX()]==1){
				moveValid=false;
				System.out.println("You cannot go that way!");
				};
			break;
		case "s":			
			if(board[(int) (coordinates.getY()+1)][(int) coordinates.getX()]==1){
				moveValid=false;
				System.out.println("You cannot go that way!");
				};
			break;
		case "a":
			if(board[(int) (coordinates.getY())][(int) coordinates.getX()-1]==1){
				moveValid=false;
				System.out.println("You cannot go that way!");
				};
			break;
		case "d":
			if(board[(int) (coordinates.getY())][(int) coordinates.getX()+1]==1){
				moveValid=false;
				System.out.println("You cannot go that way!");
				};
			break;
		}
		return moveValid;
	}
	
	//TEST CODE
	public static void main(String[] args)
	{
		inputMover move=new inputMover();
		move.makeDoughnut();
		move.move();
	}
}
