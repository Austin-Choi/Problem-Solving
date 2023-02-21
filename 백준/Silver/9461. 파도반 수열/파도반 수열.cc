#include <iostream>
long long dp[101];
using namespace std;

long long p(int n){
	if (0<n && n < 4) {
		return 1;
	}
	if (dp[n])
		return dp[n];
	else {
		dp[n] = p(n - 2) + p(n - 3);
		return dp[n];
	}
}
int main() {
	int t;
	int n;
	cin >> t;
	//초깃값
	dp[0] = 0;
	for (int i = 1; i <= 3; i++) {
		dp[i] = 1;
	}
	for (int i = 0; i < t; i++) {
		cin >> n;
		cout << p(n) << endl;
	}
	return 0;
}