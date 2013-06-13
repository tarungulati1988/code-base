/**
 * 
 * O(n^2) for all best, average and worst case, stable when using extra space
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */
package com.tarun.sort.selection;

import java.util.Arrays;

public class SelectionSort {
	private int numbers[];
	private int size;
	
	public void sort( int[] arrayToSort ){
		this.numbers = Arrays.copyOf( arrayToSort, arrayToSort.length );
		int temp;
		for(int i = 0 ; i < numbers.length ; i++){
			for(int j = i ; j < numbers.length ; j++){
				if(numbers[i] > numbers[j]){
					temp = numbers[j];
					numbers[j] = numbers[i];
					numbers[i] = temp;
				}
			}
		}
		/*for(int k : numbers){
			System.out.print("  " + k);
		}*/
	}

}
