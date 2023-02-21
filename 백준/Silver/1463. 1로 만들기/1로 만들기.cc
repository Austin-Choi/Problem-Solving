#include <iostream>
#include <algorithm>
using namespace std;
int storage_DP[1000000];

int main(void) {
	int number_N = 0;
	cin >> number_N;

	for (int i = 2; i < number_N + 1; i++) {
		// 1. 1을 뺀다
		// +1은 계산횟수
		storage_DP[i] = storage_DP[i - 1] + 1;

		// 2. 2로 나뉘어지면
		if (i % 2 == 0) {
			storage_DP[i] = min(storage_DP[i], storage_DP[i / 2] + 1);
		}

		// 3. 3으로 나뉘어지면
		if (i % 3 == 0) {
			storage_DP[i] = min(storage_DP[i], storage_DP[i / 3] + 1);
		}
	}
	cout << storage_DP[number_N];
	return 0;
}