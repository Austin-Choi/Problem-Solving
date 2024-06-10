import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        ArrayList<Integer> answer = new ArrayList<>();
        for(int[] query : queries){
            int s = query[0];
            int e = query[1];
            int k = query[2];
            int min = 99999999;
            boolean flag = false;
            for(;s<=e;s++){
                if(arr[s] > k){
                    min = Math.min(min, arr[s]);
                    flag = true;
                }
            }
            if(flag)
                answer.add(min);
            else
                answer.add(-1);
        }
        int[] rst = answer.stream().mapToInt(Integer::intValue).toArray();
        return rst;
    }
}