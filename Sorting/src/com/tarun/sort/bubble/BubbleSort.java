/**
 * 
 * O(n) is the best case and O(n^2) is the average and worst case 
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */

package com.tarun.sort.bubble;

import java.util.Arrays;
import java.util.Arrays.*;

public class BubbleSort {
	private int numbers[];
	
	public void sort(int[] arrayToSort){
		this.numbers = Arrays.copyOf(arrayToSort, arrayToSort.length);
		int temp , j = 0;
		boolean swapped = true;
		while(swapped){
			swapped = false;
			j++;
			for(int i = 0 ; i < numbers.length - j ; i++){
				if(numbers[i] > numbers[i+1]){
					temp = numbers[i+1];
					numbers[i+1] = numbers[i];
					numbers[i] = temp;
					swapped = true;
				}
			}
		}
		/*for(int k : numbers){
			System.out.print("  " + k);
		}*/
	}

}
