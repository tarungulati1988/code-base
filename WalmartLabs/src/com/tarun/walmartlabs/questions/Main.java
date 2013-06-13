/**
 * 
 */
package com.tarun.walmartlabs.questions;
import com.tarun.walmartlabs.questions.*;
/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreeNumbersSumToZero tnsz = new ThreeNumbersSumToZero();
		int[] num = {1,-4,8,19,1999,-98,-34, 90, 100, 1000, -1100, -91};
		tnsz.calculateSumUsingBruteForce(num);
		
		System.out.println("---------------------------------------------------------");
		tnsz.calculateSumImprovedO(num);
		
		System.out.println("---------------------------------------------------------");
		tnsz.calculateSumBestO(num);
		
		System.out.println("---------------------------------------------------------");
		MaximumProduct mpVar = new MaximumProduct();
		mpVar.maxProduct(num);
		
		System.out.println("---------------------------------------------------------");
		ClosestPerfectSquare cpsVar = new ClosestPerfectSquare();
		cpsVar.findClosestSquareRoot(149);
		
	}

}
