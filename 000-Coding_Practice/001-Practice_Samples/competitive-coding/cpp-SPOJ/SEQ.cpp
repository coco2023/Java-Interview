#include <bits/stdc++.h>
using namespace std;
typedef long long int ll;
#define MOD 1000000000
ll k;
vector<ll> a,b,c;
vector<vector<ll>> multiply(vector<vector<ll>> a, vector<vector<ll>> b){
    vector<vector<ll>> retval (k+1, vector<ll>(k+1));
    for(ll i = 1;i<=k;i++){
        for(ll j = 1;j<=k;j++){
            for(ll m = 1;m<=k;m++){
                retval[i][j] = (retval[i][j] + (a[i][m]*b[m][j])%MOD)%MOD;
            }
        }
    }
    return retval;
}

vector<vector<ll>> pow(vector<vector<ll>> matrix, ll power){
    if(power == 1){
        return matrix;
    }
    if(power&1){
        return multiply(matrix, pow(matrix,power-1));
    } else {
        vector<vector<ll>> smallAns = pow(matrix, power/2);
        return multiply(smallAns, smallAns);
    }
}

ll nextTermCalc(ll N){
    if(N == 0){
        return 0;
    }
    if( N <= k ){
        return b[N - 1];
    }
    //F1 vector
    vector<ll> F1(k+1);
    for(int i = 1;i<=k;i++){
        F1[i] = b[i-1];
    }
    //Transformation Matrix
    vector<vector<ll>> transformation(k+1,vector<ll>(k+1));
    for(ll i = 1;i<=k;i++){
        for(ll j = 1;j<=k;j++){
            if(i<k){
                if(j==i+1){
                    transformation[i][j] = 1;
                } else {
                    transformation[i][j] = 0; 
                }
                continue;
            }
            transformation[i][j] = c[k-j];
        }
    }
    transformation = pow(transformation,N-1);
    ll res = 0;
    for(ll i = 1;i<=k;i++){
        res = (res +(transformation[1][i]*F1[i])%MOD)%MOD;
    }
    return res;
    
}

int main() {
    ll t, temp, i, N;
    cin>>t;
    while(t--){
        cin>>k;
        for(i = 0 ;i<k;i++){
            cin>>temp;
            b.push_back(temp);
        }
        for(i = 0;i<k;i++){
            cin>>temp;
            c.push_back(temp);
        }
        cin>>N;
        cout<<nextTermCalc(N)<<endl;
        b.clear();
        c.clear();
    }
    return 0;
}
