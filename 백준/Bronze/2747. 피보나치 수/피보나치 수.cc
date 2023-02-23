#include <iostream>
using namespace std;
long dp[45];

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	dp[0] = 0;
	dp[1] = 1;
	short n;
	cin >> n;
	for (short i = 2; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}
	cout << dp[n];
	return 0;
}