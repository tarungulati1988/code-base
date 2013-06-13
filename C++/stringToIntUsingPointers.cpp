#include <cstdlib>
#include <iostream>

using namespace std;

int myAtoi(char *str);

void main(void)
{
    cout<<"\nMy atoi for -1988 is: "<<myAtoi("-1988");
    getchar();
}

int myAtoi(char *str){
                // cout<<*str;
                int i = 0, j = 0;
                bool isNegative = false;
                if(*str == '-'){
                            isNegative = true;
                            str++;
                        }
                while(*str){
                             cout<<endl<<*str;
                             i *= 10;
                             i += (*str -'0');
                             str++;
                            // cout<<*str<<"\t"<<(*str - '0');
                            }
                if(isNegative)
                              i *= -1;
                return i;
    }
