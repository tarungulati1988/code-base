#include <cstdlib>
#include <iostream>
#include<stdio.h>

using namespace std;


#define N 10
int maxSubSum(int left, int right);
int list[N] = {-11, -12, -15, -3, 8, -9, 1, 8, 10, -2};
void main(void)
{
    int i,j,k;
    int maxSum, sum;
    /*---------------------------------------
    * CUBIC - O(n*n*n)
    *---------------------------------------*/
    maxSum = 0;
    for(i=0; i<N; i++)
    {
    for(j=i; j<N; j++)
    {
    sum = 0;
    for(k=i ; k<j; k++)
    {
    sum = sum + list[k];
    }
    maxSum = (maxSum>sum)?maxSum:sum;
    }
    }
    //printf("\nmaxSum = [%d]\n", maxSum);
    cout<<"\nmax sum: "<<maxSum;
    /*-------------------------------------
    * Quadratic - O(n*n)
    * ------------------------------------ */
    maxSum = 0;
    for(i=0; i<N; i++)
    {
    sum=0;
    for(j=i; j<N ;j++)
    {
    sum = sum + list[j];
    maxSum = (maxSum>sum)?maxSum:sum;
    }
    }
    //printf("\nmaxSum = [%d]\n", maxSum);
    cout<<"\nmax sum: "<<maxSum;
    
    /*----------------------------------------
    * Divide and Conquer - O(nlog(n))
    * -------------------------------------- */
//    printf("\nmaxSum : [%d]\n", maxSubSum(0,9));
    cout<<"\nmax sum: "<<maxSubSum(0,9);

    cout<<"   "<<10%5<<7%3;
    getchar();
}


int maxSubSum(int left, int right)
{
        int mid, sum, maxToLeft, maxToRight, maxCrossing, maxInA, maxInB;
        int i;
        if(left>right){return 0;}
        if(left==right){return((0>list[left])?0:list[left]);}
        mid = (left + right)/2;
        sum=0;
        maxToLeft=0;
        for(i=mid; i>=left; i--)
        {
        sum = sum + list[i];
        maxToLeft = (maxToLeft>sum)?maxToLeft:sum;
        }
        sum=0;
        maxToRight=0;
        for(i=mid+1; i<=right; i++)
        {
        sum = sum + list[i];
        maxToRight = (maxToRight>sum)?maxToRight:sum;
        }
        maxCrossing = maxToLeft + maxToRight;
        maxInA = maxSubSum(left,mid);
        maxInB = maxSubSum(mid+1,right);
        return(((maxCrossing>maxInA)?maxCrossing:maxInA)>maxInB?((maxCrossing>maxInA)?maxCrossing:maxInA):maxInB);
}
