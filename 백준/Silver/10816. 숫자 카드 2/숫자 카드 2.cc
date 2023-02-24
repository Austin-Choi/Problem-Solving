#include <iostream>
#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;

//binary_search stl o(logn)
vector<int> vec_n;
//hashmap o(1)
unordered_map<int, int> map;
/*
10
6 3 2 10 10 10 -10 -10 7 3
8
10 9 -5 2 3 4 5 -10
*/
//3 0 0 1 2 0 0 2
int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int n;
	cin >> n;
	int tmp;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		//vec_n.push_back(tmp);
		map[tmp]++;
	}
	
	sort(vec_n.begin(), vec_n.end());

	int m;
	cin >> m;
	for (int i = 0; i < m; i++) {
		cin >> tmp;
		//lower_bound() 주어진 값보다 크거나 같은 첫번째 원소의 위치를 시퀀스에서 찾는다
		//upper_bound() 주어진 값보다 큰 첫번째 원소의 위치를 시퀀스에서 찾는다. 
		//equal_range() lower_bound()와 upper_bound()의 결과를 모두 담은 pair을 리턴
		//cout << lower_bound(vec_n.begin(), vec_n.end(), tmp) - upper_bound(vec_n.begin(), vec_n.end(), tmp) << " ";
		cout << map[tmp] << " ";
	}
	return 0;
}