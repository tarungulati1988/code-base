#include <cstdlib>
#include <iostream>

using namespace std;

int myatoi(const char *str);


void main(void)
{
//    printf("\n%d\n", myatoi("1998"));
    cout<<"my atoi for 1988: "<<myatoi("1998");
    getchar();
}

int myatoi(const char *str){
                 int i=0, j=0;
                 while(*str){
                                               i = (i<<3) + (i<<1) + (*str - '0');
                                               str++;
                                               cout<<*str<<"\t"<<(*str - '0');
                             }
                 return (i);
    }
