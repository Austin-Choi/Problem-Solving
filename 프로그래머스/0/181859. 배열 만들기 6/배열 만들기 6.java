import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i<arr.length;){
            if(s.empty()){
                s.push(arr[i]);
                i++;
            }
            else{
                if(s.peek()==arr[i]){
                    s.pop();
                    i++;
                }
                else{
                    s.push(arr[i]);
                    i++;
                }
            }
        }
        if(s.empty())
            s.push(-1);
        int[] answer = s.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}