#include <cstdlib>
#include <iostream>

using namespace std;

void reverseString(char str[]);
void reverseStringAndWords(char str[]);
void reverseWord(char str[], int start, int len);

void main(void)
{
    char str1[]="I have to reverse this string in the most efficient way.";
    char str2[]="I have to reverse this string in the most efficient way.";    
    cout<<"\nReversing only the strings and not the words";
    cout<<"\n\nOriginal string before being reversed: "<<str1;
    cout<<"\nString after being reversed: ";
    reverseString(str1);
    
    cout<<"\n\n\n---------------------------------------------------------\n\n";
    
    cout<<"\nReversing the string and also the individual words in it.";
    cout<<"\n\nOriginal string before being reversed: "<<str2;
    cout<<"\nString after being reversed: ";
    reverseStringAndWords(str2);
    
    getchar();
}

void reverseString(char str[]){
                        int start = 0, end = 0, len;
                        len = strlen(str) - 1;
                        //cout<<len;
                        reverseWord(str, start, len);
                        while(end < len){
                                         if(str[end] != ' '){
                                                             start = end;
                                                             while(end < len && str[end] != ' '){
                                                                             end++;
                                                                             }   
                                                             end--;
                                                             reverseWord(str, start, end);
                                         }
                        end++;
                        }
                        cout<<endl<<str;
              }
              
void reverseWord(char str[], int start, int end){
                             //cout<<str<<start<<end;
                             char temp;
                             while(end > start){
                                                temp = str[start];
                                                str[start] = str[end];
                                                str[end] = temp;
                                                start++;
                                                end--;
                                       }
                             //cout<<endl<<str;
     }
     
void reverseStringAndWords(char str[]){
                        int start = 0, end = 0, len;
                        len = strlen(str) - 1;
                        //cout<<len;
                        reverseWord(str, start, len);
                        cout<<endl<<str;
              }
