#include <iostream>
#include <algorithm>
using namespace std;
int tri[501][501] = { 0, };
int dp[501][501] = { 0, };

/*
	3
	7
	3 8
	8 1 0

	   7
	 10 15
	18 16 15

	18

*/

int main(void) {
	int n;
	cin >> n;
	int total = 0;

	for (int i = 1; i < n + 1; i++) {
		for (int j = 1; j < i + 1; j++) {
			cin >> tri[i][j];
		}
	}
	//dp문제는 항상
	//메모할곳 = 조건(예전 메모한거랑 비교할것) + 코스트 이런식
	dp[1][1] = tri[1][1];
	for (int i = 2; i < n + 1; i++) {
		for (int j = 1; j < i + 1; j++) {
			dp[i][j] = max(dp[i - 1][j-1], dp[i - 1][j]) + tri[i][j];
			//3 1 = 2 0, 2 1(10) + 
		}
	}

	for (int i = 1; i < n + 1; i++) {
		if (dp[n][i] > total)
			total = dp[n][i];
	}
	cout << total;
	return 0;
}