import java.util.*;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        for(String str : intStrs){
            int temp = Integer.parseInt(str.substring(s,(s+l)));
            if(temp > k)
                list.add(temp);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}