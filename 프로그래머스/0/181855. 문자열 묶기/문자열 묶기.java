import java.util.*;

class Solution {
    public int solution(String[] strArr) {
        int answer = -1;
        Map<Integer, Integer> m = new HashMap<>();
        for(String s : strArr){
            m.put(s.length(), m.getOrDefault(s.length(),0)+1);
        }
        for(int i : m.keySet()){
            answer = Math.max(m.get(i), answer);
        }
        return answer;
    }
}