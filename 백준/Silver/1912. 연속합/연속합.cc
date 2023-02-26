#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int n;
	cin >> n;
	vector<int> nums(100001);
	int dp[100001];
	int ans;

	for (int i = 1; i <= n; i++) {
		cin >> nums[i];
		dp[i] = nums[i];
	}

	ans = dp[1] = nums[1];
	for (int i = 2; i <= n; i++) {
		dp[i] = max(nums[i], dp[i - 1] + nums[i]);
		ans = max(ans, dp[i]);
	}
	cout << ans;
	return 0;
}