import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> l = Arrays.stream(arr).boxed().collect(Collectors.toList());
        ArrayList<Integer> ans = new ArrayList<>();
        int size = arr.length;
        int start = l.indexOf(2);
        int end = l.lastIndexOf(2);

        if(start != -1 && end != -1){
            for(;start <= end; start++){
                ans.add(arr[start]);
            }
        }
        else{
            ans.add(-1);
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}