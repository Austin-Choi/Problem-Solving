#include <iostream>
#include <algorithm>
using namespace std;
int n; //1~1000
int a[1001] = { 0, };
int dp[1001];
//dp[자리수] = 부분순열의 길이
// 반례
//4
//1423
//->3
int main() {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> a[i];
	}
	for (int i = 1; i <= n; i++) {
		dp[i] = 1;
		//i까지로 고정해놓고 j로 순회하면서 비교
		for (int j = 1; j <= i; j++) {
			if (a[i] > a[j]) {
				if (dp[j] >= dp[i])
					dp[i] = dp[j] + 1;
			}
		}
	}
	cout << *max_element(dp, dp + n+1);
	return 0;
}