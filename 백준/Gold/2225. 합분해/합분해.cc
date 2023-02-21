#include <iostream>
using namespace std;
int n;
int k;
// n개를 더해서 그 합이 k가 되는 경우의 수
long long dp[201][201];

int main() {
	cin >> n;
	cin >> k;
	
	// 1개를 더해서 그 합이 k가 되는건 자기 자신 1가지 뿐
	for (int i = 0; i <= k; i++) {
		dp[1][i] = i;
	}
	// k가 0이면 dp[k][x] = 0
	// 어떤 수를 0개를 더해서 합이 x가되는 경우는 없으므로

	for (int i = 2; i <= n; i++) {
		for (int j = 1; j <= k; j++) {
			dp[i][j] = dp[i - 1][j] + dp[i][j-1];
			dp[i][j] %= 1000000000;
		}
	}
	cout << dp[n][k];
	return 0;
}