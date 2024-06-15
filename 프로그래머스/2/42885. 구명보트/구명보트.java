import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        Deque<Integer> ppl = new LinkedList<>();
        for(int p : people){
            ppl.add(p);
        }
        while(!ppl.isEmpty()){
            if(ppl.size() == 1){
                answer++;
                break;
            }
            int a = ppl.peekFirst();
            int b = ppl.peekLast();
            int nextLm = limit - b;
            ppl.pollLast();
            if(nextLm >= a){
                if(!ppl.isEmpty()){
                    ppl.pollFirst();
                }
            }
            answer++;
        }
        return answer;
    }
}