#include <iostream>
using namespace std;
int n;
//a칸인 곳에 [b] 경우의수   
int dp[100001][3];

int main() {
	cin >> n;
	//2*1 우리에서 놓는건 아예 안놓기 1
	//왼쪽 1, 오른쪽 1가지뿐
	//초기식
	for (int i = 0; i < 3; i++) {
		dp[1][i] = 1;
	}
	for (int i = 2; i <= n; i++) {
		//안 놓는것은 이전에 어떤 경우가 와도 좋음
		dp[i][0] = dp[i-1][0] + dp[i - 1][1] + dp[i - 1][2] ;
		dp[i][0] %= 9901;
		//왼쪽은 바로 위에 뭐가 있으면 안되므로 
		dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
		dp[i][1] %= 9901;
		dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
		dp[i][2] %= 9901;
	}
	int total = 0;
	for (auto num : dp[n]) {
		total += num;
	}
	cout << total % 9901;
	return 0;
}