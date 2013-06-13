/**
 * 
 * O(nlogn) in best, worst and average case. It is highly parallelizable and good for large data
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */

package com.tarun.sort.merge;

import java.util.Arrays;
import java.util.Arrays.*;

public class MergeSort {
	private int[] numbers;
	
	public void mergesort(int[] num, int first, int n){
		int firstHalf, secondHalf;
		this.numbers = Arrays.copyOf(num, num.length);
		if(n > 1){
			firstHalf = n/2;
			secondHalf = n - firstHalf;
			
			mergesort(numbers, first, firstHalf); //sort first element to mid
			mergesort(numbers, first + firstHalf, secondHalf); //sort mid to last element
			//merge the two sorted arrays
			merge(numbers, first, firstHalf, secondHalf);
		}
	}
	
	private void merge(int[] num, int first, int n, int m){
		int[] temp = new int[n + m];
		int copied  = 0; // Number of elements copied from data to temp
	    int copied1 = 0; // Number copied from the first half of data
	    int copied2 = 0; // Number copied from the second half of data
	    int i;           // Array index to copy from temp back into data
	
	    // Merge elements, copying from two halves of data to the temporary array.
	    while ((copied1 < n) && (copied2 < m)){
	    	if (num[first + copied1] < num[first + n + copied2])
	    		temp[copied++] = num[first + (copied1++)];
	    	else
	    		temp[copied++] = num[first + n + (copied2++)];
	    }
	
	    // Copy any remaining entries in the left and right subarrays.
	    while (copied1 < n)
	    	temp[copied++] = num[first + (copied1++)];
	    while (copied2 < m)
	    	temp[copied++] = num[first + n + (copied2++)];
	
	    for (i = 0; i < n + m; i++)
	    	num[first + i] = temp[i];
		
	}
	
	public void show(){
		for(int i : numbers){
			System.out.print("  " + i);
		}
	}

}
