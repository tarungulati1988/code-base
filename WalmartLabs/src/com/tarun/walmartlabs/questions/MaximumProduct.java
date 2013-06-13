/**
 * 
 * Given an array of both positive and negative numbers, find the contiguous
 * range in the array which gives the maximum product. Give an algorithm
 * which runs in O(N).
 * 
 */
package com.tarun.walmartlabs.questions;

/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class MaximumProduct {
	
	public void maxProduct(int[] arr) { 

		boolean isNegative = false; 
		int maxproduct = 1; 
		int maxProductTemp = 1; 
		int negativeProduct = 1; 
		for (int i = 0; i < arr.length; i++){ 
			if (arr[i] < 0){// negative 
				isNegative = true; 
				negativeProduct=maxProductTemp; 
				negativeProduct *= arr[i]; 
				if (maxproduct < maxProductTemp){ 
					maxproduct = maxProductTemp; 
					} 
				maxProductTemp = 1; 
			} else if (arr[i] > 0) {// positive 
				if (isNegative) { 
					negativeProduct *= arr[i]; 
					} 
				maxProductTemp *= arr[i]; 
			} else {// 0 
				if (maxproduct < maxProductTemp) { 
						maxproduct = maxProductTemp; 
					} 
				maxProductTemp = 1; 
				} 
		} 
		maxProductTemp = (maxProductTemp > negativeProduct) ? maxProductTemp : negativeProduct; 
		maxproduct = (maxProductTemp > maxproduct) ? maxProductTemp : maxproduct; 
		System.out.println(" Max contiguous product in O(n) is: " + maxproduct); 
		
		/*long maxprod = -1;
		long prod = 1;

		for(int i=0;i<arr.length;++i)
		{
			if(arr[i] == 0)
				prod = 1;
			else
			{	
				System.out.print("\nprevious prod: " + prod + "  ");
				prod *= arr[i];	
				maxprod = maxprod > prod ? maxprod : prod;
				System.out.print("prod = " +prod + ", maxprod : " +maxprod+ " , arr[i] = " +arr[i]);
			}
		}
		System.out.println(maxprod);*/
	}
}
