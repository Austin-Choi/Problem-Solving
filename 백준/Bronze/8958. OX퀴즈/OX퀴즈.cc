#include <iostream>
#include <string>
using namespace std;
int cnt = 0;
int total = 0;

int main() {
	int n = 0;
	cin >> n;
	cin.ignore();
	for (int i = 0; i < n; i++) {
		string str;
		getline(cin, str);
		for (int i = 0; i < str.length(); i++) {
			if (str[i] == 'O') {
				cnt += 1;
			}
			else
			{
				cnt = 0;
			}
			total += cnt;
		}
		cout << total << endl;
		cnt = 0;
		total = 0;
	}
	return 0;
}