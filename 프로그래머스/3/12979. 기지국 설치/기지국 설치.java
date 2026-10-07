/*
01234 56789 67891011 1213141516
stations o(N)으로 커버가능한지 한번에 훑으면서 체크하기

미도달 구간 길이 체크해서 길이/W sum up
*/
import java.util.*;
class Solution {
    public int solution(int N, int[] S, int W) {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(Math.max(0, S[0]-W-1));
        for(int i = 1; i<S.length; i++){
            int gap = S[i] - 2*W - S[i-1] - 1;
            if(gap > 0)
                l.add(gap);
        }
        l.add(Math.max(0, N-(S[S.length-1]) - W));
        
        int ans = 0;
        int ww = 2*W+1;
        for(int len : l){
            if(len % ww > 0){
                ans += (len/ww)+1;
            }
            else
                ans += len/ww;
        }
        return ans;
    }
}