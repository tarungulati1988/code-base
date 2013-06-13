/**
 * 
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */

import java.util.Random;

import com.tarun.sort.selection.*;
import com.tarun.sort.insertion.*;
import com.tarun.sort.bubble.*;
import com.tarun.sort.quicksort.*;
import com.tarun.sort.merge.*;
import com.tarun.sort.shell.*;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = new int[600000];
		double startTime, finishTime, elapsedTime;
		Random randNumGenerator = new Random();
		for (int i=0; i<num.length; i++){
			num[i] = (randNumGenerator.nextInt(65536) - 32768);
		}
		
		System.out.println("The numbers to be sorted are: ");
		
		for(int j : num){
			System.out.print("  " + j);
		}
		
		
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println("Selection Sort: ");
		startTime = System.currentTimeMillis();
		SelectionSort ss = new SelectionSort();
		ss.sort(num);
		finishTime = System.currentTimeMillis();
		elapsedTime = (finishTime - startTime) / 1000;
		System.out.println("\nTime taken: " + elapsedTime + " seconds..");
		
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println("\nInsertion Sort: ");
		startTime = System.currentTimeMillis();
		InsertionSort is = new InsertionSort();
		is.sort(num);
		finishTime = System.currentTimeMillis();
		elapsedTime = (finishTime - startTime) / 1000;
		System.out.println("\nTime taken: " + elapsedTime + " seconds..");
		
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println("\nBubble Sort: ");
		startTime = System.currentTimeMillis();
		BubbleSort bs = new BubbleSort();
		bs.sort(num);
		finishTime = System.currentTimeMillis();
		elapsedTime = (finishTime - startTime) / 1000;
		System.out.println("\nTime taken: " + elapsedTime + " seconds..");
		
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println("\nQuick Sort: ");
		startTime = System.currentTimeMillis();
		QuickSort qs = new QuickSort();
		qs.sort(num, 0, num.length-1);
		//qs.show();
		finishTime = System.currentTimeMillis();
		elapsedTime = (finishTime - startTime) / 1000;
		System.out.println("\nTime taken: " + elapsedTime + " seconds..");
		
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println("\nMerge Sort: ");
		startTime = System.currentTimeMillis();
		MergeSort ms = new MergeSort();
		ms.mergesort(num, 0, num.length);
		//ms.show();
		finishTime = System.currentTimeMillis();
		elapsedTime = (finishTime - startTime) / 1000;
		System.out.println("\nTime taken: " + elapsedTime + " seconds..");
		
		System.out.println("\n--------------------------------------------------------------------------------------------------------");
		System.out.println("\nShell Sort: ");
		startTime = System.currentTimeMillis();
		ShellSort shs = new ShellSort();
		shs.sort(num);
		finishTime = System.currentTimeMillis();
		elapsedTime = (finishTime - startTime) / 1000;
		System.out.println("\nTime taken: " + elapsedTime + " seconds..");
	}

}
