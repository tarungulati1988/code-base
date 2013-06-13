/**
 * 
 * Given an array find any three numbers which sum to zero. Give the best algorithm.
 * 
 */
package com.tarun.walmartlabs.questions;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Arrays.*;
import java.util.Hashtable;

import com.tarun.sort.merge.*;
/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class ThreeNumbersSumToZero {
private int[] numArr;

protected void calculateSumUsingBruteForce(int[] num){
	this.numArr = Arrays.copyOf(num, num.length);
	for(int i = 0 ; i < numArr.length; i++){
		for(int j = i + 1 ; j < numArr.length; j++){
			for(int k = j + 1 ; k < numArr.length ; k++){
				if((numArr[i] + numArr[j] + numArr[k]) == 0){
					System.out.println("The numbers are: " + numArr[i] + ", " + numArr[j] + ", " + numArr[k]);
				}
			}
		}
	}
}

public void calculateSumImprovedO(int[] num){
	this.numArr = Arrays.copyOf(num, num.length);
	Hashtable hs = new Hashtable();
	for(int i = 0 ; i < numArr.length ; i++){
		hs.put(numArr[i], i);
	}
	int mid = numArr.length/2;
	int sum = 0;
	//System.out.println(numArr.length + "     " + mid + "  " + numArr[numArr.length - 1]);
	int temp = numArr[numArr.length - 1];
	/*for(int i = 0 ; i < numArr.length/2 ; i++){
		sum = numArr[i] + temp;
		//System.out.println(sum + " , " + -sum + " , " + hs.containsKey(-sum));
		if(hs.containsKey(-(sum))){
			System.out.println("The numbers are: " + numArr[i] + ", " + temp + " , " + num[(int)hs.get(-(sum))]);
		}
		sum = 0;
		temp = numArr[numArr.length - i - 1];
	}*/
	for(int i = 0 ; i < numArr.length ; i++){
		int j = i + 1;
		while(j < numArr.length){
			sum = numArr[i] + numArr[j];
			if(sum != 0 && hs.containsKey(-sum) && (int)hs.get(-sum) != i){
				System.out.println("The numbers are: " + numArr[i] + ", " + numArr[j] + " , " + num[(int)hs.get(-(sum))]);
			}
			j++;
		}
	}
}


/**
 * O(nlogn)
 * @param num
 */
public void calculateSumBestO(int[] num){
	MergeSort mergeSortVar = new MergeSort();
	mergeSortVar.mergesort(num, 0, num.length);
	this.numArr = mergeSortVar.returnSortedArray();
	Hashtable hs = new Hashtable();
	for(int i = 0 ; i < numArr.length ; i++){
		hs.put(numArr[i], i);
	}
	int low = 0, high = numArr.length-1;
	while(low < high){
		int s = numArr[low] + numArr[high];
		if(hs.containsKey(-s) && (int)hs.get(-s) != low){
			System.out.println("The numbers are: " + numArr[low] + ", " + numArr[high] + " , " + numArr[(int)hs.get(-(s))]);
			high--;
			low++;
		}if(s > 0)
			 high--;
		else
		 	low++;
		s = 0;
	}

}

}
