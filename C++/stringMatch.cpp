#include <cstdlib>
#include <iostream>

using namespace std;

void myStrStr(const char *str, int strLength, const char *subStr, int subStrLength){
                    cout<<str<<"  "<<strLength<<"  "<<subStr<<"  "<<subStrLength;
                    int i, j;
                    int distancePatterns[3];
                    for(i=0;i<subStrLength - 1; i++){
                                           distancePatterns[i] = subStrLength - i - 1;
                                           cout<<endl<<*subStr<<"\t : "<<distancePatterns[i];
                                           subStr++;
                                           }
     }

void main(void)
{
   myStrStr("qwertyasdf", 10, "qw3", 3);
   getchar(0);
}
