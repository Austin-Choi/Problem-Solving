import java.util.*;
import java.util.stream.Collector;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        int[] answer = {};
        Deque<Integer> dq = new LinkedList<>();
        for(int i = 0; i<arr.length; i++){
            if(flag[i]){
                for(int j = 0; j<arr[i]*2; j++)
                    dq.offer(arr[i]);
            }
            else{
                for(int j = 0; j<arr[i]; j++)
                    dq.pollLast();
            }
        }
        return dq.stream().mapToInt(Integer::intValue).toArray();
    }
}