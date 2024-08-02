#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> makeBinary(int n, int limitSize=16) {
    vector<int> binaryNum;
    while (true) {
        binaryNum.push_back(n % 2);
        n /= 2;
        if (n == 0)
        {
            int limit = limitSize - binaryNum.size();
            for (int i = 0; i < limit; i++) {
                binaryNum.push_back(0);
            }
            reverse(binaryNum.begin(), binaryNum.end());
            return binaryNum;
        }
    }
}

// 비교하는거 서로 크기 다를때 고려

vector<int> operator|(vector<int> lhs, vector<int> rhs) {
    vector<int> toReturn;
    for (int i = 0; i < lhs.size();i++) {
        toReturn.push_back(lhs[i] | rhs[i]);
    }
    return toReturn;
}

vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
    vector<string> answer;
    vector<vector<int>> binaryAnswer;
    //2진수 처리
    for (int i = 0; i < arr1.size(); i++) {
        //arr1 arr2 각각 or연산으로 합치고 binaryAnswer에 저장
        binaryAnswer.push_back(makeBinary(arr1[i],n) | (makeBinary(arr2[i],n)));
    }
    //1이면 '#'' 0이면 ' '으로 처리해서 answer에 저장
    string sAnswer;
    for (auto item : binaryAnswer) {
        sAnswer="";
        for (int i = 0; i < item.size(); i++) {
            if (item[i]==1)
                sAnswer.push_back('#');
            else
                sAnswer.push_back(' ');
        }
        answer.push_back(sAnswer);
    }
    
    return answer;
}