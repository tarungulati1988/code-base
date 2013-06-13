/**
 * 
 * Print in spiral form as shown below 
 *	For n=2 
 *	3 2 
 *	0 1 
 *	For n=3 
 *	4 3 2 
 *	5 0 1 
 *	6 7 8 
 *	For n=4 
 *	15 14 13 12 
 *	4 3 2 11 
 *	5 0 1 10 
 *	6 7 8 9
 * 
 */
package com.tarun.ibm.questions;

/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class PrintInSprialForm {
	private int num;
	
	public void printSpiral(int x){
		this.num = x;
		int[][] arr = new int[num][num];
	    int number = num * num;
	    int i = 0, icount = 0, j = 0, jcount = 0;
	    number -= 1;
	    while(number >= 0){
	        i = icount;
	        for(j = jcount ; j < num - jcount ; j++)
	            arr[i][j]= number--;
	        j--;
	        for(i = icount + 1 ; i < num - icount ; i++)
	            arr[i][j] = number--;
	        i--;
	        for(j = ( j - 1 ) ; j >= jcount ; j--)
	            arr[i][j]=number--;
	        j++;
	        for(i = (num - icount - 2) ; i > icount ; i--)
	            arr[i][j]=number--;

	        icount++;
	        jcount++;
	    }
	    for(i = 0 ; i < num ; i++)
	    {
	        System.out.println();
	        for(j = 0 ; j < num ; j++)
	            System.out.print("   " + arr[i][j]);
	    }
	}
}
