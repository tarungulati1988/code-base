/*
 * Author : Tarun Gulati
 * email : tgulati@indiana.edu, tarun.gulati1988@gmail.com
 * 
 * Different sorting algorithm implementations 
 * insertion sort
 * merge sort
 * bubble sort
 * shell sort
 * simple sort
 * 
 */

package com.sort.implementation;

import static java.lang.System.*;

public class SortsImplementation {

	//-------------insertion sort----------------
	public static void insertionSort(int[] num){
	    int k;
		for(int j = 0; j < num.length ; j++){
	       int temp = num[j]; 
	       k = j; 
	       while(k > 0 && num[k-1] >= temp){
	    	   num[k] = num[k-1]; 
	    	   --k; 
	       }
	       num[k] = temp;
	    }
		
		out.println("\nAfter sorting using insertion sort: ");
		for(int i = 0 ; i < num.length ; i++)
			out.print(" " + num[i]);
	}
	
	//--------------shell sort------------------
	public static void shellSort(int[] num){
		
	}
	
	//--------------merge sort------------------
	public static void mergeSort(int[] num, int low, int high){
		
		
	}
	
	//--------------simple sort-----------------
	public static void simpleSort(int[] num){
		int temp1, temp2;
		for(int i = 0 ; i < num.length ; i++){
			for(int j = 0 ; j < i - 1 ; j++){
				if(num[j] > num[j + 1]){
					temp1 = num[j + 1];
					temp2 = num[j];
					num[j] = temp1;
					num[j + 1] = temp2;
				}
			}
		}
		out.println("\nAfter sorting using simple sort: ");
		for(int l = 0 ; l < num.length ; l++)
			out.print(" " + num[l]);
	}
	
	//--------------bubble sort-----------------
	
	public static void bubbleSort(int[] num){
		int temp;
		for(int i = 0; i < num.length; i++){
			for(int j = 1; j< (num.length - 1); j++){
				if(num[j-1] > num[j]){
					temp = num[j-1];
					num[j-1] = num[j];
					num[j] = temp;
				}
			}
		}
		out.println("\nAfter sorting using bubble sort: ");
		for(int l = 0 ; l < num.length ; l++)
			out.print(" " + num[l]);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {100, 12, 130, 1, 98, 56, 234, 3, 4, 1};
		out.println("Before sorting: ");
		for(int i = 0; i < num.length; i++)
			out.print(" " + num[i]);
		insertionSort(num);
		simpleSort(num);
		bubbleSort(num);
	}

}
