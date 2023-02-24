#include <iostream>
using namespace std;
int m;
int n;
int map[501][501];
// dp[x][y] = a, (x,y)좌표 까지의 조건에 부합하는 경로 가짓수
int dp[501][501] = { 0, };
bool visit[501][501] = { 0, };
//하좌우
int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };

/*
	기존에 탐색한 경로값으로 
	해당 좌표에 방문하면 dp값을 반환하는 dfs구현
*/

//재귀
int dfs(int x, int y) {
	//종료조건
	if (x == m && y == n) {
		return 1;
	}

	//dp값이 초기값인 0이 아닌 경우
	//즉, 방문한 상태면 dp값 반환
	if (visit[x][y]) {
		return dp[x][y];
	}
	
	visit[x][y] = true;

	for (int k = 0; k < 4; k++) {
		int nx = x + dx[k];
		int ny = y + dy[k];
		if (nx > 0 && ny > 0 && nx <= m && ny <= n) {
			if (map[x][y] > map[nx][ny]) {
				dp[x][y] += dfs(nx, ny);
			}
		}
	}
	return dp[x][y];
}

int main() {
	cin >> m >> n;
	for (int i = 1; i <= m; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> map[i][j];
		}
	}
	cout << dfs(1, 1);
	return 0;
}