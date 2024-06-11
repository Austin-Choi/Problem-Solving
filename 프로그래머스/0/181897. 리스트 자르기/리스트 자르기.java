import java.util.*;
import java.util.stream.Collector;

class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        int[] answer = {};
        int a = slicer[0];
        int b = slicer[1];
        int c = slicer[2];
        switch(n){
            case 1:
                answer = Arrays.copyOfRange(num_list, 0, b+1);
                break;
            case 2:
                answer = Arrays.copyOfRange(num_list, a, num_list.length);
                break;
            case 3:
                answer = Arrays.copyOfRange(num_list, a, b+1);
                break;
            case 4:
                ArrayList<Integer> l = new ArrayList<>();
                for(;a<=b;){
                    l.add(num_list[a]);
                    a+=c;
                }
                answer = l.stream().mapToInt(Integer::intValue).toArray();
            
        }
        return answer;
    }
}