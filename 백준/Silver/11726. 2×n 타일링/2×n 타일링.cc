#include <iostream>
#include <vector>
using namespace std;

int main(int argc, const char* argv) {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	vector<int> tiles(1001, 0);
	int n = 0;
	cin >> n;
	tiles[1] = 1;
	tiles[2] = 2;
	for (int i = 3; i < n + 1; i++) {
		tiles[i] = (tiles[i - 1] + tiles[i - 2]) % 10007;
	}
	cout << tiles[n];
	return 0;
}