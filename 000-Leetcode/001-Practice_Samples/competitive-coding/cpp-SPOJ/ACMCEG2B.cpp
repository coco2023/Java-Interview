#include <bits/stdc++.h>
using namespace std;

int main() {
    typedef pair<int,int> codeHolder;
    map<codeHolder, string> codes;
    int n;
    cin >> n;
    codeHolder temp;
    while (n--) {
        cin >> temp.first >> temp.second;
        cin >> codes[temp];
    }
    
    cin >> n;
    while (n--) {
        cin >> temp.first >> temp.second;
        cout << codes[temp] <<endl;
    }
    return 0;
}
