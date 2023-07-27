import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        //둘다 maxQueue생성
        PriorityQueue<Integer> w = new PriorityQueue<>((x,y)->y-x);
        PriorityQueue<Integer> h = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int[] n : sizes) {
            if(n[0]>n[1]){
        		w.add(n[0]);
        		h.add(n[1]);
            }
            else
            {
                w.add(n[1]);
        		h.add(n[0]);
            }
        }
        
        return w.peek()*h.peek();
    }
}