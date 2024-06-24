import java.util.*;

class Solution
{
    public int solution(String s)
    {
        //스택 이용해서 peek poll
        int answer = -1;
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(!st.isEmpty() && st.peek() == c){
                st.pop();
                continue;
            }
            else
                st.add(c);
        }
        if(st.isEmpty())
            return 1;
        else
            return 0;
    }
}