import java.util.*;

class Solution {
    public List solution(int[] arr, int[] query) {
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> dq = new LinkedList<>();
        for(int i : arr){
            dq.add(i);
        }
        for(int i = 0; i<query.length; i++){
            int tmp = query[i];
            if(i%2==0){
                int rpt = dq.size() - (tmp+1);
                for(int j = 0; j<rpt; j++){
                    dq.pollLast();
                }
            }
            else{
                int rpt = tmp;
                for(int j = 0; j<rpt; j++){
                    dq.pollFirst();
                }
            }
        }
        Iterator it = dq.iterator();
        while(it.hasNext()){
            ans.add((int)it.next());
        }
        return ans;
    }
}