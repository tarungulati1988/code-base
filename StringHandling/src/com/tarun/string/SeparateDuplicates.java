/**
 * 
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 * 
 */
package com.tarun.string;

public class SeparateDuplicates {
	private String strToCheck;
	
	public void splitDuplicatesWithAsterix(String str){
		this.strToCheck = str;
		String temp = "";
		char temp2 = ' ';
		for(int i = 0 ; i < strToCheck.length() ; i++){
			if(strToCheck.charAt(i) == temp2){
				temp += "*";
				temp += strToCheck.charAt(i);
			}else{
				temp += strToCheck.charAt(i);
			}
			temp2 = strToCheck.charAt(i);
		}
		System.out.println(temp);
	}

}
