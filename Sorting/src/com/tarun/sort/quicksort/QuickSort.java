/**
 * 
 *  O(nlogn) complexity, suitable for sorting bid data
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */
package com.tarun.sort.quicksort;

import java.util.Arrays;
import java.util.Arrays.*;

public class QuickSort {
	private int[] numbers;
	
	public void sort(int[] num, int left, int right){
		this.numbers = Arrays.copyOf(num, num.length);
		int index = partition(numbers, left, right);
		if(left < index - 1){
			sort(numbers, left, index - 1);
		}if(index < right){
			sort(numbers, index, right);
		}
	}
	
	int partition(int[] num, int left, int right){
		int i = left, j = right, temp, pivot;
		pivot = num[(i + j)/2];
		
		while(i <= j){
			while(num[i] < pivot){
				i++;
			}
			while(num[j] > pivot){
				j--;
			}
			if(i <= j){
				temp = num[i];
				num[i] = num[j];
				num[j] = temp;
				i++;
				j--;
			}
		}
		return i;
	}
	
	public void show(){
		for(int i : this.numbers){
			System.out.print("  " + i);
		}
	}

}
