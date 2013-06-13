#include <cstdlib>
#include <iostream>

using namespace std;

const int MAX_SIZE = 100;

class StackOverFlowException{
                             public:
                                    StackOverFlowException(){
                                                                cout<<"Stack Overflow!"<<endl;
                                                             }
      };
      
class StackUnderFlowException{
                              public:
                                     StackUnderFlowException(){
                                                                cout<<"Stack Underflow!"<<endl;
                                                               }
      };
      
class ArrayStack{
                 private:
                         int data[MAX_SIZE];
                         int top;
                 public:
                         ArrayStack(){
                                            top = -1;
                                      }
                         
                         void push(int element){
                                       if(top >= MAX_SIZE){
                                                 throw StackOverFlowException();
                                              }
                                       else{
                                              data[++top] = element;
                                            }
                              }
                              
                         int pop(){
                                   if(top == -1){
                                             throw StackUnderFlowException();
                                          }
                                   return data[--top];
                             }
                             
                         int Top(){
                                   return data[top];
                             }
                             
                         int size(){
                                    return (top + 1);
                             }
                             
                         bool isEmpty(){
                                        return (top == -1) ? true : false;
                              }
      };
      
      

void main(void)
{
    ArrayStack s;
    try{
                if(s.isEmpty()){
                                        cout<<"Stack is empty"<<endl;
                                }
                s.push(200);
                s.push(300);
                cout<<"Size of the stack is: "<<s.size()<<endl;
                cout<<"top is "<<s.Top()<<endl;
                s.pop();
                s.pop();
                cout<<s.isEmpty();
        }
    catch(...){
                cout<<"Some exception occured!!"<<endl;
               }
    getchar();
}
