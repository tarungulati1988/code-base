/**
 *
 * Give you two sequences of length N, how to find the max window of matching 
 * patterns. The patterns can be mutated. 
 *	For example, seq1 = “ABCDEFG”, seq2 = “DBCAPFG”, then the max window is 4. ( 
 *	ABCD from seq1 and DBCA from seq2). 
 * 
 */
package com.tarun.string;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class MaxWindowOFMatching {
	private String str;
	private String sequence;
	
	public void MaxWindowStr(String string, String seq){
		this.str = string;
		this.sequence = seq;
		Hashtable h = new Hashtable();
		Hashtable hashSeq = new Hashtable();
		for(int i = 0 ; i < str.length() ; i++){
			if(!h.containsKey(str.charAt(i))){
				h.put(str.charAt(i), 1);
			}else{
				h.put(str.charAt(i), ((int)h.get(i) + 1));
			}
		}
		
		for(int j = 0 ; j < sequence.length() ; j++){
			if(!hashSeq.containsKey(sequence.charAt(j))){
				hashSeq.put(sequence.charAt(j), 1);
			}else{
				hashSeq.put(sequence.charAt(j), ((int)hashSeq.get(j) + 1));
			}
		}
		
		Enumeration en = h.keys();
		while(en.hasMoreElements()){
			if(hashSeq.containsKey(en.nextElement())){
				System.out.println("yaaaba dabbbbba dooooo!!!");
			}
		}
	}
}
