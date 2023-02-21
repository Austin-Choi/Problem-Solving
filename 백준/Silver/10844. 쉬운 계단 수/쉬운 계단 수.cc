#include <iostream>
constexpr long long mod = 1000000000;
using namespace std;

int n;
long long dp[101][10];
long long tot = 0;

//dp[계단수][끝자리(0~9)]=가짓수
void solution() {
	//
	for (int j = 2; j <= n; j++) {
		for (int i = 0; i <= 9; i++) {
			if (i == 9) {
				dp[j][i] = dp[j - 1][i - 1] % mod;
			}
			else if (i == 0) {
				dp[j][i] = dp[j - 1][i + 1] % mod;
				//dp[j][i] %= mod;
			}
			else {
				//dp[i][j]에 값이 제대로 안들어감
				//-> 이유 끝자리 0처리와
				//-> for문 조건에 의해 아직 처리되지 않은 자리의 수를 대입하려해서
				dp[j][i] = (dp[j - 1][i - 1] + dp[j - 1][i + 1]) % mod;
			}
			//cout << dp[j][i] << " ";
		}
		//cout << "\n";
	}
}

int main() {
	cin >> n;
	//1자리일때
	dp[0][0] = 0;
	dp[1][0] = 0;
	for (int i = 1; i <= 9; i++) {
		dp[1][i] = 1;
	}
	solution();
	for (int i = 0; i <= 9; i++) {
		//수가 크면 tot에서 overflow?
		tot += dp[n][i] % mod;
	}
	cout << tot % mod;
	return 0;
}