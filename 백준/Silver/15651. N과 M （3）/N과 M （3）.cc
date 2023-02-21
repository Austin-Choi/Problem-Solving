#include <iostream>
//dfs stack bfs queue
#include <stack>
#include <queue>
using namespace std;

int n;
int m;
int nums[9];
bool visited[9] = { false, };
void bt3(int count) {
	if (count == m) {
		for (int i = 0; i < m; i++) {
			cout << nums[i] << " ";
		}
		cout << "\n";
		return;
	}
	for (int i = 1; i <= n; i++) {
		//if (!visited[i]) {
		visited[i] = true;
		nums[count] = i;
		bt3(count + 1);
		visited[i] = false;
		//}
	}
}

int main() {
	cin >> n >> m;
	//bt(0);
	//bt(1, 0);
	bt3(0);
	return 0;
}