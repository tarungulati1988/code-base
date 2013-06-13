/**
 * 
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */

package com.tarun.main;

import com.tarun.string.MoveLowerCaseCharacters;
import com.tarun.string.SeparateDuplicates;
import com.tarun.string.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeparateDuplicates sd = new SeparateDuplicates();
		MoveLowerCaseCharacters mc = new MoveLowerCaseCharacters();
		MaxWindowOFMatching var3 = new MaxWindowOFMatching();
		SubString var4 = new SubString();
		Interleave var5 = new Interleave();
		
		System.out.println("--------------------------------------------------");
		sd.splitDuplicatesWithAsterix("aaabbbbccd");
		
		System.out.println("--------------------------------------------------");
		mc.MoveCharacters("xxBBcffSdxaxSDRERfssd");
		
		System.out.println("--------------------------------------------------");
		var3.MaxWindowStr("ABCDEFG", "DBCAPFG");
		
		System.out.println("--------------------------------------------------");
		var4.SubStringCheck("Santaandbanta", "and");
		
		System.out.println("--------------------------------------------------");
		var5.interleave("ABC", "DEF");
		
	}

}
