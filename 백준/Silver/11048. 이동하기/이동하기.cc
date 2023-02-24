#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;
int maze[1001][1001];
int n;
int m;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cin.tie(nullptr);

	cin >> n >> m;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			cin >> maze[i][j];
		}
	}
	//cout << "처음 nm : " << maze[n][m] << "\n";
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			if (j == 1) {
				maze[i][j] = maze[i - 1][j] + maze[i][j];
			}
			else if (i == 1) {
				maze[i][j] = maze[i][j-1] + maze[i][j];
			}
			else {
				vector<int> tmp;
				tmp.push_back(maze[i - 1][j]);
				tmp.push_back(maze[i][j - 1]);
				tmp.push_back(maze[i - 1][j - 1]);
				maze[i][j]
					= *max_element(tmp.begin(), tmp.end()) + maze[i][j];
				tmp.clear();
				//벡터 내용 지우고 메모리 정리
				vector<int>().swap(tmp);
			}
			
		}
	}
	cout << maze[n][m];
	return 0;
}