#include <cstdlib>
#include <iostream>
#include <string>
#include <stdio.h>

using namespace std;


int strToInt(string str){
                  //cout<<str;
                  int i = 0, num = 0;
                  bool isNegative = false;
                  int len = str.length();
                  //cout<<"    "<<len;
                  
                  //checking for negative integer
                  if(str[0] == '-'){
                               isNegative = true;
                               i = 1;
                            }
                            while(i < len){
                                           num *= 10;
                                           num += (str[i++] - '0');
                                           
                                    }
                            if(isNegative){
                                               num *= -1;
                                           }
                  return num;
                  //return 0;
    }

void main(void)
{
    int newInt;
    string str = "-1234";
    cout<<"\nOriginal string before being converted to int: "<<str;
    newInt = strToInt(str);
    cout<<"\nString after being converted to int: "<<newInt;
    getchar();
}
