#include <iostream>
using namespace std;

long long dp[1000001];
int N;

int main() {
	cin >> N;

	dp[1] = 1;
	dp[2] = 2;

	for (int i = 3; i <= N; i++) {
		dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
	}
	cout << dp[N];
	return 0;
}