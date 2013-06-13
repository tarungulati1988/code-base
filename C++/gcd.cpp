#include <cstdlib>
#include <iostream>

using namespace std;

int gcd(int a, int b);

void main(void)
{
    int a = 45, b = 9;
    cout<<"\n GCD of "<<a<<" and "<<b<<" is: "<<gcd(a, b);
    
    getchar();
}

int gcd(int a, int b){
            while(a != b){
                    if(a > b)
                         return gcd(a-b, b);
                    else
                        return gcd(a, b-a);
            }
            return a;
    }
