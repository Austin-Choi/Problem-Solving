#include <iostream>
using namespace std;
int dp[21][21][21];

int w(int a, int b, int c) {
	if (a <= 0 || b <= 0 || c <= 0) {
		
		return 1;
	}
	if (a > 20 || b > 20 || c > 20) {
		return w(20, 20, 20);
	}
	//위 두 조건에서 a,b,c는 1~20까지
	if (dp[a][b][c])
		return dp[a][b][c];

	if (a  < b && b< c) { 
		dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
		return dp[a][b][c];
	}
	else {
		dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
		return dp[a][b][c];
	}
}

int main() {
	int a = 60, b = 60, c = 60;
	while (true) {
		cin >> a >> b >> c;
		cin.ignore();
		if (a == -1 && b == -1 && c == -1)
			break;
		//w(1, 1, 1) = 2
		cout << "w(" << a << ", " << b << ", " << c << ") = " << w(a,b,c) << endl;
	}
	return 0;
}