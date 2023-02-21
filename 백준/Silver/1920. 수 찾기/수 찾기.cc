#include <iostream>
#include <algorithm>

using namespace std;
int n; 
int m;
int tmp;
int nums[100000];

int my_binary_search(int n, int target) {
	int left = 0;
	int right = n-1;
	int pivot;
	while(left <= right) {
		pivot = (left + right) / 2;
		if (nums[pivot] == target)
			return 1;
		else {
			if (nums[pivot] > target)
				right = pivot - 1;
			else if (nums[pivot] < target)
				left = pivot + 1;
		}
	}
	return 0;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> nums[i];
	}

	sort(nums, nums + n);

	cin >> m;
	for (int i = 0; i < m; i++) {
		cin >> tmp;
		cout << my_binary_search(n, tmp) << "\n";
		//stl binary_serach
		//if (binary_search(nums, nums + n, tmp)) {
		//	cout << 1 << endl;
		//}
		//else
		//	cout << 0 << endl;
	}
	return 0;
}