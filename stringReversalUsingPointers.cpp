#include<stdio.h>
#include <cstdlib>
#include <iostream>
 
using namespace std;

void reverse(char *begin, char *end);

//1. reverse individual words
//2. reverse the complete string to get the desired output
 
/*Function to reverse words*/
void reverseWords(char *s)
{
    char *word_begin = NULL;
    char *temp = s; /* temp is for word boundry */
    
    /*STEP 1 of the above algorithm */
    while( *temp )
    {
        /*This condition is to make sure that the string start with
          valid character (not space) only*/
        if (( word_begin == NULL ) && (*temp != ' ') )
        {
            word_begin=temp;
        }
        if(word_begin && ((*(temp+1) == ' ') || (*(temp+1) == '\0')))
        {
            reverse(word_begin, temp);
            word_begin = NULL;
        }
        temp++;
    } /* End of while */
 
    /*STEP 2 of the above algorithm */
    reverse(s, temp-1);
}

void reverseWordsAndString(char *s)
{
    char *word_begin = NULL;
    char *temp = s; 
    while( *temp )
    {
        if (( word_begin == NULL ) && (*temp != ' ') )
        {
            word_begin=temp;
        }
        if(word_begin && ((*(temp+1) == ' ') || (*(temp+1) == '\0')))
        {
            word_begin = NULL;
        }
        temp++;
    } 
    reverse(s, temp-1);
}
 

/*Function to reverse any sequence starting with pointer
  begin and ending with pointer end  */
void reverse(char *begin, char *end)
{
  char temp;
  while (begin < end)
  {
    temp = *begin;
    *begin++ = *end;
    *end-- = temp;
  }
}
 
 
 
void main(void)
{
  char s1[] = "String reversal using pointers.";
  cout<<"\nOriginal string is: "<<s1;
  reverseWords(s1);
  cout<<"\nReversed string is: "<<s1;
  
  cout<<"\n\n===================================================\n";
  
  char s2[] = "String reversal using pointers.";
  cout<<"\nOriginal string is: "<<s2;
  reverseWordsAndString(s2);
  cout<<"\nReversed string is: "<<s2;
  
  getchar();
}
