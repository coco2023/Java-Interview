#include <iostream>
using namespace std;

int bitCounter(int N){
    int retval = 0;
    while(N!=0){
        if(N&1){
            retval++;
        }
        N = N>>1;
    }
    return retval;
}

int main() {
    int arr[50];
    int M;
    cin>>M;
    int N;
    cin>>N;
    for(int i = 0;i<N;i++){
        cin>>arr[i];
    }
    int ans = 0;
    for(int i = 1 ; i < 1<<N; i++){
        int divisor = 1, temp, temp2 = i, j = N-1;
        while(temp2){
            if(temp2&1){
                divisor *= arr[j];
            }
            j--;
            temp2 = temp2>>1;
        }
        cout<<divisor<<endl;
        temp = M/divisor;
        if(bitCounter(i)%2==0){
            temp *= -1;
        }
        ans += temp;
    }
    cout<<ans;
}
