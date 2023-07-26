import java.util.*;

public class Solution {
    public ArrayList<Integer> solution(int []arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i : arr){
            q.add(i);
        }
        int cur = 0;
        int prev = q.poll();
        answer.add(prev);
        
        while(!q.isEmpty()){
            cur = q.poll();
            if(cur != prev)
                answer.add(cur);
            prev = cur;
        }
        return answer;
    }
}