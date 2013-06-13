/**
 * 
 * --> GOOGLE
 * Given two strings .Print all the interleavings of the two strings. 
 * Interleaving means that the if B comes after A .It should also come after A in the interleaved string. 
 * ex- 
 * AB and CD 
 * ABCD 
 * ACBD 
 * ACDB 
 * CABD 
 * CADB 
 * CDAB
 * 
 */

package com.tarun.string;

/**
 * @author Tarun Gulati, tarun.gulati1988@gmail.com
 *
 */
public class Interleave {
	
	public void interleave(String a, String b) {
		interleave(a, b, 0, 0, 0, new char[a.length() + b.length()]);
	}
	
	private void interleave(String a, String b, int indA, int indB,	int current, char[] newWord) {

		if (current == newWord.length) {
			System.out.println(newWord);
			return;
		}
		if (indA < a.length()) {
			newWord[current] = a.charAt(indA);
			interleave(a, b, indA + 1, indB, current + 1, newWord);
		}

		if (indB < b.length()) {
			newWord[current] = b.charAt(indB);
			interleave(a, b, indA, indB + 1, current + 1, newWord);
		}
	}
}
