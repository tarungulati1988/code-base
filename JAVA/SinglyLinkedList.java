/*
 * Author : Tarun Gulati
 * email : tgulati@indiana.edu, tarun.gulati1988@gmail.com
 * Different methods to reverse a string...hope you enjoy!!! 
 * Singly linked list implementation
 * 
 */

package com.singlylinkedlist.implementation;



//---------------Node class---------------
class Node{
	Node next;
	int data;
	
	void setData(int newData){
		data = newData;
	}
	
	void setNext(Node newNext){
		next = newNext;
	}
	
	int getData(){
		return data;
	}
	
	Node getNext(){
		return next;
	}
}


//-----------------Linked List class----------
class LinkedList{
	Node first;
	
	void add(int data){
		Node temp = new Node();
		temp.setData(data);
		if(first == null){
			first = temp;
		}
		else{
			Node prev = new Node();
			prev = first;
			while(prev.getNext() != null){
				prev = prev.getNext();
			}
			prev.setNext(temp);
		}
	}
	
	
	void addAtPosition(int data, int pos){
		int count = 1;
		Node temp = new Node();
		temp.setData(data);
		Node prev = new Node();
		prev = first;
		while(count != pos - 1 && prev.getNext() != null){
			prev = prev.getNext();
			count++;
		}
		temp.setNext(prev.getNext());
		prev.setNext(temp);
	}
	
	
	void search(int data){
		Node temp = new Node();
		temp = first;
		int pos = 1;
		while(temp != null){
			if(data == temp.getData()){
				System.out.println("The number has been found to exist at: " + pos);
			}
			pos++;
			temp = temp.getNext();
		}
	}
	
	void delete(int data){
		Node temp = new Node();
		temp = first;
		if(data == temp.getData()){
			first = first.getNext();
		}
		else{
			while(temp != null){
				if(temp.getNext().getData() == data){
					break;
				}
				else{
					temp = temp.getNext();
				}
			}
			System.out.println("The node has been deleted!!");
			temp.setNext(temp.getNext().getNext());
		}
	}
	
	void displayList(){
		Node temp = new Node();
		temp = first;
		while(temp != null){
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}
	
	void addNodeBeginning(int data){
		Node temp = new Node();
		temp.setData(data);
		temp.setNext(first);
		first = temp;
	}
	
	void addNodeAtEnd(int data){
		Node temp = new Node();
		temp.setData(data);
		Node prev = new Node();
		prev = first;
		while(prev.getNext() != null){
			prev = prev.getNext();
		}
		prev.setNext(temp);
		temp.setNext(null);
	}
	
	
}


public class SinglyLinkedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.displayList();
		//list.delete(3);
		list.displayList();
		list.addNodeBeginning(99);
		list.search(2);
		list.addNodeAtEnd(100);
		list.displayList();
		list.search(100);
		list.addAtPosition(22, 2);
		list.displayList();
		list.search(22);

	}

}
