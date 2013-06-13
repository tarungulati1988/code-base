#include <cstdlib>
#include <iostream>

using namespace std;

const int MAX_SIZE = 100;

class QueueOverFlowException{
                             public:
                                    QueueOverFlowException(){
                                                                  cout<<"Queue over flow exception!!"<<endl;
                                         }
      };
      
class QueueEmptyException{
                          public:
                                 QueueEmptyException(){
                                                            cout<<"Queue empty exception!!"<<endl;
                                      }
      };
      
class ArrayQueue{
                 private:
                         int data[MAX_SIZE];
                         int front;
                         int rear;
                 public:
                        ArrayQueue(){
                                            front = -1;
                                            rear = -1;
                                     }
                        void enqueue(int element){
                                         if( Size() == MAX_SIZE - 1){
                                                    throw QueueOverFlowException();
                                             }
                                         data[rear] = element;
                                         rear = ++rear % MAX_SIZE;
                             }
                        int dequeue(){
                                      if( isEmpty() ){
                                                       throw QueueEmptyException();
                                                    }
                                      int retVal = data[front];
                                      
                                      front = ++front % MAX_SIZE;
                                      return retVal; 
                            }
                        int Front(){
                                    if( isEmpty() ){
                                                     throw QueueEmptyException();
                                                  }
                                    return data[front];
                            }
                        int Size(){
                                   return abs(rear - front);
                            }
                        bool isEmpty(){
                                       return (front == rear) ? true : false;
                             }
      };
      
      

void main(void)
{
    ArrayQueue q;
    try{
               if( q.isEmpty() ){
                                       cout<<"Queue is empty!!"<<endl;
                              }
               q.enqueue(200);
               q.enqueue(300);
               q.enqueue(400);
               cout<<"Size of the queue is : "<<q.Size()<<endl;
               cout<<"Front : "<<q.Front()<<endl;
               cout<<endl<<q.dequeue();
               cout<<endl<<q.dequeue();
               cout<<endl<<q.dequeue();
               cout<<"Size of the queue is : "<<q.Size()<<endl;
        }
    catch(...){
                           cout<<"Some exception occured!!"<<endl;
               }
    
    getchar();
}
