#include <iostream>
#include <vector>
#include <string.h>
#include <queue>
#include <algorithm>
using namespace std;

vector<int> vec[10002];
vector<int> v_bfs;
vector<int> v_dfs;
bool visit[1002];

void bfs(int tmp) {
	queue<int> q;
	q.push(tmp);
	visit[tmp] = true;
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		v_bfs.push_back(x);

		for (size_t i = 0; i < vec[x].size(); i++) {
			if (!visit[vec[x][i]]){
				q.push(vec[x][i]);
				visit[vec[x][i]] = true;
			}
				
		}
	}
}

void dfs(int x) {
	visit[x] = true;
	v_dfs.push_back(x);

	for (size_t i = 0; i < vec[x].size(); i++) {
		if (!visit[vec[x][i]])
			dfs(vec[x][i]);
	}
}

int main() {
	int n, m, v, a, b;
	cin >> n >> m >> v;
	for (int i = 1; i < m + 1; i++) {
		cin >> a >> b;
		vec[a].push_back(b);
		vec[b].push_back(a); //양방향 간선처리
	}
	for (int i = 1; i < n + 1; i++) {
		sort(vec[i].begin(), vec[i].end());
	}
	dfs(v);
	memset(visit, false, sizeof(visit));
	bfs(v);
	for (auto x : v_dfs) {
		cout << x << " ";
	}
	cout << endl;
	for (auto x : v_bfs) {
		cout << x << " ";
	}
	return 0;
}