/**
 * 
 *	author: Tarun Gulati, tgulati@indiana.edu
 *	SumOfPrimes.java, Aug 29, 2013, 1:50:37 PM	
 *  SumOfPrimes, 
 *
 */

/**
 * @author Tarun Gulati
 *
 */
public class SumOfPrimes {

	static boolean isPrime(int num){
		for( int i = 2 ; i < num ; i++){
			if(num % i == 0){
				return false;
			}
		}
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count, num, sum;
		num = 1;
		sum = 0;
		count = 0;
		while(num < 10000){
			if(isPrime(num)){
				sum += num;
				count++;
			}
			num++;
		}
		System.out.println("Sum of primes is = " + sum);

	}

}
