#include <iostream>
using namespace std;
long long pinary_nums[91];

/*
 1 / 10 / 101, 100 / 1001 1010 1000 
 / 10101, 10001, 10010, 10100, 10000
 n칸의 경우의 수 =
뒤가 1일때는 01로 끝나야해서 두 칸 앞의 경우의 수 +
뒤가 0일때는 아무거나 가능해서 바로 앞칸의 경우의 수
*/
int main() {
	int n;
	cin >> n;
	pinary_nums[1] = 1;
	pinary_nums[2] = 1;
	for (int i = 3; i <= n; i++) {
		pinary_nums[i] = pinary_nums[i - 1] + pinary_nums[i - 2];
	}
	cout << pinary_nums[n];
	return 0;
}