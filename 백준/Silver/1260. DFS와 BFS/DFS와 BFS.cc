#include <iostream>
#include <vector>
#include <stack>
#include <string.h>
#include <queue>
#include <algorithm>
using namespace std;

vector<int> road[1001];
bool visit[1001];
//n 정점의 개수
//m 간선의 개수
//v 시작점
//a, b 간선정보 (시작a, 끝b)
//양방향이면 간선들[a][b]=1 간선들[b][a] =1로 처리
int n, m, v, a, b;

//int road[시작점][끝점]
//인접행렬을 이용, 재귀로 구현한 dfs
void dfs_old(int v) {
	visit[v] = true;
	cout << v << " ";

	for (int i = 1; i <= n; i++) {
		if (road[v][i] == 1 && !visit[i]) {
			visit[i] = true;
			dfs_old(i);
		}
	}
}

//int road[시작점][끝점]
//인접행렬을 이용, queue로 구현한 dfs
void bfs_old(int v) {
	queue<int> q;
	q.push(v);
	visit[v] = true;
	cout << v << " ";

	while (!q.empty()) {
		v = q.front();
		q.pop();


		for (int i = 1; i <= n; i++) {
			if (road[v][i] == 1 && !visit[i]) {
				q.push(i);
				visit[i] = true;
				cout << i << " ";
			}
		}
	}
}

//vector<int> road[시작점]={끝점1, 끝점2, 끝점3 ... } ;
//인접 리스트를 이용, stack으로 구현한 dfs
void dfs(int v) {
	stack<int> s;
	s.push(v);

	while (!s.empty()) {
		int idx = s.top();
		s.pop();

		if (!visit[idx]) {
			visit[idx] = true;
			cout << idx << " ";

			//dfs는 뒤에서부터 스택에 삽입
			//stack은 후입선출의 구조라
			for (int i = road[idx].size()-1; i >= 0; i--) {
				if (!visit[road[idx][i]]) {
					s.push(road[idx][i]);
				}
			}
		}
	}
}

//vector<int> road[시작점]={끝점1, 끝점2, 끝점3 ... } ;
//인접 리스트를 이용, queue으로 구현한 bfs
void bfs(int v) {
	queue<int> q;
	q.push(v);

	while (!q.empty()) {
		int idx = q.front();
		q.pop();

		if (!visit[idx]) {
			visit[idx] = true;
			cout << idx << " ";

			//queue는 선입선출의 구조
			for (int i = 0; i < road[idx].size(); i++) {
				if (!visit[road[idx][i]]) {
					q.push(road[idx][i]);
				}
			}
		}
	}
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> n >> m >> v;
	//n = 4;
	//m = 5;
	//v = 1;
	for (int i = 0; i < m; i++) {
		cin >> a >> b;
		road[a].push_back(b);
		road[b].push_back(a); //양방향 간선처리
	}
	//sorting 
	for (int i = 1; i <= n; i++) {
		sort(road[i].begin(), road[i].end());
	}
	memset(visit, false, sizeof(visit));
	dfs(v);
	cout << "\n";
	memset(visit, false, sizeof(visit));
	bfs(v);
	
	return 0;
}