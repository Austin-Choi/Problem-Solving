#include <iostream>
#include <vector>
using namespace std;

int n;
//queen[a] = b;
//퀸의 위치 : a행의 b열
int queen[14];
int cnt;

bool possible_queen(int x) {
	for (int i = 0; i < x; i++) {
		if (queen[i] == queen[x] || x - i == queen[x] - queen[i] || x - i == queen[i] - queen[x])
			return false;
	}
	return true;
}

void bt(int x) {
	if (x == n) {
		cnt++;
		return;
	}
	else {
		for (int y = 0; y < n; y++) {
			queen[x] = y;
			if (possible_queen(x)) {
				bt(x + 1);
			}
		}
	}

	
}
int main() {
	cnt = 0;
	cin >> n;
	bt(0);
	cout << cnt;
	return 0;
}