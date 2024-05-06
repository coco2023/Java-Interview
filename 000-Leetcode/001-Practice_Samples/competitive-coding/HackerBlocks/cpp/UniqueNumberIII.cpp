#include <iostream>
using namespace std;
int main() {
    int arr[64] = {0};
    int t;
    cin>>t;
    while(t--){
        int N, i = 0;
        cin >> N;
        while(N) {
            if(N&1){
                arr[i]++;
            }
            N = N>>1;
            i++;
        }
    }
    int ans = 0;
    for(int i = 0 ; i < 64 ; i++){
        arr[i] %= 3;
        if(arr[i]!=0)
            ans += 1 << i; 
    }
    cout<<ans<<endl;
    
}
