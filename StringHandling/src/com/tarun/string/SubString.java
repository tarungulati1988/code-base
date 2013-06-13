/**
 * 
 * Code to check if a given short string is a substring of a main string.
 * Can you get a linear solution (O(n)) if possible?
 * 
 */
package com.tarun.string;

/**
 * 
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 * We will implement the KMP algorithm/ Z algorithm to solve the given problem in O(n).
 * 
 * Santaandbanta - string
 * and - substring
 * output: yabbaaaa dabbba dooooo!!!
 * 
 * ay - substring
 * output: arggghhhhh!!!
 *
 */
public class SubString {
	private String str, subStr;
	
	public void SubStringCheck(String s1, String s2){
		this.str = s1;
		this.subStr = s2;
		boolean flag = false;
		int len1, len2;
		len1 = str.length();
		len2 = subStr.length();
		int j = 0;
		System.out.println("1.");
		for(int i = 0 ; i < len1 ; i++){
			if(subStr.charAt(j) == str.charAt(i)){
				if(j == subStr.length() - 1){
					flag = true;
					break;
				}
				j++;
				continue;
		}else{
			 if (j > 0){
			 	 i--;
			 	 j = 0;
			 }else{
				 j = 0;
			 }
		}
	}
		if(flag){
			System.out.println("yabaaaa dabbba doooo!!!");
		}else{
			System.out.println("arrrrggghhhh!!!");
		}
	}
}