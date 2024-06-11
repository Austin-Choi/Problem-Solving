import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        int[] answer = new int[52];
        Map<Character, Integer> m = new HashMap<>();
        for(char c : my_string.toCharArray()){
            m.put(c, m.getOrDefault(c,0)+1);
        }
        // a 97~122 -71  // A 65~90 -65
        //map은 키셋으로 iterate
        for(char c : m.keySet()){
            if(Character.isUpperCase(c)){
                answer[c-65] = m.get(c);
            }
            else{
                answer[c-71] = m.get(c);
            }
        }
        return answer;
    }
}