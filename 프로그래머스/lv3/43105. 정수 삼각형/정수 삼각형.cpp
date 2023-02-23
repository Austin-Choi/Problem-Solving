#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int dp[500][500];

int solution(vector<vector<int>> triangle) {
	int answer = -1;
	dp[0][0] = triangle[0][0];
	int height = triangle.size();
	int width = triangle[height - 1].size();

	for (int i = 0; i < 2; i++) {
		dp[1][i] = triangle[1][i] + dp[0][0];
	}
	for (int i = 2; i < height; i++) {
		for (int j = 0; j < triangle[i].size(); j++) {
			dp[i][j] = triangle[i][j] + max(dp[i - 1][j], dp[i - 1][j - 1]);
		}
	}

	return *max_element(dp[height - 1], dp[height - 1] + width);
}