#include <bits/stdc++.h>

using namespace std;

int main(){
    vector<int> nums;
    long long int N;
    cin >> N;
    while (N) {
        nums.push_back( N % 10 );
        N /= 10;
    }
    for(int i = 0 ; i < nums.size() ; i++){
        if( i == 0){
            if(nums[i] == 9){
                nums[i] = 9;
            } else if (nums[i] >= 5) {
                nums[i] = 9 - nums[i];
            }
        } else {
           if( nums[i] >= 5){
               nums[i] = 9 - nums[i];
           } 
        }
    }
    for(int i = 0 ; i < nums.size() ; i++){
        cout<<nums[i];
    }
    cout<<endl;
}