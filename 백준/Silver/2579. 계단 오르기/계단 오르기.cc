#include <iostream>
#include <algorithm>
using namespace std;
int stair_amount;
int stair_actual[301];
int stair_DP[301];

int main(int argc, const string* argv) {
	cin >> stair_amount;
	for (int i = 1; i < stair_amount + 1; i++)
		cin >> stair_actual[i];

	stair_DP[1] = stair_actual[1];
	// 양의 점수밖에 없는 상황에서 1번째 2번째 밟은게
	// 1번만 밟은것보다 클리가 없으므로
	stair_DP[2] = stair_actual[1] + stair_actual[2];


	for (int i = 3; i < stair_amount + 1; i++) {
		stair_DP[i]
			= max(stair_DP[i - 3] + stair_actual[i - 1] + stair_actual[i],
				stair_DP[i - 2] + stair_actual[i]);
	}
	cout << stair_DP[stair_amount];
	return 0;
}