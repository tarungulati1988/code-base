#include <cstdlib>
#include <iostream>

using namespace std;

void main(void)
{
     int *p, *ptr;
     int a = 10;
     p = &a;
     cout<<a<<"\t"<<*p<<"\t"<<&p<<"\t"<<&a<<"\t"<<p;
    // ptr = a;
     //ptr++;
     //++ptr;
     //cout<<" ptr = "<<ptr;
     char ch[] = "Numerical Methods!";
     cout<<"\n"<<&ch<<"\t"<<&ch[0]<<"\t"<<&ch[1]<<"\t"<<ch;
     char **marker;
     char *course[5];
     course[0] = "Numeric";
     course[1] = "Methods";
     marker = &course[0];
     cout<<"\n"<<*marker;
     marker++;
     cout<<"aaaaa";
     cout<<"\n"<<*marker;
     (*marker)++;
     cout<<"\n"<<*marker;
     //---------------------------------
     
     int darr[][3]={{1,2,3},{4,5,16},{17,18,9},{10,11,12},{13,14,175}};
     int g, h;
     int *P, (*q)[3], *r;
     P = (int*)darr;
     q = darr;
     r = (int*)q;
     cout<<"------------------------";
     cout<<P<<endl<<q<<endl<<*P<<endl<<*(r)<<endl<<*(r+1)<<endl<<*(r+2)<<endl<<&darr<<endl<<*P + 6<<endl<<*(r+14);
     P++;
     q += 3;
     r=(int*)q;
     cout<<"------------------------";
     cout<<P<<endl<<q<<endl<<*P<<endl<<*(r)<<endl<<*(r+1)<<endl<<*(r+2)<<endl<<&darr<<endl<<*P + 6<<endl<<*(r+14);
     
     getchar();
}
