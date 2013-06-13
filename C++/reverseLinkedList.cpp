#include<iostream>

using namespace std;

class Node{
	int data;
	Node* next;
	public:
		Node(){};
		void setData(int aData){
			data = aData;
		};

		void setNext(Node *aNext){
			next = aNext;
		};

		int Data(){
			return data;
		};

		Node* Next(){
			return next;
		};
};

class List{
	Node *head;
	public:
		List(){
			head = NULL;
		};	
		void print();
		void add(int data);
		void Delete(int data);
		void reverse();
};

void List::print(){
	Node *temp = head;
	if(temp == NULL){
		return;
	}

	if(temp->Next() == NULL){
		cout<<temp->Data();
		cout<<"\t";
	}
	else{
		do{
			cout<<temp->Data();
			cout<<"\t";
			temp = temp->Next();
		}while(temp != NULL);
	}
	cout<<endl;
}


void List::add(int data){
	Node* newNode = new Node();
	newNode->setData(data);
	newNode->setNext(NULL);

	Node *temp = head;

	if(temp != NULL){
		while(temp->Next() != NULL){
			temp = temp->Next();
		}
		temp->setNext(newNode);
	}
	else{
		head = newNode;
	}
}


void List::Delete(int data){
	Node *temp = head;
	if(temp == NULL){
		return;
	}

	if(temp->Next() == NULL){
		delete temp;
		head = NULL;
	}
	else{
		Node *prev;
		do{
			if(temp->Data() == data){
				break;
			}
			prev = temp;
			temp = temp->Next();
		}while(temp != NULL);

		prev->setNext(temp->Next());

		delete temp;
	}
}

void List::reverse(){
                     Node *curr = head;
                     Node *nextt = NULL;
                     //Node *n3 = NULL;
                     while(curr->Next() != NULL){
                                      nextt = curr;
                                      cout<<nextt->Data()<<"  "<<nextt->Next()<<endl;
                                      curr = nextt->Next();
                                      cout<<"cur : "<<curr->Data()<<"  "<<curr->Next()<<"     head    "<<head->Data()<<endl;
                                      nextt = head->Next();
                                      head = nextt;
                                      cout<<"Head : "<<head->Data()<<"  "<<head->Next()<<endl;
                                      cout<<"Nextt : "<<nextt->Data()<<"  "<<nextt->Next()<<endl;
                                      cout<<endl;
                                       
                              }
     }




void main(void)
{
    // New list
    List list;

    // Append nodes to the list
    list.add(100);
    list.print();
    list.add(200);
    list.print();
    list.add(300);
    list.print();
   
    //list.add(60);
    /*list.print();
    list.add(60);
    list.print();
    list.add(70);
    list.print();
    list.add(80);
    list.print();
    list.add(90);*/
    list.print();
    list.add(400);
    list.print();
    list.add(500);
    list.print();
    cout<<endl;
    list.reverse();

    // Delete nodes from the list
    /*list.Delete(400);
    list.print();;
    list.Delete(300);
    list.print();
    list.Delete(200);
    list.print();
    list.Delete(500);
    list.print();
    list.Delete(100);*/
    list.print();
    getchar();
}
