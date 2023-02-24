#include <iostream>
#include <string>
#include <queue>
using namespace std;

short map[101][101] = { 0, };
bool visit[101][101] = { false, };
//현재 좌표까지의 거리
int dist[101][101] = { 0, };

void bfs(int n, int m, int x, int y) {
	queue<pair<int, int>> q;
	q.push(make_pair(x, y));
	visit[x][y] = true;
	dist[x][y] = 1;

	int dx[4] = { 0,0,-1,1 };
	int dy[4] = { -1,1,0,0 };
	
	while (!q.empty()) {
		int cur_x = q.front().first;
		int cur_y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = cur_x + dx[i];
			int ny = cur_y + dy[i];
			
			if (nx > -1 && ny > -1 && nx < n && ny < m) {
				if (!visit[nx][ny] && map[nx][ny] == 1) {
					q.push(make_pair(nx, ny));
					visit[nx][ny] = true;
					dist[nx][ny] = dist[cur_x][cur_y] + 1;
				}
			}
			
		}
	}
}

int main() {
	int n;
	int m;

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		//getline(cin, s); X
		string s;
		cin >> s;
		for (int n = 0; n < m; n++) {
			map[i][n] = s[n] - 48;
		}
	}
	bfs(n, m, 0, 0);
	cout << dist[n - 1][m - 1];
	return 0;
}