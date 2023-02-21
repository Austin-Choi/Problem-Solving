#include <iostream>
//dfs stack bfs queue
#include <stack>
#include <queue>
using namespace std;

int n;
int m;
int nums[9];
bool visited[9] = { false, };

void bt(int count) {
	if (count == m) {
		for (int i = 0; i < m; i++) {
			cout << nums[i] << " ";
		}
		cout << "\n";
	}
	for (int i = 1; i <= n; i++) {
		if (!visited[i]) {
			visited[i] = true;
			nums[count] = i;
			bt(count+1);
			//들어가기 전 했던 행동을 들어가기 전 상태로 돌려놓게 함
			//dfs와 백트래킹 문제의 차이점
			//dfs는 재귀와 stack으로 구현하고 백트래킹은 재귀로 구현
			visited[i] = false;
			//이걸 하지 않으면 두번다시 해당 점은 재방문이 불가능
		}
	}
}

int main() {
	cin >> n >> m;
	bt(0);
	return 0;
}