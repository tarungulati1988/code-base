#include <cstdlib>
#include <iostream>

using namespace std;

int addNoArithmetic(int a, int b);

void main(void)
{
    int a = 8, b = 9;
    cout<<addNoArithmetic(a, b);
    getchar();
}

int addNoArithmetic(int a, int b){
                           if(a == 0)
                                return b;
                           else if(b == 0)
                                return a;
                           else{
                                       int sum = a ^ b;
                                       cout<<"\nsum"<<sum;
                                       int carry = (a & b) << 1;
                                       cout<<"\ncarry"<<carry;
                                       return addNoArithmetic(sum, carry);
                                }     
    }
