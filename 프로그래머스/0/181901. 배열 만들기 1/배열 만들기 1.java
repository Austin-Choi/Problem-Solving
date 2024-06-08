import java.util.*;

class Solution {
    public List solution(int n, int k) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 1;i <= (n/k);i++){
            int m = k * i;
            answer.add(m);
            
        }
        return answer;
    }
}