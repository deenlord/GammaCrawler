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
		//Create a user
		User deen = new User("Deenski");
		
		//create a status bar for the user
		StatusBar.addStatus(deen.toString());
		
		//set userName
		deen.setName("Deenlord");
		
		//set user health
		deen.setHP(65);
		
		//move user
		deen.move(Direction.NORTH);
		deen.move(Direction.NORTH);
		
		//add the status of the user
		StatusBar.addStatus(deen.toString());
		
		// direction is not incrementing location coordinates for some reason
		// #deenlord 3/24
	}

}
