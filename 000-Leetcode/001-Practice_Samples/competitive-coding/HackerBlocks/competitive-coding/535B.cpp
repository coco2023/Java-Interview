#include <bits/stdc++.h>

using namespace std;

int main(){
    int N;
    cin>>N;
    int len = 0, temp = N;
    vector<int> arr;
    while(temp){
        len++;
        arr.insert(arr.begin(),temp%10);
        temp /= 10;
    }
    long long int rank = 0;
    rank += 1 << len;
    rank -= 2;
    for(int i = arr.size() - 1, pos = 0; i >= 0 ;i--, pos++){
        if(arr[i] == 7){
            rank += 1 << pos;
        }
    }
    cout<<++rank;
    return 0;
}