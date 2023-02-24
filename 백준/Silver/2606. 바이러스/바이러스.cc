#include <iostream>
#include <queue>
#include <vector>
using namespace std;
// net[a] = b; a가 시작점이고 b가 끝점인 연결
//bool net[101][101] = { false, };
bool visit[101] = { false, };
vector<int> net[101];

int bfs(int start, int& cnt) {
	queue<int> q;
	q.push(start);
	visit[start] = true;

	while (!q.empty()) {
		int cur = q.front();
		q.pop();

		for (int i = 0; i < net[cur].size(); i++) {
			if (!visit[net[cur][i]]) {
				visit[net[cur][i]] = true;
				q.push(net[cur][i]);
				cnt += 1;
			}
		}
	}
	return cnt;
}

int main() {
	int x; //정점 수
	int y; //간선 수

	cin >> x >> y;
	int i;
	int j;
	for (int n = 1; n <= y; n++) {
		cin >> i >> j;
		//net[i][j] = net[j][i]
		//net[i]=j;
		//net[j]=i;
		net[i].push_back(j);
		net[j].push_back(i);
	}
	int cnt = 0;
	cout << bfs(1, cnt);
	return 0;
}