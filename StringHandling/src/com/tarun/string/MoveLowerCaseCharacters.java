/**
 * 
 */
package com.tarun.string;

import java.util.Arrays;
import java.util.Arrays.*;

/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class MoveLowerCaseCharacters {
	private String str;
	
	public void MoveCharacters(String inputStr){
		this.str = inputStr;
		StringBuilder s = new StringBuilder();
	    int x = 0;
	    String temp = "";

	    for (int i = 0; i < str.length(); ++i){
	        if(Character.isLowerCase(str.charAt(i))){
	            temp += str.charAt(i);
	        	++x;
	        }else{
	            s.append(str.charAt(i));
	        }
	    }

	    for(int i = 0 ; i < x ; ++i){
	        s.append('x');
	    }
	    System.out.println(" String: " + s.toString() + " lower case chracters : " + temp);
	}
}
