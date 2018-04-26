package com.gammacrawler.test;
import com.gammacrawler.Direction;
import com.gammacrawler.StatusBar;
import com.gammacrawler.entity.User;

/**
 * @author deenlord
 * 3/24
 */
public class UserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// User deen = new User(); // not calling other constructor...
		
		User deen = new User("Deenski");
		
		StatusBar.addStatus(deen.toString());
		
		deen.setName("Deenlord");
		
		deen.setHP(65);
		
		//deen.increaseExp(10);
		
		deen.move(Direction.NORTH);
		
		deen.move(Direction.NORTH);
		
		StatusBar.addStatus(deen.toString());
		
		// direction is not incrementing location coordinates for some reason
		// #deenlord 3/24
	}

}
