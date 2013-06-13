#include<iostream>
#include<sys/time.h>
#include<fstream>
#include<vector>
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<iomanip>
#include<math.h>

using namespace std;


void main(void)

{
  int data[] = {2, 6, 4, 10, 24, 50, 78, 1, 3, 11, 42};
  int lenD = sizeof(data)/sizeof(int);
  int inc = lenD/2;

  while(inc>0){

    for(int i=inc;i<lenD;i++){

      int tmp = data[i];

      int j = i;

      while(j>=inc && data[j-inc]>tmp){

        data[j] = data[j-inc];

        j = j-inc;

      }

      data[j] = tmp;

    }

  inc = (inc /2);

  }

  for(int x=0; x<lenD; x++)
  {
    cout<<data[x]<<endl;
  }

  getchar();
}
