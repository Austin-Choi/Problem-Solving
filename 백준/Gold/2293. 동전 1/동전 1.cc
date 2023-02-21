#include <iostream>
using namespace std;
int n;
int k;
int coin[101];
int dp[10001];

int main() {
	dp[0]=1; //아무것도 사용하지 않는 방법수 0원만들기
	cin >> n;
	cin >> k;
	for (int i = 1; i <= n; i++) {
		cin >> coin[i];
	}
	for (int i = 1; i <= n; i++) {
		for (int j = coin[i]; j <= k; j++) {
			dp[j] = dp[j] 
				//내가 X원으로 Y원을 만들기 위해선, 
				//Y-X원을 만드는 경우의 수만큼 더해줘야
				+ dp[j - coin[i]];
		}
	}
	cout << dp[k];
	return 0;
}