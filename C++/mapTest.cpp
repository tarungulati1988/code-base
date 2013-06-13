#include <cstdlib>
#include <iostream>
#include <map>
#include <string>
#include <utility>

using namespace std;

void main(void)
{
     map<int,int> emp;
     //emp[1] = "aaaa";
     //emp[4] = "qwe";
//     cout<<emp[4]<<"    ";
     
     for(int j = 0; j < 10; j++){
                 emp[j] = (j-1);
                // cout<<endl<<emp[j];
             }

     map<int, int>::iterator i = emp.begin();
     for(i; i != emp.end(); ++i){
//                            cout<<endl<<(*i).first<<"     "<<(*i).second;
                            if(((*i).second) > 1)
                                             cout<<endl<<"yes greater than 1: "<<(*i).first<<"     "<<(*i).second;
            }
    getchar();
}
