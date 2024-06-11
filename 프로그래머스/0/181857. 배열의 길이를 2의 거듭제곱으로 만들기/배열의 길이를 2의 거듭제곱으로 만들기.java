import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int[] answer = {};
        int closest = 1;
        while(closest<arr.length){
            closest *= 2;
        }
        answer = Arrays.copyOf(arr, closest);
        return answer;
    }
}