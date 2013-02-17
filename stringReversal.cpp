#include <cstdlib>
#include <iostream>
#include <stdio.h>

using namespace std;

static char str[]="character";
int reverse(int pos);

void main(void)
{
//printf("\nOriginal string : [%s]", str);
cout<<"\nOriginal string: "<<str;
// Call the recursion function
reverse(0);
//printf("\nReversed string : [%s]", str);
cout<<"\nReversed string: "<<str;
getchar();
}

int reverse(int pos)
{
// Here I am calculating strlen(str) everytime.
// This can be avoided by doing this computation
// earlier and storing it somewhere for later use.
cout<<endl<<pos<<"  : "<<strlen(str)/2;
if(pos<(strlen(str)/2))
{
char ch;
// Swap str[pos] and str[strlen(str)-pos-1]
ch = str[pos];
str[pos]=str[strlen(str)-pos-1];
str[strlen(str)-pos-1]=ch;
// Now recurse!
reverse(pos+1);
}
}

