import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

class Solution {
    public List solution(int[] arr, int[][] intervals) {
        List l 
            = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List ans = new ArrayList<>();
        
        for(int n = 0; n < 2; n++){
            for(int i = intervals[n][0]; i <= intervals[n][1]; i++){
                ans.add(l.get(i));
            }
        }
        return ans;
    }
}