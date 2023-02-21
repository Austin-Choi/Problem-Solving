#include <iostream>
using namespace std;
int n;
//i길이의 j로 끝나는 오르막 수의 갯수
int dp[1001][10];

int main() {
	int total = 0;
	cin >> n;
	//1자리에서 각 자릿수로 끝나는건 1가지뿐이므로 1을 채워줌
	for (int i = 0; i < 10; i++) {
		dp[1][i] = 1;
	}

	for (int i = 2; i <= n; i++) {
		for (int j = 0; j < 10; j++) {
			if (j == 0) {
				//0이 끝자리일땐 00, 000등 한가지밖에 없으므로
				dp[i][j] = 1;
				continue;
			}
			dp[i][j] = (dp[i - 1][j] + dp[i][j-1]) % 10007;
		}
	}
	for (auto i : dp[n]) {
		total += i;
	}
	cout << total%10007;
	return 0;
}