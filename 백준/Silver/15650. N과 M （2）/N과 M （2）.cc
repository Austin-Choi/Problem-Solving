#include <iostream>
//dfs stack bfs queue
#include <stack>
#include <queue>
using namespace std;

int n;
int m;
int nums[9];
bool visited[9] = { false, };

void bt(int num, int count) {
	if (count == m) {
		for (int i = 0; i < m; i++) {
			cout << nums[i] << " ";
		}
		cout << "\n";
	}
	//재귀 호출에서 
	//현재 뽑은 원소의 이전값들은 고려하지 않게끔, 
	//for문의 i값을 같이 넘겨주기
	for (int i = num; i <= n; i++) {
		if (!visited[i]) {
			visited[i] = true;
			nums[count] = i;
			bt(i+1, count + 1);
			visited[i] = false;
		}
	}
}

int main() {
	cin >> n >> m;
	//bt(0);
	bt(1, 0);
	return 0;
}