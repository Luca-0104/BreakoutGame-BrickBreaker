package gameComponents;

import java.util.Random;

public class RandomSpeed extends Random {
	
	double xSpeed;
	double ySpeed;

	public RandomSpeed() {
		SpeedFactory();
	}
	
	private double getRandomX() {
		double rsx;
		
		/*
		// return a random speed for x direction, which can be both a negative integer or a positive integer  
		double[] speed = {1, 2, 3, -1, -2, -3};
		int i = nextInt(6);
		rsx = speed[i];
		*/
		
		double[] speed = {2.0, -2.0};
		int i = nextInt(2);
		rsx = speed[i];
		
		System.out.println("x: " + rsx);
		
		return rsx;
	}
	
	private double getRandomY() {
		
		/*
		double rsy;
		// return a random speed for y direction, which can be only a negative integer
		double[] speed = {1, 2, 3};
		int i = nextInt(3);
		rsy = speed[i];
		// to make sure the range of the angle is from -45 to +45 
		while(rsy < Math.abs(rsx)) {
			i = nextInt(3);
			rsy = speed[i];
		}
		*/
		
		double rsy = 2.0;
		
		System.out.println("y: " + (-rsy));
		
		return -rsy;
	}
	
	/*
	private void unitizeSpeed(double xSpeed, double ySpeed) {
		
		// change (xSpeed, ySpeed) into unit vector
		double e = Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
		xSpeed = xSpeed / e;
		ySpeed = ySpeed / e;

		System.out.println("e: " + e);
		System.out.println("X: " + xSpeed);
		System.out.println("Y: " + ySpeed);
		double a = Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2);
		System.out.println("unit v： " + a);
		
	}
	*/
	
	private void SpeedFactory() {
		xSpeed = getRandomX();
		ySpeed = getRandomY();
//		unitizeSpeed(xSpeed, ySpeed);  // ABANDONED!
	}
	
	public double getUnitizedRandomX() {
		return xSpeed;
	}
	
	public double getUnitizedRandomY() {
		return ySpeed;
	}
	
}
