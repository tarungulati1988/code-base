#include <cstdlib>
#include <iostream>

using namespace std;

void main(void)
{
    int a[] = {2, -8, 3, -2, 4, -10};
    int maxsum = 0;
    int sum = 0;
    for (int i = 0; i < 6; i++) {
    sum += a[i];
    if (maxsum < sum) {
               maxsum = sum;
    } else if (sum < 0) {
               sum = 0;
    }
    }
    cout<<maxsum;
    cout<<"  "<<(10<<2);
    getchar();
}
