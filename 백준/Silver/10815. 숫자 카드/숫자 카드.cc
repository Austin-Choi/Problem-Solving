#include <iostream>
#include <algorithm>
using namespace std;
int n;
long long cards[500001];
int m;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> cards[i];
	}
	sort(cards, cards + n + 1);
	cin >> m;
	long long tmp; 
	for (int i = 1; i <= m; i++) {
		cin >> tmp;
		if (binary_search(cards, cards + n + 1, tmp))
			cout << 1;
		else
			cout << 0;
		cout << " ";
	}
	return 0;
}