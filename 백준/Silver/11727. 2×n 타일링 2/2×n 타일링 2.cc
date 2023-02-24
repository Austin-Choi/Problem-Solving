#include <iostream>
using namespace std;
constexpr int mod = 10007;
long long dp[1001];

//그림을 그려 보면 
//2xn의 사각형을 채우는 방법은
//2xn-1길이의 사각형에 2x1사각형을 붙이는 1가지
//2xn-2길이의 사각형에 1x2사각형 2개를 붙이는 것과
//2x2 1개를 붙이는 총 2가지가 존재
//따라서, 점화식은
// dp[i] = dp[i-1]+2*dp[i-2];

int main() {
	int n;
	cin >> n;
	//초기값
	dp[0] = 0;
	dp[1] = 1;
	dp[2] = 3;

	//반복문에서는 초기값 건들지 않기
	for (int i = 3; i <= n; i++) {
		dp[i] = (dp[i - 1] + 2*dp[i - 2]) % mod;
	}
	cout << dp[n] % mod;
	return 0;
}