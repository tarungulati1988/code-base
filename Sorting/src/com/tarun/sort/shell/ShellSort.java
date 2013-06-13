/**
 * 
 * O(n) best case and O(nlogn) for worst and average case, useful when memory at hand is very low
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */
package com.tarun.sort.shell;

import java.util.Arrays;
import java.util.Arrays.*;

public class ShellSort {
	private int[] numbers;
	
	public void sort(int[] num){
		this.numbers = Arrays.copyOf(num, num.length);
		int inc = numbers.length/2;
		
		while(inc > 0){
			for(int i = 0 ; i < numbers.length ; i++){
				int temp = numbers[i];
				int j = i;
				while(j >= inc && numbers[j - inc] > temp){
					numbers[j] = numbers[j - inc];
					j = j- inc;
				}
				numbers[j] = temp;
			}
			inc = inc/2;
		}
		/*for(int k : numbers){
			System.out.print("  " + k);
		}*/
	}

}
