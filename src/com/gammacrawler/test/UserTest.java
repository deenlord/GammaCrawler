package com.gammacrawler.test;
import com.gammacrawler.*;

/**
 * @author deenlord
 * 3/24
 */
public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// User deen = new User(); // not calling other constructor...
		
		User deen = new User("Deenski");
		
		System.out.println(deen.toString());
		
		deen.setName("Deenlord");
		
		deen.setHP(65);
		
		//deen.increaseExp(10);
		
		deen.move(Direction.NORTH);
		
		deen.move(Direction.NORTH);
		
		System.out.println(deen.toString());
		
		// direction is not incrementing location coordinates for some reason
		// #deenlord 3/24
	}

}
