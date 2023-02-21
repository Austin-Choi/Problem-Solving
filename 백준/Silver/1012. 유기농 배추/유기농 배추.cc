#include <iostream>
#include <queue>
#include <cstring>
using namespace std;
int t; //테스트 케이스 수
int m; //가로길이
int n; // 세로길이
int k; //배추 갯수
int farm[50][50] = { 0, }; //배추밭 1이면 배추 0이면 없음
//상하좌우
int dy[4] = { -1, 1, 0, 0 };
int dx[4] = {  0, 0, -1, 1 };

//visited -> farm[y][x] == 0 
void bfs(int init_y, int init_x) {
	queue<pair<int, int>> q;
	q.push(make_pair(init_y, init_x));

	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (nx >= 0 && ny >= 0 && ny < n && nx < m) {
				if (farm[ny][nx] == 1) {
					farm[ny][nx] = 0;
					q.push(make_pair(ny, nx));
				}
			}
		}
	}

}

int main() {
	cin >> t;
	int worm = 0;
	int col;
	int row;

	for (int i = 0; i < t; i++) {
		cin >> m; //col max
		cin >> n; //row max
		cin >> k;
		
		for (int cnt = 0; cnt < k; cnt++) {
			cin >> col;
			cin >> row;
			farm[row][col] = 1;
		}

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (farm[y][x] == 1) {
					bfs(y, x);
					worm++;
				}
			}
		}

		cout << worm << "\n";
		worm = 0;
		memset(farm, 0, sizeof(farm));
	}
	return 0;
}