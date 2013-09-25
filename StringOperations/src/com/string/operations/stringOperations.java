/*
 * Author : Tarun Gulati
 * email : tgulati@indiana.edu, tarun.gulati1988@gmail.com
 * Different methods to reverse a string and some string problems...hope you enjoy!!! 
 * 
 * 
 */

package com.string.operations;


public class stringOperations {

	//---------string reversal using a buffer-------------
	
	public static String stringReversalUsingBuffer(String string){
		String tempString = "";
		for(int i = string.length() - 1; i >= 0; i--){
			//System.out.println(string.charAt(i));
			tempString += string.charAt(i);
		}
		
		return tempString;
	}
	
	//------------using string builder----------------------
	
	public static String stringReversalUsingStringBuilder(String string){
		StringBuilder tempBuilder = new StringBuilder();
		for(int i = string.length() - 1; i >= 0; i--){
			tempBuilder.append(string.charAt(i));
		}
		
		return tempBuilder.toString();
	}
	
	//---------string reversal using built in method--------------
	
	public static String stringReversalUsingBuiltInMethod(String string){
		StringBuilder tempBuilder = new StringBuilder(string);
		return tempBuilder.reverse().toString();
	}
	
	//--------string reversal with array swaps---------
	
	public static String stringReversalWithArraySwap(String string){
		char[] reversalArray = string.toCharArray();
		int length = string.length() - 1;
		int mid = length/2;
		char temp;
		for(int i = length; i >= mid; i--){
			temp = reversalArray[length - i];
			reversalArray[length - i] = reversalArray[i];
			reversalArray[i] = temp;
		}
		return String.valueOf(reversalArray);
	}
	
	
	//-----------string reversal using string split-------------
	
	public static String stringReversalUsingStringSplit(String string){
		StringBuilder tempBuilder = new StringBuilder();
		String[] temp = string.split(" ");
		for(int i = (temp.length - 1); i >= 0 ; i--){
			tempBuilder.append(temp[i]);
			tempBuilder.append(" ");
		}
		return tempBuilder.toString();
	}
	
	//-------------string reversal with word reversal--------------
	
	
	public static String stringReversalWithWordReversal(String string){
		StringBuilder tempBuilder = new StringBuilder();
		String[] temp = string.split(" ");
		String tempStr = "";
		for(int i = (temp.length - 1); i >= 0 ; i--){
			tempStr = reverseWord(temp[i]);
			//System.out.println("before: " + temp[i]);
			tempBuilder.append(tempStr);
			tempBuilder.append(" ");
			//System.out.println("after: " + tempStr);
			tempStr = "";
		}
		return tempBuilder.toString();
	}

	
	//--------no string reversal only word reversal---------------
	
	public static String onlyWordReversalInString(String string){
		StringBuilder tempBuilder = new StringBuilder();
		String[] temp = string.split(" ");
		String tempStr = "";
		for(int i = 0 ; i < (temp.length) ; i++){
			tempStr = reverseWord(temp[i]);
			tempBuilder.append(tempStr);
			tempBuilder.append(" ");
			tempStr = "";
		}
		return tempBuilder.toString();
	}
	
	//------------pallindrone check for strings--------------------
	
	public static boolean pallindroneCheckInStrings(String string){
		String tempStr = string;
		int len = string.length() - 1;
		for(int i = 0; i < string.length(); i++){
			if(tempStr.charAt(len - i) != string.charAt(i))
				return false;
		}
		return true;
	}
	
	//--------------pallindrone check using StringBuilder------------
	
	public static boolean pallindroneCheckUsingStringBuilder(String string) {
        String reversedString = new StringBuilder(string).reverse().toString();
        return string.equals(reversedString);
    }
	
	//-------------halfStringReversal-------------------
	
	public static String halfStringReversal(String str){
		String[] temp = str.split("\\.");
		//System.out.println(temp.length);
		String tempStr = "";
		StringBuilder tempBuilder = new StringBuilder();
		for(int i = 0; i < temp.length; i++){
			tempStr = stringReversalUsingStringSplit(temp[i]);
			tempBuilder.append(tempStr);
			//tempBuilder.append(".");
			//tempBuilder.replace(tempBuilder.length()-1, tempBuilder.length()-1, ".");
		}
		return tempBuilder.toString();
	}
	
	
	//-----------------subStringStar---------------------------
	
	public static String subStringStar(String str){
		System.out.println(str);
		StringBuffer sb = new StringBuffer(str);
		for(int i = 0 ; i < sb.length()-1; i++){
			if(sb.charAt(i) == sb.charAt(i+1)){
				sb.insert((i+1), "*");
				i++;
			}
		}
		return sb.toString();
	}
	
	//-------reverse word helper---------------
	
	public static String reverseWord(String str){
		//String resturnStr = "";
		String tempString = "";
		for(int i = str.length() - 1; i >= 0; i--){
			//System.out.println(string.charAt(i));
			tempString += str.charAt(i);
		}
		return tempString;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "Batman in hindi is fauji ka naukar";
		String halfSplitStr = "one two three. four five six.";
		String str2;
		//method 1
		str2 = stringReversalUsingBuffer(str1);
		System.out.println("----------------------------------");
		System.out.println("method1: " + str2);
		//method 2
		str2 = stringReversalUsingStringBuilder(str1);
		System.out.println("----------------------------------");
		System.out.println("method2: " + str2);
		//method 3
		str2 = stringReversalUsingBuiltInMethod(str1);
		System.out.println("----------------------------------");
		System.out.println("method3: " + str2);
		//method 4
		str2 = stringReversalWithArraySwap(str1);
		System.out.println("----------------------------------");
		System.out.println("method4: " + str2);
		//method 5
		str2 = stringReversalUsingStringSplit(str1);
		System.out.println("----------------------------------");
		System.out.println("method5: " + str2);
		//method 6
		str2 = stringReversalWithWordReversal(str1);
		System.out.println("----------------------------------");
		System.out.println("method6: " + str2);
		//method 7
		str2 = onlyWordReversalInString(str1);
		System.out.println("----------------------------------");
		System.out.println("method7: " + str2);
		//pallindrone check in strings
		String strPallindrone = "abba";
		System.out.println("----------------------------------");
		System.out.println("Pallindrone check");
		if(pallindroneCheckInStrings(strPallindrone)){
			System.out.println("A pallindrone!!");
		}
		else{
			System.out.println("Not a pallindrone!!");
		}
		//pallindrone check in strings using stringBuilder
		System.out.println("----------------------------------");
		System.out.println("Pallindrone check using StringBuilder");
		if(pallindroneCheckUsingStringBuilder(strPallindrone)){
			System.out.println("A pallindrone!!");
		}
		else{
			System.out.println("Not a pallindrone!!");
		}
		
		str2 = halfStringReversal(halfSplitStr);
		System.out.println("----------------------------------");
		System.out.println(halfSplitStr);
		System.out.println(str2);
		
		//hello --> hel*lo
		//xxyyyyzz -->x*xy*y*y*y*z*z
		System.out.println("----------------------------------");
		str2 = subStringStar("xxxyyyyyzzzzzzzz");
		System.out.println(str2);
		
	}

}
