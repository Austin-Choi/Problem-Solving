#include <iostream>
#include <stack>
 
using namespace std;
 
// 우선순위 : 값이 클수록 우선순위가 높다.
int prior(char ch)
{
    if (ch == '(')
    {
        return 0;
    }
    else if (ch == '+' || ch == '-')
    {
        return 1;
    }
    return 2;
}
 
int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
 
    // 중위 표기식 입력
    string infix;
    cin >> infix;
 
    char ans[101];
    int ap = 0;
 
    // 후위 표기식 만들기
    stack<char> op;
    for (char ch : infix)
    {
        // 피연산자 -> 그대로 출력
        if ('A' <= ch && ch <= 'Z')
        {
            ans[ap++] = ch;
        }
        // 연산자 -> 우선순위에 따라 출력
        else
        {
            // '(' : 우선순위가 제일 낮으므로, 따로 처리해준다. push only
            if (ch == '(')
            {
                op.push(ch);
            }
            // ')' : '('를 만날때까지 출력
            else if (ch == ')')
            {
                while (!op.empty())
                {
                    char top = op.top();
                    op.pop();
                    if (top == '(')
                    {
                        break;
                    }
                    ans[ap++] = top;
                }
            }
            // 그 외 연산자 : 우선순위가 높은 연산자들을 모두 먼저 출력
            else
            {
                while (!op.empty() && prior(op.top()) >= prior(ch))
                {
                    ans[ap++] = op.top();
                    op.pop();
                }
                op.push(ch);
            }
        }
    }
 
    while (!op.empty())
    {
        ans[ap++] = op.top();
        op.pop();
    }
 
    ans[ap] = '\0';
    cout << ans;
 
    return 0;
}
