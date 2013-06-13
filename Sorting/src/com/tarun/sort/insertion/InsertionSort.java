/**
 * 
 * O(n) is the best case and O(n^2) is the average and worst case 
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */

package com.tarun.sort.insertion;

import java.util.Arrays;

public class InsertionSort {
	private int numbers[];
	
	public void sort(int[] arrayToSort){
		this.numbers = Arrays.copyOf(arrayToSort, arrayToSort.length);
		for(int k = 0 ; k < numbers.length ; k++){
			int v = numbers[k];
			int j = k;
			while(j != 0 && numbers[j-1] > v){
				numbers[j] = numbers[j-1];
				j = j - 1;
			}
			numbers[j] = v;
		}
		/*for(int i : numbers){
			System.out.print("  " + i);
		}*/
	}

}