#include <cstdlib>
#include <iostream>

using namespace std;

void print(int b[][2], int l, int p){
     int i,j;
     cout<<"==============================print(arr, m, n)==================================";
     for(i=0; i<5; i++){
              cout<<endl<<b[i][0]<<"\t"<<b[i][1];
              }
     cout<<endl;
     }

void show(int *b, int l, int p){
     int i,j;
     cout<<"==============================show(arr, m, n)==================================";
     for(i=0; i<5; i++){
              cout<<endl<<*(b+i*2+0)<<"\t"<<*(b+i*2+1);
              }
     cout<<endl;
     }
     
void display(int(*b)[2], int l){
     int i,j,*p;
     cout<<"==============================display(arr, m)==================================";
     for(i=0; i<5; i++){
              p=(int*)b;
              cout<<endl<<*(p)<<"\t"<<*(p+1);
              b++;
              }
     cout<<endl;
     }
     
void exhibit(int(*b)[5][2], int l){
     int i,j;
     cout<<"==============================exhibit(arr, m)==================================";
     for(i=0; i<5; i++){
             // p=(int*)b;
              cout<<endl<<(*b)[i][0]<<"\t"<<(*b)[i][1];
              b++;
              }
     cout<<endl;
     }

void main(void)
{
    int arr[][2]={{11,12}, {21,22}, {31,32}, {41,42}, {51,52}};
    int i, m, n, *j;
    m = 5;
    n = 2;
    print(arr, m, n); //array to array
    //show(arr, m, n);  //array to a pointer
    display(arr, m);  //array to single dimension pointer array
    exhibit(arr, m);  // array to double dimension pointer array
    
    
    getchar();
}


