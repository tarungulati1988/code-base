/*print the summation of n numbers
 * Method 1: using a for loop
 * Method 2: using stacks
 * */


package summationOfN;

import static java.lang.System.*;

public class summationOfN {

	/**
	 * @param args
	 */
	//Method 1:
	public static void method1(int n){
		int sum = 0;
		for(int i = 0; i < n; i++){
			sum += i;
		}
		out.println("The sum is: " + sum);
	}
	
	
	//Method 2:
	public static void method2(int n){
		Stack st = new Stack(n);
		int m2sum = 0;
		while(n > 0){
			st.push(n);
			--n;
		}
		
		while(!st.isEmpty()){
			//.println(st.pop());
			m2sum += st.pop();
		}
		
		out.println("The sum is: " + m2sum);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		method1(50);
		method2(50);
	}

}

class Stack{
	private int maxSize;
	private int[] data;
	private int top;
	
	public Stack(int s){
		maxSize = s;
		data = new int[maxSize];
		top = -1;
	}
	
	public void push(int a){
		data[++top] = a;
	}
	
	public int pop(){
		return data[top--];
	}
	
	public int atTop(){
		return data[top];
	}
	
	public boolean isEmpty(){
		return (top == -1);
	}
}
