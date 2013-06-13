#include <cstdlib>
#include <iostream>

using namespace std;

int binarySearch(int numbers[], int lower, int upper, int num);

void main(void)
{
    int numbers[]={8, 9, 10, 11, 12};
    int upper = sizeof(numbers)/sizeof(int) - 1;
    int lower = 0; 
    bool val;
    int num=11;
    cout<<"\n\nBinary Search\n";
    val = binarySearch(numbers, lower, upper, num);
    cout<<"\n"<<val;
    getchar();
}

int binarySearch(int numbers[], int lower, int upper, int num){
                     int mid;
                     if(lower <= upper){
                                        mid = (lower + upper)/2;
                                        if(num == numbers[mid])
                                               return true;
                                         else if(num < numbers[mid])
                                              return binarySearch(numbers, lower, mid - 1, num);
                                         else
                                             return binarySearch(numbers, mid + 1, upper, num);
                                        }
                     else
                                        return false;
                         
                     
    }
