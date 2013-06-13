#include <cstdlib>
#include <iostream>

using namespace std;

int factorial(int n);

void main(void)
{
    int num = 5, fact;
    cout<<"Factorial of "<<num<<" is: ";
    fact = factorial(num);
    cout<<fact;
    getchar();
}

int factorial(int n){
                  if(n > 1)
                       return (factorial(n-1) * n);
                  else 
                       return 1;
    }
