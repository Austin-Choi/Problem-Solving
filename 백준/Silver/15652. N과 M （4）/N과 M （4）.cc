#include <iostream>
//dfs stack bfs queue bt 재귀
#include <stack>
#include <queue>
using namespace std;

int n;
int m;
int nums[9];
bool visited[9] = { false, };

void bt4(int num, int count) {
	if (count == m) {
		for (int i = 0; i < m; i++) {
			cout << nums[i] << " ";
		}
		cout << "\n";
        return ;
	}
	//재귀 호출에서 
	//현재 뽑은 원소의 이전값들은 고려하지 않게끔, 
	//for문의 i값을 같이 넘겨주기
	for (int i = num; i <= n; i++) {
		visited[i] = true;
		nums[count] = i;
		bt4(i, count + 1);
		visited[i] = false;
	}
}

int main() {
	cin >> n >> m;
	//bt(0);
	//bt(1, 0);
	bt4(1, 0);
	return 0;
}