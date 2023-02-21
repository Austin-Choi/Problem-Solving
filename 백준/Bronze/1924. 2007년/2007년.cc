#include <iostream>
#include <string>
/*
	string days[7] = {MON, TUE, WED, THU, FRI, SAT, SUN};
	x월 y일 = x-1월까지 쌓인 일수 + y = accumlate_dates
	tot_date[12] = {31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365}
	days[tot_date % 7]

*/
using namespace std;

int main() {
	string days[7] = { "SUN","MON","TUE","WED","THU","FRI","SAT"};
	int monthdates[12] = { 31,28,31,30,31,30,31,31,30,31,30,31 };
	int total = 0;
	int x = 0; 
	int y = 0;

	cin >> x >> y;
	x -= 1;
	for (int i = 0; i < x; i++) {
		total += monthdates[i];
	}
	total += y;
	cout << days[ total % 7];
	return 0;
}