/**
 * 
 * Given a number, find closest perfect square to the number?
 * 
 */
package com.tarun.walmartlabs.questions;

/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class ClosestPerfectSquare {
	private int num;
	
	public void findClosestSquareRoot(int x){
		this.num = x;
		double y = Math.sqrt(num); //calculate square root
		double floorY = Math.floor(y); //calculate floor
		System.out.println("The closest square root is: " + floorY * floorY); //calculate square of the floor to get the closest square root
	}
}
