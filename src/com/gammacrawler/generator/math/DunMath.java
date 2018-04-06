package com.gammacrawler.generator.math;

public class DunMath {

	public static int randomOdd(int min, int max) {
		if (max % 2 == 0)
			max--;
		if (min % 2 == 0)
			min++;
		return min + 2 * (int) (Math.random() * ((max - min) / 2 + 1));
	}

}
