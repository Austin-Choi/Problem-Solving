#include <iostream>
#include <algorithm>
using namespace std;
int dp[1001];
int cost[1001];
int n;


int main() {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> cost[i];
	}
	dp[0] = cost[0] = 0;
	for (int i = 1; i <= n;i++) {
		for(int j = 1; j<=i; j++){
			dp[i] = max(dp[i], dp[i - j] + cost[j]);
		}
	}
	cout << dp[n];
	return 0;
}