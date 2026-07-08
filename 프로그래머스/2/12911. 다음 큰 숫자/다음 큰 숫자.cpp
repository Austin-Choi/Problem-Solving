#include <string>
#include <vector>
#include <bitset>
using namespace std;

int binaryCount(int n) {
    int count = 0;
    while (n > 0) {
        n % 2 == 0 ? count += 0 : count++;
        n /= 2;
    }
    return count;
}

int solution(int n) {
    int answer = n+1;
    int biN = binaryCount(n);
    while (true) {
        if ((answer ^ n) == 0 || (biN == binaryCount(answer)))
            break;
        answer++;
    }
    return answer;
}